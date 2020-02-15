package de.crazypokemondev.tardismod.util.handlers;

import de.crazypokemondev.tardismod.TardisMod;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Configuration;

@Config(modid = TardisMod.MODID)
public class TardisConfig extends Configuration {

	// Dimension
	@RangeInt(max = 500, min = -500)
	@Comment("The ID for the TARDIS dimension type")
	public static int TARDIS_DIMENSION_TYPE_ID = 12;
	@Comment("Whether the TARDIS' dimension should be kept loaded")
	public static boolean KEEP_DIMENSION_LOADED = true;
	@Comment("The name of the default console room schematic.")
	public static String DEFAULT_CONSOLE_ROOM = "tardisConsoleMain";
}
