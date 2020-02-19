package de.crazypokemondev.tardismod.block;

import de.crazypokemondev.tardismod.block.tileentities.TileEntityRoundel;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRoundel extends TardisInternalBlock implements ITileEntityProvider {

	public static final PropertyBool OPEN = PropertyBool.create("open");

	public BlockRoundel() {
		this.setDefaultState(this.blockState.getBaseState().withProperty(OPEN, false));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, OPEN);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(OPEN) ? 1 : 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(OPEN, meta == 1);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityRoundel();
	}

}
