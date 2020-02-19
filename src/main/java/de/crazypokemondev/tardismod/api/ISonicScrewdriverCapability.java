package de.crazypokemondev.tardismod.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ISonicScrewdriverCapability {
	/**
	 * Executes an action (or doesn't) based on the screwdriver mode after a player
	 * used it on a block This is called in the onItemUseFirst method, so it will be
	 * called before any block activation events.
	 * 
	 * @param player  The player holding the screwdriver
	 * @param worldIn The world that the player is currently in
	 * @param pos     The block that was right clicked
	 * @param side    The side of the block that was clicked
	 * @param hitX    X coordinate to determine where exactly they clicked
	 * @param hitY    Y coordinate to determine where exactly they clicked
	 * @param hitZ    Z coordinate to determine where exactly they clicked
	 * @param hand    The hand in which the player is holding the screwdriver
	 * @param mode    The mode that the screwdriver is in
	 * @return PASS to allow minecraft to handle the click event further, anything
	 *         else to prevent that
	 */
	EnumActionResult screw(EntityPlayer player, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY,
			float hitZ, EnumHand hand, ScrewdriverMode mode);
}
