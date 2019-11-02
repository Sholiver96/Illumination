package com.sholiver.illumination.blocks.illuminator;

import com.sholiver.illumination.blocks.lightmachine.BlockLightMachine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class BlockIlluminator extends BlockLightMachine {

    public BlockIlluminator(String registryName, int maxLuminosity) {
        super(registryName, maxLuminosity, 1);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityIlluminator().setValues(800);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof TileEntityIlluminator){
            ItemStackHandler ish = (ItemStackHandler) te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            for(int i=0;i<ish.getSlots();i++) {
                if(ish.getStackInSlot(i) != null){
                    world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), ish.getStackInSlot(i)));
                }
            }
        }
        super.breakBlock(world, pos, state);
    }
}
