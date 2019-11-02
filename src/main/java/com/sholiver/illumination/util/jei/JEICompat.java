package com.sholiver.illumination.util.jei;

import com.sholiver.illumination.util.jei.illuminator.IlluminatorRecipeCategory;
import com.sholiver.illumination.util.jei.illuminator.IlluminatorRecipeMaker;
import com.sholiver.illumination.util.jei.lightfurnace.LightFurnaceRecipeCategory;
import com.sholiver.illumination.util.jei.lightfurnace.LightFurnaceRecipeMaker;
import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.util.text.translation.I18n;

import java.util.IllegalFormatException;

@JEIPlugin
public class JEICompat implements IModPlugin{
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        final IJeiHelpers helpers = registry.getJeiHelpers();
        final IGuiHelper gui = helpers.getGuiHelper();

        registry.addRecipeCategories(new IlluminatorRecipeCategory(gui));
        registry.addRecipeCategories(new LightFurnaceRecipeCategory(gui));
    }

    @Override
    public void register(IModRegistry registry) {
        final IJeiHelpers jeiHelpers = registry.getJeiHelpers();

        registry.addRecipes(IlluminatorRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.ILLUMINATOR);
        registry.addRecipes(LightFurnaceRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.LIGHT_FURNACE);
    }

    public static String translateToLocal(String key){
        if(I18n.canTranslate(key)) return I18n.translateToLocal(key);
        else return I18n.translateToFallback(key);
    }
    public static String translateToLocalFormatted(String key, Object... format){
        String s = translateToLocal(key);
        try{
            return String.format(s, format);
        }catch (IllegalFormatException e){
            return "Format error: " + s;
        }
    }
}
