package com.sholiver.illumination.client;

import com.sholiver.illumination.Illumination;
import com.sholiver.illumination.ModBlocks;
import com.sholiver.illumination.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Illumination.MODID)
public class ModelRegistrationHandler
{
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        registerModel(ModItems.DUST_GLIMMERING, 0);
        registerModel(ModItems.DUST_LUMINOUS, 0);
        registerModel(ModItems.DUST_RADIANT, 0);

        registerModel(ModItems.INGOT_GLIMMERING, 0);
        registerModel(ModItems.INGOT_LUMINOUS, 0);
        registerModel(ModItems.INGOT_RADIANT, 0);

        registerModel(ModItems.COAL_GLIMMERING, 0);
        registerModel(ModItems.COAL_LUMINOUS, 0);
        registerModel(ModItems.COAL_RADIANT, 0);

        registerModel(ModItems.LENS, 0);

        registerModel(Item.getItemFromBlock(ModBlocks.BLOCK_GLIMMERING),0);
        registerModel(Item.getItemFromBlock(ModBlocks.BLOCK_LUMINOUS),0);
        registerModel(Item.getItemFromBlock(ModBlocks.BLOCK_RADIANT),0);
        
        registerModel(Item.getItemFromBlock(ModBlocks.ORE_GLIMMERING),0);
        registerModel(Item.getItemFromBlock(ModBlocks.ORE_LUMINOUS),0);
        registerModel(Item.getItemFromBlock(ModBlocks.ORE_RADIANT),0);

        registerModel(Item.getItemFromBlock(ModBlocks.ILLUMINATOR),0);
        registerModel(Item.getItemFromBlock(ModBlocks.ILLUMINATOR_GLIMMERING),0);
        registerModel(Item.getItemFromBlock(ModBlocks.ILLUMINATOR_LUMINOUS),0);
        registerModel(Item.getItemFromBlock(ModBlocks.ILLUMINATOR_RADIANT),0);

        registerModel(Item.getItemFromBlock(ModBlocks.LIGHT_FURNACE),0);

        registerModel(Item.getItemFromBlock(ModBlocks.SOLAR_LENS),0);

        registerModel(Item.getItemFromBlock(ModBlocks.LIGHT_RELAY),0);
    }

    private static void registerModel(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
