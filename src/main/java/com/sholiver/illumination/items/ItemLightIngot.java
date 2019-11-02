package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemLightIngot extends Item {
    public ItemLightIngot(String registryName) {
        setRegistryName(registryName);
        setUnlocalizedName(Illumination.MODID + "." + registryName);
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}