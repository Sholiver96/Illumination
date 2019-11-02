package com.sholiver.illumination.blocks.lightfurnace;

import com.sholiver.illumination.blocks.lightmachine.ContainerLightMachine;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerLightFurnace extends ContainerLightMachine {

    public ContainerLightFurnace(IInventory inv, TileEntityLightFurnace te)
    {
        super(inv, te);
    }

    @Override
    protected void addOwnSlots()
    {
        IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        addSlotToContainer(new SlotItemHandler(itemHandler, 0, 56, 35));
        addSlotToContainer(new SlotItemHandler(itemHandler, 1, 116, 35));
    }
}
