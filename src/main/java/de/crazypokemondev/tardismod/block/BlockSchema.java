package de.crazypokemondev.tardismod.block;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSchema extends TardisInternalBlock {
	public static final PropertyBool CORE = PropertyBool.create("core");
	
	public BlockSchema() {
		this.setDefaultState(this.blockState.getBaseState().withProperty(CORE, false));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(CORE) ? 1 : 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(CORE, meta == 1);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CORE);
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager) {
		// this prevents vanilla particle effects from happening
		return true;
	}
}
