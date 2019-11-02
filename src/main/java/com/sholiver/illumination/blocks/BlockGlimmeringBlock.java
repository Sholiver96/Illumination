package com.sholiver.illumination.blocks;

import com.sholiver.illumination.Illumination;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockGlimmeringBlock extends Block {

    public BlockGlimmeringBlock() {
        super(Material.IRON);
        setSoundType(SoundType.METAL);
        setLightLevel(11.0F / 15.0f);
        setHardness(2.0f);
        setResistance(20.0f);
        setRegistryName("block_glimmering");
        setUnlocalizedName(Illumination.MODID + "." + "block_glimmering");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}
