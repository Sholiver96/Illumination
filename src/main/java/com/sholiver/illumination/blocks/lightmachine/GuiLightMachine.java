package com.sholiver.illumination.blocks.lightmachine;

import net.minecraft.client.gui.inventory.GuiContainer;

public abstract class GuiLightMachine<T extends ContainerLightMachine> extends GuiContainer {

    protected TileEntityLightMachine te;

    public GuiLightMachine(T container) {
        super(container);
    }

    protected int getCookProgressScaled(int scale) {
        int cookTime = this.te.getField(1);
        return cookTime != 0 ? cookTime * scale / te.totalCookTime : 0;
    }

    protected int getLuminosityScaled(int scale, int level) {
        int luminosity = this.te.getField(0);
        switch(level){
            case 0: return luminosity != 0 ? Math.min(scale, (luminosity * scale) / 800) : 0;
            case 1: return luminosity > 800 ? Math.min(scale, ((luminosity - 800) * scale) / 800) : 0;
            case 2: return luminosity > 1600 ? Math.min(scale, ((luminosity - 1600) * scale) / 1600) : 0;
            case 3: return luminosity > 3200 ? Math.min(scale, ((luminosity - 3200) * scale) / 3200) : 0;
            default: return 0;
        }
    }

    protected int getLuminosityLevel()
    {
        int luminosity = te.getField(0);
        if(luminosity<=800) return 0;
        else if(luminosity<=1600) return 1;
        else if(luminosity<=3200) return 2;
        else return 3;
    }
}
