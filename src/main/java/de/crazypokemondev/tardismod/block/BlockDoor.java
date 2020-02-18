package de.crazypokemondev.tardismod.block;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

public class BlockDoor extends TardisInternalBlock {
	public static PropertyBool PRIMARY = PropertyBool.create("primary");
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockDoor() {
		this.setDefaultState(
				this.blockState.getBaseState().withProperty(PRIMARY, false).withProperty(FACING, EnumFacing.WEST));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, PRIMARY, FACING);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = state.getValue(FACING).getHorizontalIndex() - 1;
		if (meta < 0) {
			meta += 4;
		}
		if (state.getValue(PRIMARY)) {
			meta += 4;
		}
		return meta;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(PRIMARY, meta > 3).withProperty(FACING,
				EnumFacing.getHorizontal((meta + 1) % 4));
	}
}
