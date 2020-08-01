package com.sebasphere.udaexpansions;

import org.apache.logging.log4j.Logger;

import com.sebasphere.udaexpansions.commands.CommandGravityChanger;
import com.sebasphere.udaexpansions.common.GravityChanger;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(
        modid = UDAExpansions.MOD_ID,
        name = UDAExpansions.MOD_NAME,
        version = UDAExpansions.VERSION,
        acceptedMinecraftVersions = UDAExpansions.ACCEPTED_MC_VERSIONS,
        dependencies = UDAExpansions.DEPENDENCIES_MODS,
        modLanguage = "java"
)
public class UDAExpansions {

    public static final String MOD_ID = "udaexpansions";
    public static final String MOD_NAME = "UDAExpansions";
    public static final String VERSION = "1.0.2-ALPHA";
	public static final String ACCEPTED_MC_VERSIONS = "[1.12.2]";
	public static final String ACCEPTED_MC_VERSION  = ForgeVersion.mcVersion;
	public static final String DEPENDENCIES_MODS    = "required-after:mysttmtgravitymod@[2.9.1,)";


    /** This is the instance of your mod as created by Forge. It will never be null. */
    @Mod.Instance(MOD_ID)
    public static UDAExpansions INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    private static Logger logger;
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        logger.info("Want a break from the ads???");
       MinecraftForge.EVENT_BUS.register(new GravityChanger());

    }
    
    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandGravityChanger());
    }

    /**
     * Forge will automatically look up and bind blocks to the fields in this class
     * based on their registry name.
     */
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Blocks {

    }

    /**
     * Forge will automatically look up and bind items to the fields in this class
     * based on their registry name.
     */
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Items {

    }

    /**
     * This is a special class that listens to registry events, to allow creation of mod blocks and items at the proper time.
     */
    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler {
       /** Listen for the register event for creating custom items */
       @SubscribeEvent
       public static void addItems(RegistryEvent.Register<Item> event) {

       }
       /** Listen for the register event for creating custom blocks */
       @SubscribeEvent
       public static void addBlocks(RegistryEvent.Register<Block> event) {

       }
    }
}
