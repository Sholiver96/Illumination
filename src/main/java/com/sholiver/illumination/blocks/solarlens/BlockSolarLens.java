package com.sholiver.illumination.blocks.solarlens;

import com.sholiver.illumination.blocks.lightlens.BlockLightLens;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockSolarLens extends BlockLightLens{

    public BlockSolarLens() {
        super("solar_lens", 100);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntitySolarLens().setMaxValues(10, 100);
    }
}
