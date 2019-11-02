package com.sholiver.illumination.blocks.illuminator;

import com.sholiver.illumination.blocks.lightmachine.ContainerLightMachine;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;


public class ContainerIlluminator extends ContainerLightMachine {


    public ContainerIlluminator(IInventory inv, TileEntityIlluminator te) {
        super(inv, te);
    }

    @Override
    protected void addOwnSlots()
    {
        IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        addSlotToContainer(new SlotItemHandler(itemHandler, 0, 38, 35));
        addSlotToContainer(new SlotItemHandler(itemHandler, 1, 56, 35));
        addSlotToContainer(new SlotItemHandler(itemHandler, 2, 116, 35));
    }
}
