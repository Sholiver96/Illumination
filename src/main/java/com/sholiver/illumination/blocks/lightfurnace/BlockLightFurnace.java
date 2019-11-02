package com.sholiver.illumination.blocks.lightfurnace;

import com.sholiver.illumination.Illumination;
import com.sholiver.illumination.blocks.lightmachine.BlockLightMachine;
import com.sholiver.illumination.blocks.lightreceiver.BlockLightReceiver;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class BlockLightFurnace extends BlockLightMachine {

    public BlockLightFurnace(String registryName, int maxLuminosity) {
        super(registryName, maxLuminosity, 2);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityLightFurnace().setValues(800);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof TileEntityLightFurnace){
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