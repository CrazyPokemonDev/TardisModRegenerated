package de.crazypokemondev.tardismod.util.helpers;

import de.crazypokemondev.tardismod.api.ITardisLocationCapability;
import de.crazypokemondev.tardismod.init.ModBlocks;
import de.crazypokemondev.tardismod.init.ModWorldGen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class TardisHelper {
	
	public static void moveTardisTo(World worldIn, BlockPos position, int tardisDimensionId, ITardisLocationCapability cap) {
		worldIn.setBlockState(position, ModBlocks.TARDIS.getDefaultState());
		cap.setLocation(tardisDimensionId, position);
	}

	public static int generateNewTardisDim() {
		int newDimId = DimensionManager.getNextFreeDimId();
		DimensionManager.registerDimension(newDimId, ModWorldGen.TARDIS_DIM_TYPE);
		return newDimId;
	}
}
