package com.sholiver.illumination.util.jei.illuminator;

import com.sholiver.illumination.Illumination;
import com.sholiver.illumination.ModBlocks;
import com.sholiver.illumination.recipe.IlluminatorRecipes;
import com.sholiver.illumination.util.jei.RecipeCategories;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class IlluminatorRecipeCategory extends AbstractIlluminatorRecipeCategory<IlluminatorRecipeWrapper> {

    private final IDrawable background;
    private final IDrawable icon;
    private final String name;
    protected int luminosity;

    public IlluminatorRecipeCategory(IGuiHelper helper) {
        super(helper);
        this.background = helper.createDrawable(TEXTURES, 4, 12, 150, 60);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.ILLUMINATOR));
        this.name = "Illuminator";
    }

    @Override
    public String getUid() {
        return RecipeCategories.ILLUMINATOR;
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
    public void setRecipe(IRecipeLayout recipeLayout, IlluminatorRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup stacks = recipeLayout.getItemStacks();

        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);
        this.luminosity = IlluminatorRecipes.instance().getRequiredLuminosity(outputs.get(0).get(0));


        stacks.init(0, true, 42, 4);
        stacks.init(1, true, 60, 4);
        stacks.init(2, false, 111, 22);

        stacks.set(ingredients);
    }
}
