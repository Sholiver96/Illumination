package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemRadiantCoal extends Item {

    public ItemRadiantCoal() {
        setRegistryName("coal_radiant");
        setUnlocalizedName(Illumination.MODID + ".coal_radiant");
        setCreativeTab(Illumination.ILLUMINATION_TAB);
    }

    @Override
    public int getItemBurnTime(ItemStack stack) {
        return 12800;
    }
}
