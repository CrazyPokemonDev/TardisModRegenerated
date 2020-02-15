package de.crazypokemondev.tardismod.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IScrewable {
	/**
	 * Called when a player hits a block with a screwdriver (right click).
	 * @param player The player holding the screwdriver
	 * @param worldIn The world in which the block is
	 * @param pos The position of the block that was hit
	 * @param hand The hand in which the player is holding the screwdriver
     * @param side The side of the target hit
     * @param hand Which hand the item is being held in.
	 * @return Return PASS to allow handling of other onItemUseFirst methods, anything else will stop further handling.
	 */
	EnumActionResult screw(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ, ScrewdriverMode mode);
}
