package com.sholiver.illumination.blocks.lightfurnace;

import com.sholiver.illumination.blocks.lightmachine.TileEntityLightMachine;
import com.sholiver.illumination.recipe.LightFurnaceRecipes;
import com.sholiver.illumination.util.EnumTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class TileEntityLightFurnace extends TileEntityLightMachine {

    public  TileEntityLightFurnace(EnumTier tier){
        this();
        this.tier = tier;
    }

    public TileEntityLightFurnace(){
        super();
        itemStackHandler = new ItemStackHandler(2)
        {
            @Override
            protected void onContentsChanged(int slot) {
                TileEntityLightFurnace.this.markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                super.isItemValid(slot, stack);
                switch(slot)
                {
                    case 0:
                        if(!FurnaceRecipes.instance().getSmeltingResult(stack).isEmpty()
                                || !LightFurnaceRecipes.instance().getLightSmeltingResult(stack, 6400).isEmpty()) {return true;}
                        return false;
                    default: return false;
                }
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack) && slot < 1) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    protected int getCookSpeed() {
        ItemStack slot1 = getStackInSlot(0);
        ItemStack result = LightFurnaceRecipes.instance().getLightSmeltingResult(slot1, luminosity);
        if(result.isEmpty()) return luminosity / 100;
        return LightFurnaceRecipes.instance().getCookSpeed(result, luminosity);
    }

    protected boolean canProcess() {
        if(getStackInSlot(0).isEmpty()){
            return false;
        }
        ItemStack smeltOutput = FurnaceRecipes.instance().getSmeltingResult(getStackInSlot(0));
        ItemStack lightSmeltOutput = LightFurnaceRecipes.instance().getLightSmeltingResult(getStackInSlot(0), luminosity);
        if(smeltOutput.isEmpty() && lightSmeltOutput.isEmpty()) {
            return false;
        }
        ItemStack outSlot = getStackInSlot(1);
        if(smeltOutput.isItemEqual(outSlot) || lightSmeltOutput.isItemEqual(outSlot) || outSlot.isEmpty()){
            if(smeltOutput.getCount() + outSlot.getCount() <= smeltOutput.getMaxStackSize()
                    || lightSmeltOutput.getCount() + outSlot.getCount() <= lightSmeltOutput.getMaxStackSize()) {
                return true;
            }
        }
        return false;
    }

    protected void processItems() {
        ItemStack slot1 = getStackInSlot(0);
        ItemStack smeltingResult = FurnaceRecipes.instance().getSmeltingResult(slot1);
        ItemStack lightSmeltingResult = LightFurnaceRecipes.instance().getLightSmeltingResult(slot1, luminosity);
        itemStackHandler.extractItem(0, 1,false);
        if(lightSmeltingResult.isEmpty()){
            itemStackHandler.insertItem(1, new ItemStack(smeltingResult.getItem()), false);
        }
        else{
            itemStackHandler.insertItem(1, new ItemStack(lightSmeltingResult.getItem()), false);
        }
    }
}
