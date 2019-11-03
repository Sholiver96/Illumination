package com.sholiver.illumination.blocks.lightmachine;

import com.sholiver.illumination.Illumination;
import com.sholiver.illumination.blocks.lightreceiver.BlockLightReceiver;
import com.sholiver.illumination.util.EnumTier;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.sholiver.illumination.util.Properties.FACING;

public abstract class BlockLightMachine extends BlockLightReceiver {

    public final int guiId;


    public BlockLightMachine(String registryName, EnumTier tier, int guiId) {
        super(registryName, tier);
        this.guiId = guiId;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
                                    EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        }
        TileEntity te = world.getTileEntity(pos);
        if (!(te instanceof TileEntityLightMachine)) {
            return false;
        }
        player.openGui(Illumination.instance, guiId, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }
}
