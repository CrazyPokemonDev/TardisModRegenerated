package de.crazypokemondev.tardismod.block;

import de.crazypokemondev.tardismod.block.base.AbstractTardisPartBlock;
import de.crazypokemondev.tardismod.block.tileentities.TileEntityTardis;
import de.crazypokemondev.tardismod.init.ModBlocks;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTardis extends AbstractTardisPartBlock implements ITileEntityProvider {
	private static final AxisAlignedBB TARDIS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D);
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return TARDIS_AABB;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTardis();
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		System.out.println(ModBlocks.TARDIS_TOP);
		worldIn.setBlockState(pos.up(), ModBlocks.TARDIS_TOP.getDefaultState().withProperty(FACING, state.getValue(FACING)));
		super.onBlockAdded(worldIn, pos, state);
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		if (worldIn.getBlockState(pos.up()).getBlock() instanceof BlockTardisTop) {
			worldIn.destroyBlock(pos.up(), false);
		}
		super.onBlockDestroyedByPlayer(worldIn, pos, state);
	}
}
