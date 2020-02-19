package de.crazypokemondev.tardismod.util.helpers;

import de.crazypokemondev.tardismod.init.ModBlocks;
import de.crazypokemondev.tardismod.init.ModWorldGen;
import de.crazypokemondev.tardismod.util.TardisLocation;
import de.crazypokemondev.tardismod.util.TardisModData;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class TardisHelper {

	public static void moveTardisTo(World worldIn, BlockPos position, int tardisDimensionId, EnumFacing facing) {
		worldIn.setBlockState(position,
				ModBlocks.TARDIS.getDefaultState().withProperty(BlockHorizontal.FACING, facing));
		TardisModData.get(worldIn).setLocation(tardisDimensionId,
				new TardisLocation(worldIn.provider.getDimension(), position, facing));
	}

	public static int generateNewTardisDim() {
		int newDimId = DimensionManager.getNextFreeDimId();
		DimensionManager.registerDimension(newDimId, ModWorldGen.TARDIS_DIM_TYPE);
		return newDimId;
	}
}
