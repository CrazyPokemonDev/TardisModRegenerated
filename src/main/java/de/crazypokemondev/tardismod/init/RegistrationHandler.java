package de.crazypokemondev.tardismod.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.crazypokemondev.tardismod.TardisMod;
import de.crazypokemondev.tardismod.api.ISonicScrewdriverCapability;
import de.crazypokemondev.tardismod.api.ITardisIdentificationCapability;
import de.crazypokemondev.tardismod.block.BlockConsole;
import de.crazypokemondev.tardismod.block.BlockControlPanel;
import de.crazypokemondev.tardismod.block.BlockCorridorSlab;
import de.crazypokemondev.tardismod.block.BlockDoor;
import de.crazypokemondev.tardismod.block.BlockExit;
import de.crazypokemondev.tardismod.block.BlockRoundel;
import de.crazypokemondev.tardismod.block.BlockSchema;
import de.crazypokemondev.tardismod.block.BlockSolidForceField;
import de.crazypokemondev.tardismod.block.BlockSolidGlass;
import de.crazypokemondev.tardismod.block.BlockSolidGravityLift;
import de.crazypokemondev.tardismod.block.BlockTardis;
import de.crazypokemondev.tardismod.block.BlockTardisCore;
import de.crazypokemondev.tardismod.block.BlockTardisTop;
import de.crazypokemondev.tardismod.block.BlockTemporalEngine;
import de.crazypokemondev.tardismod.block.BlockTimeRotor;
import de.crazypokemondev.tardismod.block.TardisInternalBlock;
import de.crazypokemondev.tardismod.block.tileentities.TileEntityCore;
import de.crazypokemondev.tardismod.block.tileentities.TileEntityRoundel;
import de.crazypokemondev.tardismod.block.tileentities.TileEntityTardis;
import de.crazypokemondev.tardismod.client.models.ScrewdriverCustomMesh;
import de.crazypokemondev.tardismod.item.ItemSonicScrewdriver;
import de.crazypokemondev.tardismod.item.ItemTardisKey;
import de.crazypokemondev.tardismod.util.TardisModData;
import de.crazypokemondev.tardismod.util.capabilities.CapabilityMockStorage;
import de.crazypokemondev.tardismod.util.capabilities.SonicScrewdriverFactory;
import de.crazypokemondev.tardismod.util.capabilities.TardisIdentificationFactory;
import de.crazypokemondev.tardismod.util.capabilities.TardisIdentificationStorage;
import de.crazypokemondev.tardismod.worldgen.BiomeTardisInterior;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = TardisMod.MODID)
public final class RegistrationHandler {

	private static final ResourceLocation TARDIS_TILE_ENTITY = new ResourceLocation(TardisMod.MODID,
			"tardisTileEntity");
	private static final ResourceLocation CORE_TILE_ENTITY = new ResourceLocation(TardisMod.MODID, "coreTileEntity");
	private static final ResourceLocation ROUNDEL_TILE_ENTITY = new ResourceLocation(TardisMod.MODID,
			"roundelTileEntity");

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
				createBlock(new TardisInternalBlock(), "console_deco"), createBlock(new BlockTimeRotor(), "time_rotor"),
				createBlock(new BlockTardisCore(), "tardis_core"),
				createBlock(new BlockTemporalEngine(), "temporal_engine"),
				createBlock(new TardisInternalBlock(), "temporal_engine_deco"),
				createBlock(new BlockControlPanel(), "control_panel"), createBlock(new BlockSchema(), "schema"),
				createBlock(new BlockCorridorSlab.Half(), "corridor_slab_half"),
				createBlock(new BlockCorridorSlab.Double(), "corridor_slab_double") };

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
				createItemBlock(ModBlocks.CONTROL_PANEL), createItemBlock(ModBlocks.SCHEMA),
				createItemSlab(ModBlocks.CORRIDOR_SLAB_HALF, ModBlocks.CORRIDOR_SLAB_DOUBLE) };

		event.getRegistry().registerAll(items);
		event.getRegistry().registerAll(itemBlocks);

		// register inventory variant for obj models of items
		// registerInventoryVariant.add(sonicScrewdriver);
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

	private static ItemSlab createItemSlab(BlockSlab halfBlock, BlockSlab doubleBlock) {
		ItemSlab item = (ItemSlab) new ItemSlab(halfBlock, halfBlock, doubleBlock)
				.setRegistryName(halfBlock.getRegistryName());
		registerInventoryVariant.add(item);
		return item;
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void registerModels(ModelRegistryEvent event) {
		for (Item item : registerInventoryVariant) {
			ModelLoader.setCustomModelResourceLocation(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}

		ModelLoader.registerItemVariants(ModItems.SONIC_SCREWDRIVER, ScrewdriverCustomMesh.getItemVariants());
		ModelLoader.setCustomMeshDefinition(ModItems.SONIC_SCREWDRIVER, new ScrewdriverCustomMesh());
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
		CapabilityManager.INSTANCE.register(ISonicScrewdriverCapability.class,
				new CapabilityMockStorage<ISonicScrewdriverCapability>(), new SonicScrewdriverFactory());
	}

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityTardis.class, TARDIS_TILE_ENTITY);
		GameRegistry.registerTileEntity(TileEntityCore.class, CORE_TILE_ENTITY);
		GameRegistry.registerTileEntity(TileEntityRoundel.class, ROUNDEL_TILE_ENTITY);
	}

	public static void bindTileEntitySpecialRenderers() {

	}

	@SubscribeEvent
	public static void loadWorld(WorldEvent.Load event) {
		World world = event.getWorld();
		// register dimension for every TARDIS in this world
		if (!world.isRemote) {
			TardisMod.LOGGER.info("Loading TARDIS dimensions associated to world " + world.provider.getDimension());
			Set<Integer> dimIds = TardisModData.get(world).getAllLocations().keySet();
			for (int dimId : dimIds) {
				if (!DimensionManager.isDimensionRegistered(dimId)) {
					DimensionManager.registerDimension(dimId, ModWorldGen.TARDIS_DIM_TYPE);
				}
			}
		}
	}
}