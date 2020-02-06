package de.crazypokemondev.tardismod.worldgen;

import java.util.List;

import de.crazypokemondev.tardismod.init.ModBiomes;
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

	public ChunkGeneratorTardis(World worldIn) {
		world = worldIn;
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		Chunk chunk = new Chunk(world, chunkPrimer, x, z);
		chunk.generateSkylightMap();
		byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i)
        {
            abyte[i] = (byte) Biome.getIdForBiome(ModBiomes.TARDIS);
        }
		return chunk;
	}

	@Override
	public void populate(int chunkX, int chunkZ) {
		
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
