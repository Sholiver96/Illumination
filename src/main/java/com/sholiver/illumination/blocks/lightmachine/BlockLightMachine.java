package com.sholiver.illumination.blocks.lightmachine;

import com.sholiver.illumination.Illumination;
import com.sholiver.illumination.blocks.lightreceiver.BlockLightReceiver;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.sholiver.illumination.util.WorldUtil.FACING;

public abstract class BlockLightMachine extends BlockLightReceiver {

    public final int guiId;
    protected final int maxLuminosity;

    public BlockLightMachine(String registryName, int maxLuminosity, int guiId) {
        super(registryName);
        this.guiId = guiId;
        this.maxLuminosity = maxLuminosity;
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
