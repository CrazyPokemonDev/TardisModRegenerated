package de.crazypokemondev.tardismod.worldgen;

import de.crazypokemondev.tardismod.init.ModWorldGen;
import net.minecraft.world.biome.Biome;

public class BiomeTardisInterior extends Biome {

	public BiomeTardisInterior() {
		super(new BiomeProperties(ModWorldGen.TARDIS_NAME).setBaseHeight(0.0F).setHeightVariation(0.0F)
				.setRainDisabled().setTemperature(21.0F));
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();
	}
}
