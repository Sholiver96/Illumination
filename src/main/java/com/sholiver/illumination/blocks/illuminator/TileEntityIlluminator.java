package com.sholiver.illumination.blocks.illuminator;

import com.sholiver.illumination.blocks.lightmachine.TileEntityLightMachine;
import com.sholiver.illumination.recipe.IlluminatorRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class TileEntityIlluminator extends TileEntityLightMachine {

    public static final int slots = 4;

    public TileEntityIlluminator()
    {
        super();
        itemStackHandler = new ItemStackHandler(slots) {
            @Override
            protected void onContentsChanged(int slot) {
                TileEntityIlluminator.this.markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                super.isItemValid(slot, stack);
                switch(slot)
                {
                    case 0:
                        if(IlluminatorRecipes.instance().isValidInputSlot(stack)) {return true;}
                        return false;
                    case 1:
                        if(IlluminatorRecipes.instance().isValidDustSlot(stack)){return true;}
                        return false;
                    default: return false;
                }
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack) && slot < 2) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Override
    protected void processItems() {
        ItemStack slot1 = getStackInSlot(0);
        ItemStack slot2 = getStackInSlot(1);
        ItemStack result = IlluminatorRecipes.instance().getIlluminationResult(slot1, slot2, luminosity);
        itemStackHandler.extractItem(0,1,false);
        itemStackHandler.extractItem(1,1,false);
        itemStackHandler.insertItem(2, new ItemStack(result.getItem()), false);
    }

    @Override
    protected int getCookSpeed() {
        ItemStack slot1 = getStackInSlot(0);
        ItemStack slot2 = getStackInSlot(1);
        ItemStack result = IlluminatorRecipes.instance().getIlluminationResult(slot1, slot2, luminosity);
        return IlluminatorRecipes.instance().getCookSpeed(result, luminosity);
    }

    @Override
    protected boolean canProcess()
    {
        if(getStackInSlot(0).isEmpty() || getStackInSlot(1).isEmpty()){
            return false;
        }
        ItemStack  output = IlluminatorRecipes.instance().getIlluminationResult(getStackInSlot(0), getStackInSlot(1), luminosity);
        if(output.isEmpty()){
            return false;
        }
        ItemStack outSlot = getStackInSlot(2);
        if(output.isItemEqual(outSlot) || outSlot.isEmpty()){
            if(output.getCount() + outSlot.getCount() <= output.getMaxStackSize()) {
                return true;
            }
        }
        return false;
    }
}
