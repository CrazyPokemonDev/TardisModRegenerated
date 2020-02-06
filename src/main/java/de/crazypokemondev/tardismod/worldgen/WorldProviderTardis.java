package de.crazypokemondev.tardismod.worldgen;

import de.crazypokemondev.tardismod.init.ModWorldGen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldProviderTardis extends WorldProvider {

	@Override
	public DimensionType getDimensionType() {
		return ModWorldGen.TARDIS_DIM_TYPE;
	}

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}
	
	@Override
	public boolean canDoLightning(Chunk chunk) {
		return false;
	}
	
	@Override
	public boolean canDoRainSnowIce(Chunk chunk) {
		return false;
	}
	
	@Override
	public boolean canSnowAt(BlockPos pos, boolean checkLight) {
		return false;
	}
	
	@Override
	public boolean canRespawnHere() {
		return true;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorTardis(world);
	}
}
