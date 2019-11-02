package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.item.Item;

public class ItemLens extends Item {
    public ItemLens(String registryName) {
        setRegistryName(registryName);
        setUnlocalizedName(Illumination.MODID + "." + registryName);
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}
