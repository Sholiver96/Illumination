package com.sholiver.illumination.util.jei.lightfurnace;

import com.sholiver.illumination.Illumination;
import com.sholiver.illumination.ModBlocks;
import com.sholiver.illumination.recipe.LightFurnaceRecipes;
import com.sholiver.illumination.util.jei.RecipeCategories;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import java.util.List;

public class LightFurnaceRecipeCategory extends AbstractLightFurnaceRecipeCategory<LightFurnaceRecipeWrapper> {

    private final IDrawable background;
    private final IDrawable icon;
    private final String name;
    protected int luminosity;

    public LightFurnaceRecipeCategory(IGuiHelper helper) {
        super(helper);
        this.background = helper.createDrawable(TEXTURES, 4, 12, 150, 60);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.LIGHT_FURNACE));
        this.name = "Light Furnace";
    }

    @Override
    public String getUid() {
        return RecipeCategories.LIGHT_FURNACE;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getModName() {
        return Illumination.NAME;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        getFlameAnimation(luminosity).draw(minecraft, 52, 24);
        animatedArrow.draw(minecraft, 75, 22);
        staticLuminosity.draw(minecraft, 17, 5, 52 - getScaledLuminosity(luminosity, 52),0,0,0);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, LightFurnaceRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup stacks = recipeLayout.getItemStacks();

        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);
        this.luminosity = LightFurnaceRecipes.instance().getRequiredLuminosity(outputs.get(0).get(0));

        stacks.init(0, true, 51, 4);
        stacks.init(1, false, 111, 22);

        stacks.set(ingredients);
    }
}
