package com.sholiver.illumination.blocks.lightreceiver;

import com.sholiver.illumination.Illumination;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

import static com.sholiver.illumination.util.WorldUtil.FACING;

public abstract class BlockLightReceiver extends Block implements ITileEntityProvider {

    public BlockLightReceiver(String registryName) {
        super(Material.IRON);
        setRegistryName(registryName);
        setUnlocalizedName(Illumination.MODID + "." + registryName);
        setCreativeTab(Illumination.ILLUMINATION_TAB);
        setHardness(2.0f);
        setResistance(20.0f);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        // Since we only allow horizontal rotation we need only 2 bits for facing. North, South, West, East start at index 2 so we have to add 2 here.
        return getDefaultState().withProperty(FACING, EnumFacing.getFront((meta & 3) + 2));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        // Since we only allow horizontal rotation we need only 2 bits for facing. North, South, West, East start at index 2 so we have to subtract 2 here.
        return state.getValue(FACING).getIndex()-2;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }
}
