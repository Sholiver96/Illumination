package com.sholiver.illumination;

import com.sholiver.illumination.items.*;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    @ObjectHolder(Illumination.MODID + ":ingot_glimmering")
    public static ItemGlimmeringIngot INGOT_GLIMMERING;
    @ObjectHolder(Illumination.MODID + ":ingot_luminous")
    public static ItemLuminousIngot INGOT_LUMINOUS;
    @ObjectHolder(Illumination.MODID + ":ingot_radiant")
    public static ItemRadiantIngot INGOT_RADIANT;

    @ObjectHolder(Illumination.MODID + ":dust_glimmering")
    public static ItemGlimmeringDust DUST_GLIMMERING;
    @ObjectHolder(Illumination.MODID + ":dust_luminous")
    public static ItemLuminousDust DUST_LUMINOUS;
    @ObjectHolder(Illumination.MODID + ":dust_radiant")
    public static ItemRadiantDust DUST_RADIANT;

    @ObjectHolder(Illumination.MODID + ":coal_glimmering")
    public static ItemGlimmeringCoal COAL_GLIMMERING;
    @ObjectHolder(Illumination.MODID + ":coal_luminous")
    public static ItemLuminousCoal COAL_LUMINOUS;
    @ObjectHolder(Illumination.MODID + ":coal_radiant")
    public static ItemRadiantCoal COAL_RADIANT;

    @ObjectHolder(Illumination.MODID + ":lens")
    public static ItemLens LENS;
    @ObjectHolder(Illumination.MODID + ":lens_glimmering")
    public static ItemGlimmeringLens LENS_GLIMMERING;
    @ObjectHolder(Illumination.MODID + ":lens_luminous")
    public static ItemLuminousLens LENS_LUMINOUS;
    @ObjectHolder(Illumination.MODID + ":lens_radiant")
    public static ItemRadiantLens LENS_RADIANT;

    @Mod.EventBusSubscriber(modid = Illumination.MODID)
    public static class Registration
    {
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event)
        {
            IForgeRegistry<Item> registry = event.getRegistry();
            registry.register(new ItemGlimmeringIngot());
            registry.register(new ItemLuminousIngot());
            registry.register(new ItemRadiantIngot());

            registry.register(new ItemGlimmeringDust());
            registry.register(new ItemLuminousDust());
            registry.register(new ItemRadiantDust());

            registry.register(new ItemGlimmeringCoal());
            registry.register(new ItemLuminousCoal());
            registry.register(new ItemRadiantCoal());

            registry.register(new ItemLens());
            registry.register(new ItemGlimmeringLens());
            registry.register(new ItemLuminousLens());
            registry.register(new ItemRadiantLens());
        }
    }
}
