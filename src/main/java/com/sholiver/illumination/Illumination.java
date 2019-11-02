package com.sholiver.illumination;

import com.sholiver.illumination.client.GuiProxy;
import com.sholiver.illumination.tabs.IlluminationTab;
import com.sholiver.illumination.world.ModWorldGen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = Illumination.MODID, name = Illumination.NAME, version = Illumination.VERSION)
public class Illumination
{
    public static final CreativeTabs ILLUMINATION_TAB = new IlluminationTab();

    public static final String MODID = "illumination";
    public static final String NAME = "Illumination";
    public static final String VERSION = "1.0";

    @Mod.Instance
    public static Illumination instance;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiProxy());
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
