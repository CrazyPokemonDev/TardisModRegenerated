package de.crazypokemondev.tardismod.util.helpers;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.MathHelper;

public class CalculationHelper {
	public static EnumFacing getDirectionFromYaw(float rotationYaw) {
		int direction = MathHelper.floor((double) ((rotationYaw * 4F) / 360F) + 0.5D) & 3;
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

	public static int getMetaFromEnumFacing(EnumFacing facing) {
		return facing.getHorizontalIndex() - 1;
	}

	public static EnumFacing getEnumFacingFromMeta(int meta) {
		return EnumFacing.getHorizontal((meta + 1) % 4);
	}

	public static Rotation getRotation(EnumFacing from, EnumFacing to) {
		int difference = from.getHorizontalIndex() - to.getHorizontalIndex();
		if (difference < 0) {
			difference += 4;
		}
		switch (difference) {
			case 0:
				return Rotation.NONE;
			case 1:
				return Rotation.COUNTERCLOCKWISE_90;
			case 2:
				return Rotation.CLOCKWISE_180;
			case 3:
				return Rotation.CLOCKWISE_90;
			default:
				throw new RuntimeException("Illegal facing supplied");
		}
	}
}
