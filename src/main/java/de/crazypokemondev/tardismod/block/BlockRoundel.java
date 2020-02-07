package de.crazypokemondev.tardismod.block;

import de.crazypokemondev.tardismod.api.IScrewable;
import de.crazypokemondev.tardismod.api.ScrewdriverMode;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRoundel extends TardisInternalBlock implements IScrewable {

	public static final PropertyBool OPEN = PropertyBool.create("open");

	public BlockRoundel() {
		this.setDefaultState(this.blockState.getBaseState().withProperty(OPEN, false));
	}

	@Override
	public boolean screw(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX,
			float hitY, float hitZ, ScrewdriverMode mode) {
		IBlockState state = worldIn.getBlockState(pos);
		if (mode == ScrewdriverMode.RECONFIGURE && !state.getValue(OPEN)) {
			worldIn.setBlockState(pos, state.withProperty(OPEN, true));
			return true;
		} else if (mode == ScrewdriverMode.DISMANTLE && state.getValue(OPEN)) {
			worldIn.setBlockState(pos, state.withProperty(OPEN, false));
			return true;
		}
		return false;
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

}
