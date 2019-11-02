package com.sholiver.illumination.blocks.lightmachine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ContainerLightMachine extends Container {

    protected TileEntityLightMachine te;

    protected int lastCookTime = 0;
    protected int lastMaxLuminosity = 0;

    public ContainerLightMachine(IInventory inv, TileEntityLightMachine te)
    {
        this.te = te;
        addOwnSlots();
        addPlayerSlots(inv, 8, 84);
    }

    protected abstract void addOwnSlots();

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        int slots = te.itemStackHandler.getSlots();
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < slots) {
                if (!this.mergeItemStack(itemstack1, slots, 36 + slots, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, slots-1, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }

    @Override
    protected boolean mergeItemStack(ItemStack p_mergeItemStack_1_, int p_mergeItemStack_2_, int p_mergeItemStack_3_, boolean p_mergeItemStack_4_) {
        boolean flag = false;
        int i = p_mergeItemStack_2_;
        if (p_mergeItemStack_4_) {
            i = p_mergeItemStack_3_ - 1;
        }

        Slot slot1;
        ItemStack itemstack;
        if (p_mergeItemStack_1_.isStackable()) {
            while(!p_mergeItemStack_1_.isEmpty()) {
                if (p_mergeItemStack_4_) {
                    if (i < p_mergeItemStack_2_) {
                        break;
                    }
                } else if (i >= p_mergeItemStack_3_) {
                    break;
                }

                slot1 = (Slot)this.inventorySlots.get(i);
                itemstack = slot1.getStack();
                if (!itemstack.isEmpty() && itemstack.getItem() == p_mergeItemStack_1_.getItem() && (!p_mergeItemStack_1_.getHasSubtypes() || p_mergeItemStack_1_.getMetadata() == itemstack.getMetadata()) && ItemStack.areItemStackTagsEqual(p_mergeItemStack_1_, itemstack)) {
                    int j = itemstack.getCount() + p_mergeItemStack_1_.getCount();
                    int maxSize = Math.min(slot1.getSlotStackLimit(), p_mergeItemStack_1_.getMaxStackSize());
                    if (j <= maxSize) {
                        p_mergeItemStack_1_.setCount(0);
                        itemstack.setCount(j);
                        slot1.onSlotChanged();
                        flag = true;
                    } else if (itemstack.getCount() < maxSize) {
                        p_mergeItemStack_1_.shrink(maxSize - itemstack.getCount());
                        itemstack.setCount(maxSize);
                        slot1.onSlotChanged();
                        flag = true;
                    }
                }

                if (p_mergeItemStack_4_) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        if (!p_mergeItemStack_1_.isEmpty()) {
            if (p_mergeItemStack_4_) {
                i = p_mergeItemStack_3_ - 1;
            } else {
                i = p_mergeItemStack_2_;
            }

            while(true) {
                if (p_mergeItemStack_4_) {
                    if (i < p_mergeItemStack_2_) {
                        break;
                    }
                } else if (i >= p_mergeItemStack_3_) {
                    break;
                }

                slot1 = (Slot)this.inventorySlots.get(i);
                itemstack = slot1.getStack();
                if (itemstack.isEmpty() && slot1.isItemValid(p_mergeItemStack_1_)) {
                    if (p_mergeItemStack_1_.getCount() > slot1.getSlotStackLimit()) {
                        slot1.putStack(p_mergeItemStack_1_.splitStack(slot1.getSlotStackLimit()));
                    } else {
                        slot1.putStack(p_mergeItemStack_1_.splitStack(p_mergeItemStack_1_.getCount()));
                    }

                    slot1.onSlotChanged();
                    flag = true;
                    break;
                }

                if (p_mergeItemStack_4_) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        return flag;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return te.canInteractWith(entityPlayer);
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for(IContainerListener listener : listeners)
        {
            listener.sendWindowProperty(this, 0, te.getField(0));
            if(lastCookTime != te.getField(1)) {
                listener.sendWindowProperty(this, 1, te.getField(1));
            }
            if(lastMaxLuminosity != te.getField(2)) {
                listener.sendWindowProperty(this, 2, te.getField(2));
            }
        }
        lastCookTime = te.getField(1);
        lastMaxLuminosity = te.getField(2);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int i, int j) {
        te.setField(i, j);
    }

    //Methods for bulk adding slots
    protected int addSlotRange(IInventory inv, int index, int x, int y, int amount, int dx)
    {
        for(int i = 0; i<amount; i++)
        {
            addSlotToContainer(new Slot(inv, index, x,y));
            x += dx;
            index++;
        }
        return index;
    }

    protected int addSlotBox(IInventory inv, int index, int x, int y, int horAmount, int dx, int verAmount, int dy)
    {
        for(int j = 0; j < verAmount; j++)
        {
            index = addSlotRange(inv, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    protected void addPlayerSlots(IInventory inv, int leftCol, int topRow)
    {
        addSlotBox(inv, 9, leftCol, topRow, 9,18,3,18);
        topRow += 58;
        addSlotRange(inv, 0, leftCol, topRow, 9, 18);
    }
}
