package com.sholiver.illumination;

import com.sholiver.illumination.Illumination;
import com.sholiver.illumination.items.ItemLens;
import com.sholiver.illumination.items.ItemLightCoal;
import com.sholiver.illumination.items.ItemLightDust;
import com.sholiver.illumination.items.ItemLightIngot;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    @ObjectHolder(Illumination.MODID + ":ingot_glimmering")
    public static ItemLightIngot INGOT_GLIMMERING;
    @ObjectHolder(Illumination.MODID + ":ingot_luminous")
    public static ItemLightIngot INGOT_LUMINOUS;
    @ObjectHolder(Illumination.MODID + ":ingot_radiant")
    public static ItemLightIngot INGOT_RADIANT;

    @ObjectHolder(Illumination.MODID + ":dust_glimmering")
    public static ItemLightDust DUST_GLIMMERING;
    @ObjectHolder(Illumination.MODID + ":dust_luminous")
    public static ItemLightDust DUST_LUMINOUS;
    @ObjectHolder(Illumination.MODID + ":dust_radiant")
    public static ItemLightDust DUST_RADIANT;

    @ObjectHolder(Illumination.MODID + ":coal_glimmering")
    public static ItemLightCoal COAL_GLIMMERING;
    @ObjectHolder(Illumination.MODID + ":coal_luminous")
    public static ItemLightCoal COAL_LUMINOUS;
    @ObjectHolder(Illumination.MODID + ":coal_radiant")
    public static ItemLightCoal COAL_RADIANT;

    @ObjectHolder(Illumination.MODID + ":lens")
    public static ItemLens LENS;

    @Mod.EventBusSubscriber(modid = Illumination.MODID)
    public static class Registration
    {
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event)
        {
            IForgeRegistry<Item> registry = event.getRegistry();
            registry.register(new ItemLightIngot("ingot_glimmering"));
            registry.register(new ItemLightIngot("ingot_luminous"));
            registry.register(new ItemLightIngot("ingot_radiant"));

            registry.register(new ItemLightDust("dust_glimmering"));
            registry.register(new ItemLightDust("dust_luminous"));
            registry.register(new ItemLightDust("dust_radiant"));

            registry.register(new ItemLightCoal("coal_glimmering", 2400));
            registry.register(new ItemLightCoal("coal_luminous", 3200));
            registry.register(new ItemLightCoal("coal_radiant", 4000));

            registry.register(new ItemLens("lens"));
        }
    }
}
