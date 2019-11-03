package com.sholiver.illumination;

import com.sholiver.illumination.blocks.*;
import com.sholiver.illumination.blocks.illuminator.TileEntityIlluminatorRenderer;
import com.sholiver.illumination.blocks.solarlens.BlockSolarLens;
import com.sholiver.illumination.blocks.solarlens.TileEntitySolarLens;
import com.sholiver.illumination.blocks.solarlens.TileEntitySolarLensRenderer;
import com.sholiver.illumination.blocks.lightrelay.BlockLightRelay;
import com.sholiver.illumination.blocks.lightrelay.TileEntityLightRelay;
import com.sholiver.illumination.blocks.lightrelay.TileEntityLightRelayRenderer;
import com.sholiver.illumination.blocks.illuminator.BlockIlluminator;
import com.sholiver.illumination.blocks.illuminator.TileEntityIlluminator;
import com.sholiver.illumination.blocks.lightfurnace.BlockLightFurnace;
import com.sholiver.illumination.blocks.lightfurnace.TileEntityLightFurnace;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

import static com.sholiver.illumination.util.EnumTier.*;

public class ModBlocks {
    @ObjectHolder(Illumination.MODID + ":block_glimmering")
    public static BlockGlimmeringBlock BLOCK_GLIMMERING;
    @ObjectHolder(Illumination.MODID + ":block_luminous")
    public static BlockLuminousBlock BLOCK_LUMINOUS;
    @ObjectHolder(Illumination.MODID + ":block_radiant")
    public static BlockRadiantBlock BLOCK_RADIANT;
    @ObjectHolder(Illumination.MODID + ":ore_glimmering")
    public static BlockGlimmeringOre ORE_GLIMMERING;
    @ObjectHolder(Illumination.MODID + ":ore_luminous")
    public static BlockLuminousOre ORE_LUMINOUS;
    @ObjectHolder(Illumination.MODID + ":ore_radiant")
    public static BlockRadiantOre ORE_RADIANT;

    @ObjectHolder(Illumination.MODID + ":illuminator")
    public static BlockIlluminator ILLUMINATOR;
    @ObjectHolder(Illumination.MODID + ":illuminator_glimmering")
    public static BlockIlluminator ILLUMINATOR_GLIMMERING;
    @ObjectHolder(Illumination.MODID + ":illuminator_luminous")
    public static BlockIlluminator ILLUMINATOR_LUMINOUS;
    @ObjectHolder(Illumination.MODID + ":illuminator_radiant")
    public static BlockIlluminator ILLUMINATOR_RADIANT;

    @ObjectHolder(Illumination.MODID + ":light_furnace")
    public static BlockLightFurnace LIGHT_FURNACE;
    @ObjectHolder(Illumination.MODID + ":light_furnace_glimmering")
    public static BlockLightFurnace LIGHT_FURNACE_GLIMMERING;
    @ObjectHolder(Illumination.MODID + ":light_furnace_luminous")
    public static BlockLightFurnace LIGHT_FURNACE_LUMINOUS;
    @ObjectHolder(Illumination.MODID + ":light_furnace_radiant")
    public static BlockLightFurnace LIGHT_FURNACE_RADIANT;

    @ObjectHolder(Illumination.MODID + ":solar_lens")
    public static BlockSolarLens SOLAR_LENS;
    @ObjectHolder(Illumination.MODID + ":solar_lens_glimmering")
    public static BlockSolarLens SOLAR_LENS_GLIMMERING;
    @ObjectHolder(Illumination.MODID + ":solar_lens_luminous")
    public static BlockSolarLens SOLAR_LENS_LUMINOUS;
    @ObjectHolder(Illumination.MODID + ":solar_lens_radiant")
    public static BlockSolarLens SOLAR_LENS_RADIANT;

    @ObjectHolder(Illumination.MODID + ":light_relay")
    public static BlockLightRelay LIGHT_RELAY;


    @Mod.EventBusSubscriber(modid = Illumination.MODID)
    public static class Registration
    {
        @SubscribeEvent
        public static void registerBlocks (RegistryEvent.Register<Block> event)
        {
            IForgeRegistry<Block> registry = event.getRegistry();

            registry.register(new BlockGlimmeringOre());
            registry.register(new BlockLuminousOre());
            registry.register(new BlockRadiantOre());
            registry.register(new BlockGlimmeringBlock());
            registry.register(new BlockLuminousBlock());
            registry.register(new BlockRadiantBlock());

            GameRegistry.registerTileEntity(TileEntityIlluminator.class, "illumination:illuminator");
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIlluminator.class, new TileEntityIlluminatorRenderer());
            registry.register(new BlockIlluminator("illuminator", BASIC));
            registry.register(new BlockIlluminator("illuminator_glimmering", GLIMMERING));
            registry.register(new BlockIlluminator("illuminator_luminous", LUMINOUS));
            registry.register(new BlockIlluminator("illuminator_radiant", RADIANT));

            GameRegistry.registerTileEntity(TileEntityLightFurnace.class, "illumination:light_furnace");
            registry.register(new BlockLightFurnace("light_furnace", BASIC));
            registry.register(new BlockLightFurnace("light_furnace_glimmering", GLIMMERING));
            registry.register(new BlockLightFurnace("light_furnace_luminous", LUMINOUS));
            registry.register(new BlockLightFurnace("light_furnace_radiant", RADIANT));

            GameRegistry.registerTileEntity(TileEntitySolarLens.class, "illumination:solar_lens");
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySolarLens.class, new TileEntitySolarLensRenderer());
            registry.register(new BlockSolarLens("solar_lens", BASIC));
            registry.register(new BlockSolarLens("solar_lens_glimmering", GLIMMERING));
            registry.register(new BlockSolarLens("solar_lens_luminous", LUMINOUS));
            registry.register(new BlockSolarLens("solar_lens_radiant", RADIANT));

            GameRegistry.registerTileEntity(TileEntityLightRelay.class, "illumination:light_relay");
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLightRelay.class, new TileEntityLightRelayRenderer());
            registry.register(new BlockLightRelay("light_relay", BASIC));
        }
        @SubscribeEvent
        public static void registerItems (RegistryEvent.Register<Item> event)
        {
            IForgeRegistry<Item> registry = event.getRegistry();

            registry.register(new ItemBlock(ModBlocks.ORE_GLIMMERING).setRegistryName("ore_glimmering"));
            registry.register(new ItemBlock(ModBlocks.ORE_LUMINOUS).setRegistryName("ore_luminous"));
            registry.register(new ItemBlock(ModBlocks.ORE_RADIANT).setRegistryName("ore_radiant"));
            registry.register(new ItemBlock(ModBlocks.BLOCK_GLIMMERING).setRegistryName("block_glimmering"));
            registry.register(new ItemBlock(ModBlocks.BLOCK_LUMINOUS).setRegistryName("block_luminous"));
            registry.register(new ItemBlock(ModBlocks.BLOCK_RADIANT).setRegistryName("block_radiant"));

            registry.register(new ItemBlock(ModBlocks.ILLUMINATOR).setRegistryName("illuminator"));
            registry.register(new ItemBlock(ModBlocks.ILLUMINATOR_GLIMMERING).setRegistryName("illuminator_glimmering"));
            registry.register(new ItemBlock(ModBlocks.ILLUMINATOR_LUMINOUS).setRegistryName("illuminator_luminous"));
            registry.register(new ItemBlock(ModBlocks.ILLUMINATOR_RADIANT).setRegistryName("illuminator_radiant"));

            registry.register(new ItemBlock(ModBlocks.LIGHT_FURNACE).setRegistryName("light_furnace"));
            registry.register(new ItemBlock(ModBlocks.LIGHT_FURNACE_GLIMMERING).setRegistryName("light_furnace_glimmering"));
            registry.register(new ItemBlock(ModBlocks.LIGHT_FURNACE_LUMINOUS).setRegistryName("light_furnace_luminous"));
            registry.register(new ItemBlock(ModBlocks.LIGHT_FURNACE_RADIANT).setRegistryName("light_furnace_radiant"));

            registry.register(new ItemBlock(ModBlocks.SOLAR_LENS).setRegistryName("solar_lens"));
            registry.register(new ItemBlock(ModBlocks.SOLAR_LENS_GLIMMERING).setRegistryName("solar_lens_glimmering"));
            registry.register(new ItemBlock(ModBlocks.SOLAR_LENS_LUMINOUS).setRegistryName("solar_lens_luminous"));
            registry.register(new ItemBlock(ModBlocks.SOLAR_LENS_RADIANT).setRegistryName("solar_lens_radiant"));

            registry.register(new ItemBlock(ModBlocks.LIGHT_RELAY).setRegistryName("light_relay"));
        }
    }
}
