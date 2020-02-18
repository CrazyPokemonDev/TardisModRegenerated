package de.crazypokemondev.tardismod.block;

import de.crazypokemondev.tardismod.util.helpers.CalculationHelper;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

public class BlockDoor extends TardisInternalBlock {
	public static PropertyBool PRIMARY = PropertyBool.create("primary");

	public BlockDoor() {
		this.setDefaultState(this.blockState.getBaseState().withProperty(PRIMARY, false)
				.withProperty(BlockHorizontal.FACING, EnumFacing.WEST));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, PRIMARY, BlockHorizontal.FACING);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = CalculationHelper.getMetaFromEnumFacing(state.getValue(BlockHorizontal.FACING));
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
		return getDefaultState().withProperty(PRIMARY, meta > 3).withProperty(BlockHorizontal.FACING,
				CalculationHelper.getEnumFacingFromMeta(meta));
	}
}
