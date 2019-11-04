package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.item.Item;

public class ItemLuminousDust extends Item {
    public ItemLuminousDust() {
        setRegistryName("dust_luminous");
        setUnlocalizedName(Illumination.MODID + ".dust_luminous");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}
