package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemGlimmeringIngot extends Item {
    public ItemGlimmeringIngot() {
        setRegistryName("ingot_glimmering");
        setUnlocalizedName(Illumination.MODID + ".ingot_glimmering");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}
