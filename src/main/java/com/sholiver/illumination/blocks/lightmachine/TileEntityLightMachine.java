package com.sholiver.illumination.blocks.lightmachine;

import com.sholiver.illumination.blocks.lightreceiver.TileEntityLightReceiver;
import com.sholiver.illumination.util.EnumTier;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class TileEntityLightMachine extends TileEntityLightReceiver {

    protected int cookTime;
    protected int totalCookTime = 400;
    protected ItemStackHandler itemStackHandler;

    public TileEntityLightMachine(){
        super();
    }

    @Override
    public void update() {
        boolean flag1 = false;
        boolean flag = this.isBurning();
        if(!this.world.isRemote){
            if(this.world.getWorldTime() % 20L == 0) {
                if(luminosity != Math.min(tier.getMachineLuminosity(), getLuminosityFromNearbyLenses())) luminosity = Math.min(tier.getMachineLuminosity(), getLuminosityFromNearbyLenses());
                this.world.markAndNotifyBlock(pos, null, world.getBlockState(pos), world.getBlockState(pos), 2);
            }
            if(this.isBurning() && this.canProcess()) {
                cookTime += getCookSpeed();
                if(cookTime >= totalCookTime) {
                    cookTime = cookTime - totalCookTime;
                    processItems();
                    flag1 = true;
                }
            }
            if((!this.canProcess() || !this.isBurning()) && cookTime > 0){
                cookTime = Math.max(0, cookTime-4);
            }
            if (flag != this.isBurning()) {
                flag1 = true;
            }
        }
        if(flag1){
            markDirty();
        }
    }

    protected abstract int getCookSpeed();
    protected abstract boolean canProcess();
    protected abstract void processItems();

    public ItemStack getStackInSlot(int slot)
    {
        return itemStackHandler.getStackInSlot(slot);
    }

    public int getField(int index) {
        switch (index) {
            case 0:
                return this.luminosity;
            case 1:
                return this.cookTime;
            case 2:
                return this.tier.getIndex();
            default:
                return 0;
        }
    }

    public void setField(int index, int value){
        switch (index) {
            case 0:
                this.luminosity = value;
                return;
            case 1:
                this.cookTime = value;
                return;
            case 2:
                this.tier = EnumTier.fromIndex(value);
                return;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("items")) {
            this.itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
        this.cookTime = compound.getInteger("CookTime");
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("items", itemStackHandler.serializeNBT());
        compound.setInteger("CookTime", this.cookTime);
        return compound;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
        }
        return super.getCapability(capability, facing);
    }
}
