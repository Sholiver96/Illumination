package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.item.Item;

public class ItemRadiantDust extends Item {
    public ItemRadiantDust() {
        setRegistryName("dust_radiant");
        setUnlocalizedName(Illumination.MODID + ".dust_radiant");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}
