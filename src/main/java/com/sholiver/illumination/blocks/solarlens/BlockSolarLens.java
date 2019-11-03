package com.sholiver.illumination.blocks.solarlens;

import com.sholiver.illumination.blocks.lightlens.BlockLightLens;
import com.sholiver.illumination.util.EnumTier;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static com.sholiver.illumination.util.EnumTier.BASIC;

public class BlockSolarLens extends BlockLightLens{

    public BlockSolarLens(String registryName, EnumTier tier) {
        super(registryName, tier);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntitySolarLens(tier);
    }
}
