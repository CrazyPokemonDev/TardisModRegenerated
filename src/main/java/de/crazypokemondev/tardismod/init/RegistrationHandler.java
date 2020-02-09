package de.crazypokemondev.tardismod.init;

import java.util.ArrayList;
import java.util.List;

import de.crazypokemondev.tardismod.TardisMod;
import de.crazypokemondev.tardismod.api.ITardisIdentificationCapability;
import de.crazypokemondev.tardismod.block.BlockConsole;
import de.crazypokemondev.tardismod.block.BlockControlPanel;
import de.crazypokemondev.tardismod.block.BlockDoor;
import de.crazypokemondev.tardismod.block.BlockExit;
import de.crazypokemondev.tardismod.block.BlockRoundel;
import de.crazypokemondev.tardismod.block.BlockSchemaCore;
import de.crazypokemondev.tardismod.block.BlockSolidForceField;
import de.crazypokemondev.tardismod.block.BlockSolidGlass;
import de.crazypokemondev.tardismod.block.BlockSolidGravityLift;
import de.crazypokemondev.tardismod.block.BlockTardis;
import de.crazypokemondev.tardismod.block.BlockTardisCore;
import de.crazypokemondev.tardismod.block.BlockTardisTop;
import de.crazypokemondev.tardismod.block.BlockTemporalEngine;
import de.crazypokemondev.tardismod.block.BlockTimeRotor;
import de.crazypokemondev.tardismod.block.TardisInternalBlock;
import de.crazypokemondev.tardismod.item.ItemSonicScrewdriver;
import de.crazypokemondev.tardismod.item.ItemTardisKey;
import de.crazypokemondev.tardismod.util.capabilities.TardisIdentificationFactory;
import de.crazypokemondev.tardismod.util.capabilities.TardisIdentificationStorage;
import de.crazypokemondev.tardismod.worldgen.BiomeTardisInterior;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = TardisMod.MODID)
public final class RegistrationHandler {

	private static List<Item> registerInventoryVariant = new ArrayList<Item>();

	@SubscribeEvent
	public static void registerBlocks(Register<Block> event) {
		TardisMod.LOGGER.info("Registering blocks");
		final Block[] blocks = { createBlock(new BlockTardis(), "tardis"),
				createBlock(new BlockTardisTop(), "tardis_top"), createBlock(new TardisInternalBlock(), "solid_block"),
				createBlock(new TardisInternalBlock(), "flat_block"), createBlock(new BlockSolidGlass(), "solid_glass"),
				createBlock(new BlockRoundel(), "roundel"), createBlock(new BlockDoor(), "door"),
				createBlock(new BlockSolidForceField(), "solid_force_field"),
				createBlock(new BlockSolidGravityLift(), "solid_gravity_lift"),
				createBlock(new TardisInternalBlock(), "door_connector"), createBlock(new BlockExit(), "exit_top"),
				createBlock(new BlockExit(), "exit_bottom"), createBlock(new BlockConsole(), "console"),
				createBlock(new BlockTimeRotor(), "time_rotor"), createBlock(new BlockTardisCore(), "tardis_core"),
				createBlock(new BlockTemporalEngine(), "temporal_engine"),
				createBlock(new BlockControlPanel(), "control_panel"),
				createBlock(new BlockSchemaCore(), "schema_core") };

		event.getRegistry().registerAll(blocks);
	}

	private static Block createBlock(Block block, String name) {
		return block.setRegistryName(TardisMod.MODID, name).setUnlocalizedName(TardisMod.MODID + "." + name)
				.setCreativeTab(TardisMod.CREATIVE_TAB);
	}

	@SubscribeEvent
	public static void registerItems(Register<Item> event) {
		TardisMod.LOGGER.info("Registering items");
		Item sonicScrewdriver = createItem(new ItemSonicScrewdriver(), "sonic_screwdriver");
		final Item[] items = { createItem(new ItemTardisKey(), "tardis_key"), createItem(new Item(), "kontron_crystal"),
				sonicScrewdriver };
		final Item[] itemBlocks = { createItemBlock(ModBlocks.SOLID_BLOCK), createItemBlock(ModBlocks.FLAT_BLOCK),
				createItemBlock(ModBlocks.SOLID_GLASS), createItemBlock(ModBlocks.ROUNDEL),
				createItemBlock(ModBlocks.DOOR), createItemBlock(ModBlocks.SOLID_FORCE_FIELD),
				createItemBlock(ModBlocks.SOLID_GRAVITY_LIFT), createItemBlock(ModBlocks.DOOR_CONNECTOR),
				createItemBlock(ModBlocks.EXIT_TOP), createItemBlock(ModBlocks.EXIT_BOTTOM),
				createItemBlock(ModBlocks.CONTROL_PANEL), createItemBlock(ModBlocks.SCHEMA_CORE) };

		event.getRegistry().registerAll(items);
		event.getRegistry().registerAll(itemBlocks);

		// register inventory variant for obj models of items
		registerInventoryVariant.add(sonicScrewdriver);
	}

	private static Item createItem(Item item, String name) {
		return item.setRegistryName(TardisMod.MODID, name).setUnlocalizedName(TardisMod.MODID + "." + name)
				.setCreativeTab(TardisMod.CREATIVE_TAB);
	}

	private static Item createItemBlock(final Block block) {
		Item item = new ItemBlock(block).setRegistryName(block.getRegistryName());
		registerInventoryVariant.add(item);
		return item;
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for (Item item : registerInventoryVariant) {
			ModelLoader.setCustomModelResourceLocation(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}

	@SubscribeEvent
	public static void registerBiomes(Register<Biome> event) {
		final IForgeRegistry<Biome> registry = event.getRegistry();

		TardisMod.LOGGER.info("Registering biomes");

		registry.register(new BiomeTardisInterior().setRegistryName(TardisMod.MODID, ModWorldGen.TARDIS_NAME));
	}

	public static void registerCapabilities() {
		CapabilityManager.INSTANCE.register(ITardisIdentificationCapability.class, new TardisIdentificationStorage(),
				new TardisIdentificationFactory());
	}
}