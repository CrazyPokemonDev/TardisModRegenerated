package de.crazypokemondev.tardismod.worldgen;

import java.util.List;

import de.crazypokemondev.tardismod.block.BlockDoor;
import de.crazypokemondev.tardismod.init.ModBiomes;
import de.crazypokemondev.tardismod.init.ModBlocks;
import de.crazypokemondev.tardismod.util.handlers.TardisConfig;
import de.crazypokemondev.tardismod.util.helpers.SchematicHelper;
import de.crazypokemondev.tardismod.util.schematic.Schematic;
import de.crazypokemondev.tardismod.util.schematic.SchematicBlockInfo;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

public class ChunkGeneratorTardis implements IChunkGenerator {

	private ChunkPrimer chunkPrimer = new ChunkPrimer();
	private World world;
	private static final BlockPos CONSOLE_SCHEMA_CORE_POS = new BlockPos(0, 60, 0);

	public ChunkGeneratorTardis(World worldIn) {
		world = worldIn;
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		Chunk chunk = new Chunk(world, chunkPrimer, x, z);
		chunk.generateSkylightMap();
		byte[] abyte = chunk.getBiomeArray();

		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(ModBiomes.TARDIS);
		}
		return chunk;
	}

	@Override
	public void populate(int chunkX, int chunkZ) {
		if ((chunkX == -1 || chunkX == 0) && (chunkZ == -1 || chunkZ == 0)) {
			generateConsoleRoom(chunkX, chunkZ);
		}
	}

	private void generateConsoleRoom(int chunkX, int chunkZ) {
		Schematic consoleRoom = SchematicHelper.getSchematic(TardisConfig.DEFAULT_CONSOLE_ROOM);
		for (SchematicBlockInfo block : consoleRoom.getStorage()) {
			BlockPos pos = block.getPosition();
			if (pos.getX() >= (chunkX + 1) * 16 || pos.getX() < chunkX * 16 || pos.getZ() >= (chunkZ + 1) * 16
					|| pos.getZ() < chunkZ * 16) {
				continue;
			}
			world.setBlockState(CONSOLE_SCHEMA_CORE_POS.add(block.getPosition()), block.getBlockState());
		}
		BlockPos primaryDoorPos = CONSOLE_SCHEMA_CORE_POS.add(consoleRoom.getPrimaryDoor());

		if (primaryDoorPos.getX() < (chunkX + 1) * 16 && primaryDoorPos.getX() >= chunkX * 16
				&& primaryDoorPos.getZ() < (chunkZ + 1) * 16 && primaryDoorPos.getZ() >= chunkZ * 16) {
			world.setBlockState(primaryDoorPos, ModBlocks.DOOR.getDefaultState().withProperty(BlockDoor.PRIMARY, true));
		}

		if (chunkX == 0 && chunkZ == 0) {
			world.setBlockState(new BlockPos(0, 70, 0), ModBlocks.TARDIS_CORE.getDefaultState());
			world.setBlockState(new BlockPos(0, 68, 0), ModBlocks.CONSOLE.getDefaultState());
			world.setBlockState(new BlockPos(0, 65, 0), ModBlocks.TEMPORAL_ENGINE.getDefaultState());
		}
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return null;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {

	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}

}
