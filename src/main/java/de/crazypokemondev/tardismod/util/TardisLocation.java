package de.crazypokemondev.tardismod.util;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TardisLocation {
	private int dimensionId;

	private BlockPos position;
	private EnumFacing facing;

	public TardisLocation(int dimensionId, BlockPos position, EnumFacing facing) {
		this.dimensionId = dimensionId;
		this.position = position;
		this.setFacing(facing);
	}

	public TardisLocation(World worldIn, BlockPos pos, EnumFacing facing) {
		this(worldIn.provider.getDimension(), pos, facing);
	}

	public int getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(int dimensionId) {
		this.dimensionId = dimensionId;
	}

	public BlockPos getExitPosition() {
		return getPosition().add(getFacing().getDirectionVec());
	}
	
	public BlockPos getPosition() {
		return position;
	}

	public void setPosition(BlockPos position) {
		this.position = position;
	}

	public EnumFacing getFacing() {
		return facing;
	}

	public void setFacing(EnumFacing facing) {
		this.facing = facing;
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
