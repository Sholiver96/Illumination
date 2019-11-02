package com.sholiver.illumination.items;

import com.sholiver.illumination.Illumination;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemLightCoal extends Item {
    private final int burnTime;
    public ItemLightCoal(String registryName, int burnTime) {
        setRegistryName(registryName);
        setUnlocalizedName(Illumination.MODID + "." + registryName);
        setCreativeTab(Illumination.ILLUMINATION_TAB);
        this.burnTime = burnTime;
    }

    @Override
    public int getItemBurnTime(ItemStack stack) {
        return burnTime;
    }
}
