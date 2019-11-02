package com.sholiver.illumination.tabs;

import com.sholiver.illumination.Illumination;
import com.sholiver.illumination.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class IlluminationTab extends CreativeTabs {
    public IlluminationTab()
    {
        super(Illumination.MODID);
    }
    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.DUST_GLIMMERING);
    }
}
