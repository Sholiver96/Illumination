package com.sholiver.illumination.blocks.lightlens;

import com.sholiver.illumination.blocks.lightreceiver.TileEntityLightReceiver;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import static com.sholiver.illumination.util.WorldUtil.FACING;

public abstract class TileEntityLightLens extends TileEntity implements ITickable {

    public int luminosity;
    public int maxLuminosity;
    public int maxLength;

    public TileEntityLightLens(){super();}

    public TileEntityLightLens setMaxValues(int maxLength, int maxLuminosity){
        this.maxLength = maxLength;
        this.maxLuminosity = maxLuminosity;
        return this;
    }

    @Override
    public void update() {
        if(!this.world.isRemote)
        {
            if(luminosity != getLuminosityFromSource())
            {
                luminosity = getLuminosityFromSource();
                this.world.markAndNotifyBlock(pos, null, world.getBlockState(pos), world.getBlockState(pos), 2);
            }
        }
    }

    public abstract int getLuminosityFromSource();

    public float getLightBeamLength() {
        for(int i = 1; i <= maxLength; i++) {
            BlockPos blockPos;
            switch (getOrientation()) {
                case NORTH: blockPos = pos.north(i); break;
                case SOUTH: blockPos = pos.south(i); break;
                case WEST: blockPos = pos.west(i); break;
                case EAST: blockPos = pos.east(i); break;
                default: blockPos = pos;
            }
            Block block = world.getBlockState(blockPos).getBlock();
            TileEntity te = world.getTileEntity(blockPos);
            if(te instanceof TileEntityLightReceiver){
                if(((TileEntityLightReceiver) te).getOrientation() == getOrientation().getOpposite()){
                    return 0F;
                }
                return (float) i;
            }
            if(block != Blocks.AIR){
                return 0;
            }
        }
        return 0F;
    }

    public int getField(int index) {
        switch (index) {
            case 0:
                return this.luminosity;
            case 1:
                return this.maxLuminosity;
            case 2:
                return this.maxLength;
            default:
                return 0;
        }
    }

    public void setField(int index, int value){
        switch (index) {
            case 0:
                this.luminosity = value;
                return;
            case 1:
                this.maxLuminosity = value;
                return;
            case 2:
                this.maxLength = value;
                return;
        }
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        EnumFacing rotation = this.getOrientation();
        switch(rotation)
        {
            case DOWN: return new AxisAlignedBB(this.getPos(), this.getPos().down(maxLength));
            case NORTH: return new AxisAlignedBB(this.getPos(), this.getPos().north(maxLength));
            case SOUTH: return new AxisAlignedBB(this.getPos(), this.getPos().south(maxLength));
            case WEST: return new AxisAlignedBB(this.getPos(), this.getPos().west(maxLength));
            case EAST: return new AxisAlignedBB(this.getPos(), this.getPos().east(maxLength));
            default: return new AxisAlignedBB(this.getPos(), this.getPos().up(maxLength));
        }
    }

    public EnumFacing getOrientation() {
        IBlockState state = this.world.getBlockState(this.pos);
        return state.getValue(FACING);
    }

    public boolean isBurning() { return luminosity > 0;}

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.luminosity = compound.getInteger("Luminosity");
        this.maxLuminosity = compound.getInteger("MaxLuminosity");
        this.maxLength = compound.getInteger("MaxLength");
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("Luminosity", this.luminosity);
        compound.setInteger("MaxLuminosity", this.maxLuminosity);
        compound.setInteger("MaxLength", this.maxLength);
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
