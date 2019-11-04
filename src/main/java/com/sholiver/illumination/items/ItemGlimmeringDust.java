package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemGlimmeringDust extends Item {
    public ItemGlimmeringDust() {
        setRegistryName("dust_glimmering");
        setUnlocalizedName(Illumination.MODID + ".dust_glimmering");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}
