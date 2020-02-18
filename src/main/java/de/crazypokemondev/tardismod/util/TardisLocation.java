package de.crazypokemondev.tardismod.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TardisLocation {
	private int dimensionId;

	private BlockPos position;

	public TardisLocation(int dimensionId, BlockPos position) {
		this.dimensionId = dimensionId;
		this.position = position;
	}

	public TardisLocation(World worldIn, BlockPos pos) {
		this(worldIn.provider.getDimension(), pos);
	}

	public int getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(int dimensionId) {
		this.dimensionId = dimensionId;
	}

	public BlockPos getPosition() {
		return position;
	}

	public void setPosition(BlockPos position) {
		this.position = position;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TardisLocation) {
			TardisLocation loc = (TardisLocation) obj;
			return loc.dimensionId == dimensionId && loc.position.equals(position);
		}
		return super.equals(obj);
	}
}
