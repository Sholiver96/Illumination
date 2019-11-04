package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.item.Item;

public class ItemLens extends Item {
    public ItemLens() {
        setRegistryName("lens");
        setUnlocalizedName(Illumination.MODID + ".lens");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}
