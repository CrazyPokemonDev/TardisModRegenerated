package de.crazypokemondev.tardismod.worldgen;

import de.crazypokemondev.tardismod.init.ModBiomes;
import de.crazypokemondev.tardismod.init.ModWorldGen;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldTypeTardis extends WorldType {

	public WorldTypeTardis() {
		super(ModWorldGen.TARDIS_NAME);
	}

	@Override
	public BiomeProvider getBiomeProvider(World world) {
		return new BiomeProviderSingle(ModBiomes.TARDIS);
	}

	@Override
	public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
		return new ChunkGeneratorTardis(world);
	}

	@Override
	public boolean canBeCreated() {
		return false;
	}
}
