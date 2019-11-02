package com.sholiver.illumination.util.jei.lightfurnace;

import com.sholiver.illumination.recipe.IlluminatorRecipes;
import com.sholiver.illumination.recipe.LightFurnaceRecipes;
import com.sholiver.illumination.util.jei.JEICompat;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LightFurnaceRecipeWrapper implements IRecipeWrapper {

    private final ItemStack input;
    private final List<List<ItemStack>> inputs;
    private final ItemStack output;


    public LightFurnaceRecipeWrapper(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;

        this.inputs = new ArrayList<>();
        inputs.add(Collections.singletonList(input));
    }


    @Override
    public void getIngredients(IIngredients ingredients) {

        ingredients.setInputLists(VanillaTypes.ITEM, inputs);
        ingredients.setOutput(VanillaTypes.ITEM, output);
    }

    public List getInputs() {
        return inputs;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        LightFurnaceRecipes recipes = LightFurnaceRecipes.instance();
        float experience = recipes.getExperiance(output);
        if (experience > 0) {
            String experienceString = JEICompat.translateToLocalFormatted("gui.jei.category.smelting.experience", experience);
            FontRenderer fontRenderer = minecraft.fontRenderer;
            int stringWidth = fontRenderer.getStringWidth(experienceString);
            fontRenderer.drawString(experienceString, recipeWidth - stringWidth, 0, Color.gray.getRGB());
        }
    }
}
