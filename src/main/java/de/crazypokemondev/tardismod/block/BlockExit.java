package de.crazypokemondev.tardismod.block;

import de.crazypokemondev.tardismod.util.TardisLocation;
import de.crazypokemondev.tardismod.util.TardisModData;
import de.crazypokemondev.tardismod.util.helpers.MessageHelper;
import de.crazypokemondev.tardismod.util.helpers.Teleport;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockExit extends TardisInternalBlock {
	private static final String TARDIS_DOORS_LOCKED = "messages.tardismod.tardis_doors_locked";

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (hand == EnumHand.MAIN_HAND && !worldIn.isRemote) {
			int dimensionId = worldIn.provider.getDimension();
			TardisLocation location = TardisModData.get(worldIn).getLocation(dimensionId);
			if (location == null) {
				MessageHelper.sendLocalizedMessage(playerIn, worldIn, TARDIS_DOORS_LOCKED);
				return true;
			}
			Teleport.teleportToDimension(playerIn, location.getDimensionId(), location.getExitPosition(), location.getFacing(), 0);
			return true;
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
}
