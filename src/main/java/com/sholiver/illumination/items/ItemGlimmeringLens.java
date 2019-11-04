package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.item.Item;

public class ItemGlimmeringLens extends Item {
    public ItemGlimmeringLens() {
        setRegistryName("lens_glimmering");
        setUnlocalizedName(Illumination.MODID + ".lens_glimmering");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }
}
