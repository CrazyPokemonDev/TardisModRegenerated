package de.crazypokemondev.tardismod.util.helpers;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

public class CalculationHelper {
	public static EnumFacing getDirectionFromYaw(float rotationYaw) {
		int direction = MathHelper.floor((double)((rotationYaw * 4F) / 360F) + 0.5D) & 3;
		switch (direction) {
		case 0:
			return EnumFacing.SOUTH;
		case 1:
			return EnumFacing.WEST;
		case 2:
			return EnumFacing.NORTH;
		case 3:
			return EnumFacing.EAST;
		default:
			throw new IllegalArgumentException("rotationYaw");
		}
	}
}
