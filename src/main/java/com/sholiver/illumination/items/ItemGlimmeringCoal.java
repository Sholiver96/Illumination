package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGlimmeringCoal extends Item {

    public ItemGlimmeringCoal() {
        setRegistryName("coal_glimmering");
        setUnlocalizedName(Illumination.MODID + ".coal_glimmering");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }

    @Override
    public int getItemBurnTime(ItemStack stack) {
        return 3200;
    }
}
