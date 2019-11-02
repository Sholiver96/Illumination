package com.sholiver.illumination.blocks;

import com.sholiver.illumination.Illumination;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockRadiantBlock extends Block {

    public BlockRadiantBlock() {
        super(Material.IRON);
        setSoundType(SoundType.METAL);
        setLightLevel(15.0F / 15.0f);
        setHardness(2.0f);
        setResistance(20.0f);
        setRegistryName("block_radiant");
        setUnlocalizedName(Illumination.MODID + "." + "block_radiant");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}
