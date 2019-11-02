package com.sholiver.illumination.util.jei.lightfurnace;

import com.sholiver.illumination.Illumination;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractLightFurnaceRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T> {
    protected static final ResourceLocation TEXTURES = new ResourceLocation(Illumination.MODID, "textures/gui/light_furnace_gui.png");

    protected IDrawableStatic staticFlame;
    protected IDrawableAnimated animatedFlame;
    protected IDrawableStatic staticFlameGlimmering;
    protected IDrawableAnimated animatedFlameGlimmering;
    protected IDrawableStatic staticFlameLuminous;
    protected IDrawableAnimated animatedFlameLuminous;
    protected IDrawableStatic staticFlameRadiant;
    protected IDrawableAnimated animatedFlameRadiant;

    protected IDrawableStatic staticLuminosity;
    protected IDrawableAnimated animatedArrow;

    protected AbstractLightFurnaceRecipeCategory(IGuiHelper helper)
    {
        staticFlame = helper.createDrawable(TEXTURES, 176, 0, 14, 14);
        animatedFlame = helper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);

        staticFlameGlimmering = helper.createDrawable(TEXTURES, 190, 0, 14, 14);
        animatedFlameGlimmering = helper.createAnimatedDrawable(staticFlameGlimmering, 300, IDrawableAnimated.StartDirection.TOP, true);

        staticFlameLuminous = helper.createDrawable(TEXTURES, 204, 0, 14, 14);
        animatedFlameLuminous = helper.createAnimatedDrawable(staticFlameLuminous, 300, IDrawableAnimated.StartDirection.TOP, true);

        staticFlameRadiant = helper.createDrawable(TEXTURES, 218, 0, 14, 14);
        animatedFlameRadiant = helper.createAnimatedDrawable(staticFlameRadiant, 300, IDrawableAnimated.StartDirection.TOP, true);

        IDrawableStatic staticArrow = helper.createDrawable(TEXTURES, 176, 14, 24, 17);
        animatedArrow = helper.createAnimatedDrawable(staticArrow, 200, IDrawableAnimated.StartDirection.LEFT, false);

        staticLuminosity = helper.createDrawable(TEXTURES, 176,31,8, 52);
    }

    protected IDrawable getFlameAnimation(int luminosity)
    {
        switch (luminosity)
        {
            case 150: return animatedFlameGlimmering;
            case 200: return animatedFlameLuminous;
            case 250: return animatedFlameRadiant;
            default: return animatedFlame;
        }
    }

    protected int getScaledLuminosity(int luminosity, int scale)
    {
        return luminosity != 0 ? (luminosity * scale) / 250 : 0;
    }
}
