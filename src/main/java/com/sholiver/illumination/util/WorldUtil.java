package com.sholiver.illumination.util;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

public class WorldUtil {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public static EnumFacing getDirectionByPistonRotation(IBlockState state) {
        return state.getValue(FACING);
    }
}
