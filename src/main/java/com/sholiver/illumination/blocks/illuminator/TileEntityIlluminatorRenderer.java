package com.sholiver.illumination.blocks.illuminator;

import com.sholiver.illumination.util.LightBeamRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class TileEntityIlluminatorRenderer extends TileEntitySpecialRenderer<TileEntityIlluminator> {

    @Override
    public void render(TileEntityIlluminator te, double x, double y, double z, float partialTicks, int damage, float unknown) {

        if(te.isBurning())
        {
            LightBeamRenderer lightBeamRenderer = new LightBeamRenderer();
            this.bindTexture(lightBeamRenderer.texture);
            GlStateManager.pushMatrix();
            GlStateManager.translate(x+0.5,y+0.5,z+0.5);
            lightBeamRenderer.renderLightBeam(0, .4F, 0.05F, new float[]{0.3F, 0.5F, 0.9F, 0.7F});
            lightBeamRenderer.renderLightBeam(0, .4F, 0.075F, new float[]{0.4F, 0.5F, 0.6F, 0.4F});
            GlStateManager.popMatrix();
        }
    }
}
