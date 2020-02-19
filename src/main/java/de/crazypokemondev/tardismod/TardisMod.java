package de.crazypokemondev.tardismod;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.crazypokemondev.tardismod.init.RegistrationHandler;
import de.crazypokemondev.tardismod.tabs.TardisModTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TardisMod.MODID, name = TardisMod.NAME, version = TardisMod.VERSION, acceptedMinecraftVersions = TardisMod.MC_VERSION)
public class TardisMod {
	public static File config;

	public static final String MODID = "tardismod";
	public static final String NAME = "Tardis Mod";
	public static final String VERSION = "0.0.1";
	public static final String MC_VERSION = "[1.12.2]";
	public static File SCHEMA_DIR;
	public static File CONFIG_DIR;

	public static final Logger LOGGER = LogManager.getLogger(TardisMod.MODID);

	public static final CreativeTabs CREATIVE_TAB = new TardisModTab();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// Create Schema directory for TARDIS interior
		CONFIG_DIR = new File(event.getModConfigurationDirectory(), "tardismod");
		CONFIG_DIR.mkdirs();
		SCHEMA_DIR = new File(event.getModConfigurationDirectory(), "tardismod_schemas/");
		SCHEMA_DIR.mkdirs();

		RegistrationHandler.registerCapabilities();
		RegistrationHandler.registerTileEntities();
		RegistrationHandler.bindTileEntitySpecialRenderers();

		// enable OBJ models for rendering
		OBJLoader.INSTANCE.addDomain(MODID);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}