package com.sholiver.illumination.blocks.solarlens;
import com.sholiver.illumination.blocks.lightlens.TileEntityLightLens;
import net.minecraft.world.EnumSkyBlock;

public class TileEntitySolarLens extends TileEntityLightLens {

    public TileEntitySolarLens(){super();}

    @Override
    public void update() {
        if(!this.world.isRemote)
        {
            if(luminosity != getLuminosityFromSource())
            {
                luminosity = getLuminosityFromSource();
                this.world.markAndNotifyBlock(pos, null, world.getBlockState(pos), world.getBlockState(pos), 2);
            }
        }
    }

    @Override
    public int getLuminosityFromSource() {
        if(world.getSkylightSubtracted() >= 10) return 0;
        else {
            int blockLight = world.getLightFor(EnumSkyBlock.SKY, this.getPos().up()) - 5 - world.getSkylightSubtracted();
            return Math.max(blockLight * maxLuminosity/10 , 0);
        }
    }
}
