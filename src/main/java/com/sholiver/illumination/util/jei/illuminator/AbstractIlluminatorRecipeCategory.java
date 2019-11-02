package com.sholiver.illumination.util.jei.illuminator;

import com.sholiver.illumination.Illumination;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

import static net.minecraftforge.client.resource.VanillaResourceType.TEXTURES;

public abstract class AbstractIlluminatorRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T> {
    protected static final ResourceLocation TEXTURES = new ResourceLocation(Illumination.MODID, "textures/gui/illuminator_gui.png");

    protected IDrawableStatic staticFlame;
    protected IDrawableStatic staticFlameGlimmering;
    protected IDrawableStatic staticFlameLuminous;
    protected IDrawableStatic staticFlameRadiant;

    protected IDrawableStatic staticLuminosity;
    protected IDrawableAnimated animatedArrow;

    protected AbstractIlluminatorRecipeCategory(IGuiHelper helper)
    {
        staticFlame = helper.createDrawable(TEXTURES, 176, 0, 14, 14);
        staticFlameGlimmering = helper.createDrawable(TEXTURES, 190, 0, 14, 14);
        staticFlameLuminous = helper.createDrawable(TEXTURES, 204, 0, 14, 14);
        staticFlameRadiant = helper.createDrawable(TEXTURES, 218, 0, 14, 14);

        IDrawableStatic staticArrow = helper.createDrawable(TEXTURES, 176, 14, 24, 17);
        animatedArrow = helper.createAnimatedDrawable(staticArrow, 200, IDrawableAnimated.StartDirection.LEFT, false);

        staticLuminosity = helper.createDrawable(TEXTURES, 176,31,8, 52);
    }

    protected IDrawable getFlameAnimation(int luminosity)
    {
        switch (luminosity)
        {
            case 150: return staticFlameGlimmering;
            case 200: return staticFlameLuminous;
            case 250: return staticFlameRadiant;
            default: return staticFlame;
        }
    }

    protected int getScaledLuminosity(int luminosity, int scale)
    {
        return luminosity != 0 ? (luminosity * scale) / 250 : 0;
    }
}
