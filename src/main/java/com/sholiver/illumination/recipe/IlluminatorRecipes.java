package com.sholiver.illumination.recipe;

import com.google.common.collect.Maps;
import com.sholiver.illumination.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IlluminatorRecipes {
    private static final IlluminatorRecipes ILLUMINATING_BASE = new IlluminatorRecipes();
    private final List<Triple<ItemStack, ItemStack, ItemStack>> illuminationList = new ArrayList<>();
    private final Map<ItemStack, Integer> luminosityList = Maps.newHashMap();
    private final Map<ItemStack, Float> expList = Maps.newHashMap();

    public static IlluminatorRecipes instance() {
        return ILLUMINATING_BASE;
    }

    private IlluminatorRecipes()
    {
        this.addIlluminationRecipe(new ItemStack(Items.COAL), new ItemStack(ModItems.DUST_GLIMMERING), new ItemStack(ModItems.COAL_GLIMMERING), 600, 0.7F);
        this.addIlluminationRecipe(new ItemStack(Items.COAL), new ItemStack(ModItems.DUST_LUMINOUS), new ItemStack(ModItems.COAL_LUMINOUS),1200, 0.9F);
        this.addIlluminationRecipe(new ItemStack(Items.COAL), new ItemStack(ModItems.DUST_RADIANT), new ItemStack(ModItems.COAL_RADIANT),2400, 1.2F);
    }

    public void addIlluminationRecipe(ItemStack item, ItemStack dust, ItemStack output, int luminosity,Float exp)
    {
        if(!getIlluminationResult(item, dust, 6400).isEmpty()) return;
        this.illuminationList.add(Triple.of(item, dust, output));
        if(!luminosityList.containsKey(output)) this.luminosityList.put(output, luminosity);
        if(!expList.containsKey(output)) this.expList.put(output, exp);
    }

    public ItemStack getIlluminationResult(ItemStack item, ItemStack dust, int luminosity)
    {
        for(Triple<ItemStack, ItemStack, ItemStack> triple : illuminationList)
        {
            if(item.isItemEqual(triple.getLeft())
            && dust.isItemEqual(triple.getMiddle())
            && luminosity >= luminosityList.get(triple.getRight()))
            {
                return triple.getRight();
            }
        }
        return ItemStack.EMPTY;
    }

    public boolean isValidInputSlot(ItemStack input)
    {
        for(Triple<ItemStack, ItemStack, ItemStack> triple : illuminationList)
        {
            if(input.isItemEqual(triple.getLeft())) return true;
        }
        return false;
    }

    public boolean isValidDustSlot(ItemStack dust)
    {
        for(Triple<ItemStack, ItemStack, ItemStack> triple : illuminationList)
        {
            if(dust.isItemEqual(triple.getMiddle())) return true;
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

    public List<Triple<ItemStack, ItemStack, ItemStack>> getIlluminationList()
    {
        return this.illuminationList;
    }

    public int getCookSpeed(ItemStack output, int luminosity) {
        if(getRequiredLuminosity(output)==0) return 0;
        return (8 * luminosity) / getRequiredLuminosity(output);
    }
}
