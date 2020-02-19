package de.crazypokemondev.tardismod.block;

import de.crazypokemondev.tardismod.block.tileentities.TileEntityCore;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTardisCore extends TardisInternalBlock implements ITileEntityProvider {
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCore();
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return false;
	}
}
