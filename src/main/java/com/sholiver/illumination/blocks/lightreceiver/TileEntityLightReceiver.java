package com.sholiver.illumination.blocks.lightreceiver;

import com.sholiver.illumination.blocks.solarlens.TileEntitySolarLens;
import com.sholiver.illumination.blocks.lightrelay.TileEntityLightRelay;
import com.sholiver.illumination.util.EnumTier;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import static com.sholiver.illumination.util.Properties.FACING;

public abstract class TileEntityLightReceiver extends TileEntity implements ITickable {

    public int luminosity;
    public EnumTier tier;

    public TileEntityLightReceiver(){
        super();
    }

    protected int getLuminosityFromNearbyLenses() {
        int luminosity = 0;
        for(int i = 2; i<=5; i++)
        {
            if(i != getOrientation().getIndex()){
                for(int j = 1; j<=10; j++){
                    BlockPos blockPos;
                    switch(i)
                    {
                        case 2: blockPos = pos.north(j); break;
                        case 3: blockPos = pos.south(j); break;
                        case 4: blockPos = pos.west(j); break;
                        case 5: blockPos = pos.east(j); break;
                        default: blockPos = pos;
                    }
                    TileEntity te = world.getTileEntity(blockPos);
                    Block block = world.getBlockState(blockPos).getBlock();
                    if(te instanceof TileEntitySolarLens){
                        if(((TileEntitySolarLens) te).getOrientation().getOpposite().getIndex() == i){
                            luminosity += (((TileEntitySolarLens) te).luminosity * tier.getEfficiency());
                        }
                        j=11;
                    }
                    if(te instanceof TileEntityLightRelay){
                        if(((TileEntityLightRelay) te).getOrientation().getOpposite().getIndex() == i){
                            luminosity += (((TileEntityLightRelay) te).luminosity * tier.getEfficiency());
                        }
                        j=11;
                    }
                    if(block != Blocks.AIR){
                        j = 11;
                    }
                }
            }
        }
        return luminosity;
    }

    public EnumFacing getOrientation() {
        IBlockState state = this.world.getBlockState(this.pos);
        return state.getValue(FACING);
    }

    public boolean isBurning() {
        return luminosity > 0;
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.luminosity = compound.getInteger("Luminosity");
        this.tier = EnumTier.fromIndex(compound.getInteger("Tier"));
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("Luminosity", this.luminosity);
        compound.setInteger("Tier", this.tier.getIndex());
        return compound;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.getPos(), 0, this.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.setPos(pkt.getPos());
        this.readFromNBT(pkt.getNbtCompound());
    }
}
