package com.sholiver.illumination.util;

import com.sholiver.illumination.Illumination;
import com.sholiver.illumination.blocks.illuminator.TileEntityIlluminator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.lang.ref.Reference;

import static net.minecraft.client.renderer.GlStateManager.disableBlend;
import static net.minecraft.client.renderer.GlStateManager.enableBlend;

@SideOnly(Side.CLIENT)
public class LightBeamRenderer {
    public static final ResourceLocation texture = new ResourceLocation(Illumination.MODID,"textures/misc/beam.png");

    public void renderLightBeam(int direction, float length, float radius, float[] color)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        float[] x;
        float[] y;
        float[] z;
        float rotX=0F;
        float rotZ=0F;
        switch(direction)
        {
            case 1:
                x = new float[]{-radius, +radius};
                y = new float[]{0, -length};
                z = new float[]{-radius, +radius};
                break;
            case 2:
                x = new float[]{-radius, +radius};
                y = new float[]{-radius, +radius};
                z = new float[]{0, -length};
                rotZ=1;
                break;
            case 3:
                x = new float[]{-radius, +radius};
                y = new float[]{-radius, +radius};
                z = new float[]{0, length};
                rotZ=1;
                break;
            case 4:
                x = new float[]{0, -length};
                y = new float[]{-radius, +radius};
                z = new float[]{-radius, +radius};
                rotX=1;
                break;
            case 5:
                x = new float[]{0, length};
                y = new float[]{-radius, +radius};
                z = new float[]{-radius, +radius};
                rotX=1;
                break;
            default:
                x = new float[]{-radius, +radius};
                y = new float[]{0, length};
                z = new float[]{-radius, +radius};
        }

        GlStateManager.pushMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
         if (color[3] >= 1F) {
             GlStateManager.disableBlend();
         } else {
             GlStateManager.enableBlend();
         }
        GlStateManager.disableCull();
        GlStateManager.depthMask(color[3] >= 1F);

        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        //south
        buffer.pos(x[0], y[1], z[1]).tex(0, 1).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[0], y[0], z[1]).tex(rotX, rotX).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[1], y[0], z[1]).tex(1, 0).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[1], y[1], z[1]).tex(1-rotX, 1-rotX).color(color[0], color[1], color[2], color[3]).endVertex();
        //north
        buffer.pos(x[0], y[1], z[0]).tex(0, 1).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[0], y[0], z[0]).tex(rotX, rotX).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[1], y[0], z[0]).tex(1, 0).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[1], y[1], z[0]).tex(1-rotX, 1-rotX).color(color[0], color[1], color[2], color[3]).endVertex();
        //east
        buffer.pos(x[1], y[1], z[0]).tex(0, 1).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[1], y[0], z[0]).tex(rotZ, rotZ).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[1], y[0], z[1]).tex(1, 0).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[1], y[1], z[1]).tex(1-rotZ, 1-rotZ).color(color[0], color[1], color[2], color[3]).endVertex();
        //west
        buffer.pos(x[0], y[1], z[0]).tex(0, 1).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[0], y[0], z[0]).tex(rotZ, rotZ).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[0], y[0], z[1]).tex(1, 0).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[0], y[1], z[1]).tex(1-rotZ, 1-rotZ).color(color[0], color[1], color[2], color[3]).endVertex();
        //up
        buffer.pos(x[1], y[1], z[0]).tex(0, 1).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[0], y[1], z[0]).tex(rotZ, rotZ).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[0], y[1], z[1]).tex(1, 0).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[1], y[1], z[1]).tex(1-rotZ, 1-rotZ).color(color[0], color[1], color[2], color[3]).endVertex();
        //down
        buffer.pos(x[1], y[0], z[0]).tex(0, 1).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[0], y[0], z[0]).tex(rotZ, rotZ).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[0], y[0], z[1]).tex(1, 0).color(color[0], color[1], color[2], color[3]).endVertex();
        buffer.pos(x[1], y[0], z[1]).tex(1-rotZ, 1-rotZ).color(color[0], color[1], color[2], color[3]).endVertex();
        tessellator.draw();

        GlStateManager.popMatrix();
    }

    public float[] getColorFromLuminosity(int luminosity, float alpha){
        if(luminosity<=800){
            return new float[]{0.85F, 0.29F, 0.25F, alpha * (0.3F + 0.7F *(float)luminosity/800F)};
        }
        else if(luminosity<=1600){
            return new float[]{0.23F, 0.26F, 0.60F, alpha * (0.3F + 0.7F *(float)(luminosity-800)/800F)};
        }
        else if(luminosity<=3200){
            return new float[]{0.85F, 0.63F, 0.31F, alpha * (0.3F + 0.7F *(float)(luminosity-1600)/1600F)};
        }
        else{
            return new float[]{0.92F, 0.96F, 0.84F, alpha * (0.3F + 0.7F *(float)(luminosity-3200)/6400F)};
        }
    }
}
