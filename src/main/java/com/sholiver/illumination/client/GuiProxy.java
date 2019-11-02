package com.sholiver.illumination.client;

import com.sholiver.illumination.blocks.illuminator.ContainerIlluminator;
import com.sholiver.illumination.blocks.illuminator.GuiIlluminator;
import com.sholiver.illumination.blocks.illuminator.TileEntityIlluminator;
import com.sholiver.illumination.blocks.lightfurnace.ContainerLightFurnace;
import com.sholiver.illumination.blocks.lightfurnace.GuiLightFurnace;
import com.sholiver.illumination.blocks.lightfurnace.TileEntityLightFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityIlluminator) {
            return new ContainerIlluminator(player.inventory, (TileEntityIlluminator) te);
        }
        if (te instanceof TileEntityLightFurnace) {
            return new ContainerLightFurnace(player.inventory, (TileEntityLightFurnace) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityIlluminator) {
            TileEntityIlluminator containerTileEntity = (TileEntityIlluminator) te;
            return new GuiIlluminator(player.inventory, containerTileEntity);
        }
        if (te instanceof TileEntityLightFurnace) {
            TileEntityLightFurnace containerTileEntity = (TileEntityLightFurnace) te;
            return new GuiLightFurnace(player.inventory, containerTileEntity);
        }
        return null;
    }

}
