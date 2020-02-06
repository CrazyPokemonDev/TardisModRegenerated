package de.crazypokemondev.tardismod.block;

import de.crazypokemondev.tardismod.block.base.AbstractTardisPartBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTardisTop extends AbstractTardisPartBlock {
	private static final AxisAlignedBB TARDIS_AABB_TOP = new AxisAlignedBB(0.0D, -1.0D, 0.0D, 1.0D, 1.0D, 1.0D);

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return TARDIS_AABB_TOP;
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		if (worldIn.getBlockState(pos.down()).getBlock() instanceof BlockTardis) {
			worldIn.destroyBlock(pos.down(), false);
		}
		super.onBlockDestroyedByPlayer(worldIn, pos, state);
	}

}
