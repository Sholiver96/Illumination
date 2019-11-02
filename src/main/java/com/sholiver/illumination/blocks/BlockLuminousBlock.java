package com.sholiver.illumination.blocks;

import com.sholiver.illumination.Illumination;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLuminousBlock extends Block {

    public BlockLuminousBlock() {
        super(Material.IRON);
        setSoundType(SoundType.METAL);
        setLightLevel(13.0F / 15.0f);
        setHardness(2.0f);
        setResistance(20.0f);
        setRegistryName("block_luminous");
        setUnlocalizedName(Illumination.MODID + "." + "block_luminous");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}
