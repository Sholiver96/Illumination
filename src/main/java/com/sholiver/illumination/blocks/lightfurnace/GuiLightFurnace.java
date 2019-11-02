package com.sholiver.illumination.blocks.lightfurnace;

import com.sholiver.illumination.Illumination;
import com.sholiver.illumination.blocks.lightmachine.GuiLightMachine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiLightFurnace extends GuiLightMachine<ContainerLightFurnace> {
    private static final ResourceLocation background = new ResourceLocation(Illumination.MODID, "textures/gui/light_furnace_gui.png");

    public GuiLightFurnace(IInventory inv, TileEntityLightFurnace te) {
        super(new ContainerLightFurnace(inv, te));
        this.te = te;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        drawTexturedModalRect(relX, relY, 0,0,this.xSize, this.ySize);
        int luminosityLevel = getLuminosityLevel();
        if(te.isBurning())
        {
            this.drawTexturedModalRect(relX + 56, relY + 36, 176 + 14 * luminosityLevel, 0, 14, 14);
        }
        int scaledCookTime = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(relX + 79, relY + 34, 176, 14, scaledCookTime + 1, 16);

        if(luminosityLevel > 0){
            int scaledLuminosity = this.getLuminosityScaled(52, luminosityLevel - 1);
            this.drawTexturedModalRect(relX + 21, relY + 17 + 52 - scaledLuminosity, 176 + (7 * (luminosityLevel - 1)), 31 + 52 - scaledLuminosity, 7, scaledLuminosity + 1);
        }
        int scaledLuminosity = this.getLuminosityScaled(52, luminosityLevel);
        this.drawTexturedModalRect(relX + 21, relY + 17 + 52 - scaledLuminosity, 176 + (7 * luminosityLevel), 31 + 52 - scaledLuminosity, 7, scaledLuminosity + 1);
    }
}
