package com.sholiver.illumination.blocks;

import com.sholiver.illumination.Illumination;
import com.sholiver.illumination.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockLuminousOre extends Block {
    public BlockLuminousOre() {
        super(Material.ROCK);
        setSoundType(SoundType.STONE);
        setLightLevel(9.0F / 15.0f);
        setHardness(2.0f);
        setResistance(20.0f);
        setRegistryName("ore_luminous");
        setUnlocalizedName(Illumination.MODID + "." + "ore_luminous");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
        setHarvestLevel("pickaxe", 2);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.DUST_LUMINOUS;
    }
}
