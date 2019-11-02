package com.sholiver.illumination.recipe;

import com.google.common.collect.Maps;
import com.sholiver.illumination.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;

public class LightFurnaceRecipes {
    private static final LightFurnaceRecipes LIGHT_SMELTING_BASE = new LightFurnaceRecipes();
    private final Map<ItemStack, ItemStack> lightSmeltingList = Maps.newHashMap();
    private final Map<ItemStack, Integer> luminosityList = Maps.newHashMap();
    private final Map<ItemStack, Float> expList = Maps.newHashMap();

    public static LightFurnaceRecipes instance() {
        return LIGHT_SMELTING_BASE;
    }

    private LightFurnaceRecipes()
    {
        this.addLightSmeltingRecipe(new ItemStack(ModItems.DUST_GLIMMERING), new ItemStack(ModItems.INGOT_GLIMMERING), 600, 1.0F);
        this.addLightSmeltingRecipe(new ItemStack(ModItems.DUST_LUMINOUS), new ItemStack(ModItems.INGOT_LUMINOUS), 1200, 1.0F);
        this.addLightSmeltingRecipe(new ItemStack(ModItems.DUST_RADIANT), new ItemStack(ModItems.INGOT_RADIANT), 2400, 1.0F);
    }
    
    public void addLightSmeltingRecipe(ItemStack input, ItemStack output, int luminosity, Float exp)
    {
        if(!getLightSmeltingResult(input, 6400).isEmpty()) return;
        this.lightSmeltingList.put(input, output);
        if(!luminosityList.containsKey(output)) luminosityList.put(output, luminosity);
        if(!expList.containsKey(output)) expList.put(output, exp);
    }
    
    public ItemStack getLightSmeltingResult(ItemStack input, int luminosity)
    {
        for(Map.Entry<ItemStack, ItemStack> entry : lightSmeltingList.entrySet())
        {
            if(input.isItemEqual(entry.getKey()) && luminosity >= luminosityList.get(entry.getValue()))
            {
                return entry.getValue();
            }
        }
        return ItemStack.EMPTY;
    }

    public boolean isValidInput(ItemStack input)
    {
        for(Map.Entry<ItemStack, ItemStack> entry : lightSmeltingList.entrySet()) {
            if(input.isItemEqual(entry.getKey())) {
                return true;
            }
        }
        return false;
    }

    public Float getExperiance(ItemStack output) {
        if(expList.containsKey(output)) return expList.get(output);
        return 0F;
    }

    public int getRequiredLuminosity(ItemStack output) {
        if(luminosityList.containsKey(output)) return luminosityList.get(output);
        return 0;
    }

    public Map<ItemStack, ItemStack> getLightSmeltingList() {
        return this.lightSmeltingList;
    }

    public int getCookSpeed(ItemStack output, int luminosity) {
        if(getRequiredLuminosity(output)==0) return 0;
        return (8 * luminosity) / getRequiredLuminosity(output);
    }
}
