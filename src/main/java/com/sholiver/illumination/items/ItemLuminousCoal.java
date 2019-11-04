package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemLuminousCoal extends Item {

    public ItemLuminousCoal() {
        setRegistryName("coal_luminous");
        setUnlocalizedName(Illumination.MODID + ".coal_luminous");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }

    @Override
    public int getItemBurnTime(ItemStack stack) {
        return 6400;
    }
}
