package com.sholiver.illumination.util.jei.illuminator;

import com.google.common.collect.Lists;
import com.sholiver.illumination.recipe.IlluminatorRecipes;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Triple;

import java.util.List;

public class IlluminatorRecipeMaker {
    public static List<IlluminatorRecipeWrapper> getRecipes(IJeiHelpers helpers)
    {
        IStackHelper stackHelper = helpers.getStackHelper();
        IlluminatorRecipes instance = IlluminatorRecipes.instance();
        List<Triple<ItemStack, ItemStack, ItemStack>> recipes = instance.getIlluminationList();
        List<IlluminatorRecipeWrapper> jeiRecipes = Lists.newArrayList();

        for(Triple<ItemStack, ItemStack, ItemStack> triple: recipes)
        {
            ItemStack itemInput = triple.getLeft();
            ItemStack dustInput = triple.getMiddle();
            ItemStack output = triple.getRight();
            IlluminatorRecipeWrapper recipe = new IlluminatorRecipeWrapper(itemInput, dustInput, output);
            jeiRecipes.add(recipe);
        }
        return jeiRecipes;
    }
}
