package de.crazypokemondev.tardismod.block;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class BlockDoor extends TardisInternalBlock {
	public static PropertyBool PRIMARY = PropertyBool.create("primary");
	
	public BlockDoor() {
		this.setDefaultState(this.blockState.getBaseState().withProperty(PRIMARY, false));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, PRIMARY);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(PRIMARY) ? 1 : 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(PRIMARY, meta == 1);
	}
}
