package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.item.Item;

public class ItemLuminousIngot extends Item {
    public ItemLuminousIngot() {
        setRegistryName("ingot_luminous");
        setUnlocalizedName(Illumination.MODID + ".ingot_luminous");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}
