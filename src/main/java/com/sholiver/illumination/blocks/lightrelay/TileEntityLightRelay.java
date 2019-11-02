package com.sholiver.illumination.blocks.lightrelay;

import com.sholiver.illumination.blocks.lightreceiver.BlockLightReceiver;
import com.sholiver.illumination.blocks.lightreceiver.TileEntityLightReceiver;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileEntityLightRelay extends TileEntityLightReceiver {

    public final int maxLength = 10;
    @Override
    public void update() {
        if(!this.world.isRemote) {
            if(this.world.getWorldTime() % 20L == 0 && luminosity != getLuminosityFromNearbyLenses()) {
                luminosity = getLuminosityFromNearbyLenses();
                this.world.markAndNotifyBlock(pos, null, world.getBlockState(pos), world.getBlockState(pos), 2);
            }
        }
    }

    protected float getLightBeamLength() {
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
                return 0F;
            }
        }
        return 0F;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(this.getPos().south(maxLength).east(maxLength), this.getPos().north(maxLength).west(maxLength).up(1));
    }
}
