package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.item.Item;

public class ItemRadiantLens extends Item {
    public ItemRadiantLens() {
        setRegistryName("lens_radiant");
        setUnlocalizedName(Illumination.MODID + ".lens_radiant");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}
