package com.sholiver.illumination.util.jei.lightfurnace;

import com.google.common.collect.Lists;
import com.sholiver.illumination.recipe.IlluminatorRecipes;
import com.sholiver.illumination.recipe.LightFurnaceRecipes;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Triple;

import java.util.List;
import java.util.Map;

public class LightFurnaceRecipeMaker {
    public static List<LightFurnaceRecipeWrapper> getRecipes(IJeiHelpers helpers)
    {
        LightFurnaceRecipes instance = LightFurnaceRecipes.instance();
        Map<ItemStack, ItemStack> recipes = instance.getLightSmeltingList();
        List<LightFurnaceRecipeWrapper> jeiRecipes = Lists.newArrayList();

        for(Map.Entry<ItemStack, ItemStack> entry: recipes.entrySet())
        {
            ItemStack input = entry.getKey();
            ItemStack output = entry.getValue();
            LightFurnaceRecipeWrapper recipe = new LightFurnaceRecipeWrapper(input, output);
            jeiRecipes.add(recipe);
        }
        return jeiRecipes;
    }
}
