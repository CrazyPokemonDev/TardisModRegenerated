package de.crazypokemondev.tardismod.util.helpers;

import de.crazypokemondev.tardismod.init.ModWorldGen;
import net.minecraftforge.common.DimensionManager;

public class DimensionHelper {
	public static int createNewTardisDimension() {
		int dimId = DimensionManager.getNextFreeDimId();
		DimensionManager.registerDimension(dimId, ModWorldGen.TARDIS_DIM_TYPE);
		DimensionManager.initDimension(dimId);
		return dimId;
	}
}
