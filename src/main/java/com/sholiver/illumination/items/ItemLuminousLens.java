package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.item.Item;

public class ItemLuminousLens extends Item {
    public ItemLuminousLens() {
        setRegistryName("lens_luminous");
        setUnlocalizedName(Illumination.MODID + ".lens_luminous");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}
