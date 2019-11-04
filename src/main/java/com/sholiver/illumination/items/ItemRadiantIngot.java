package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.item.Item;

public class ItemRadiantIngot extends Item {
    public ItemRadiantIngot() {
        setRegistryName("ingot_radiant");
        setUnlocalizedName(Illumination.MODID + ".ingot_radiant");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}
