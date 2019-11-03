package com.sholiver.illumination.blocks.lightrelay;

import com.sholiver.illumination.util.LightBeamRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class TileEntityLightRelayRenderer extends TileEntitySpecialRenderer<TileEntityLightRelay> {

    @Override
    public void render(TileEntityLightRelay te, double x, double y, double z, float partialTicks, int damage, float unknown) {

        if(te.isBurning() && te.getLightBeamLength() > 0)
        {
            int direction = te.getOrientation().getIndex();
            LightBeamRenderer lightBeamRenderer = new LightBeamRenderer();
            this.bindTexture(lightBeamRenderer.texture);
            GlStateManager.pushMatrix();
            GlStateManager.translate(x+0.5,y+0.5,z+0.5);
            GlStateManager.disableLighting();
            lightBeamRenderer.renderLightBeam(direction, te.getLightBeamLength(), 0.05F, new float[]{1F, 1F, 1F, 1F});
            lightBeamRenderer.renderLightBeam(direction, te.getLightBeamLength(), 0.075F, new float[]{0.8F, 0.8F, 0.8F, 0.4F});
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean isGlobalRenderer(TileEntityLightRelay te) {
        return true;
    }
}
