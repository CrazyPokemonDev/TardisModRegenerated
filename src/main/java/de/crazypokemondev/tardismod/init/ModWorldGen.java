package de.crazypokemondev.tardismod.init;

import de.crazypokemondev.tardismod.util.handlers.TardisConfig;
import de.crazypokemondev.tardismod.worldgen.WorldProviderTardis;
import de.crazypokemondev.tardismod.worldgen.WorldTypeTardis;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldType;

public class ModWorldGen {
	public static final String TARDIS_NAME = "Tardis";
	public static final DimensionType TARDIS_DIM_TYPE = DimensionType.register(TARDIS_NAME, "_" + TARDIS_NAME, TardisConfig.TARDIS_DIMENSION_TYPE_ID, WorldProviderTardis.class, TardisConfig.KEEP_DIMENSION_LOADED);
	public static final WorldType TARDIS_WORLD_TYPE = new WorldTypeTardis();
}
