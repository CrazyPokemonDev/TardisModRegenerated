package de.crazypokemondev.tardismod.util.capabilities;

import de.crazypokemondev.tardismod.api.ISonicScrewdriverCapability;
import de.crazypokemondev.tardismod.api.ScrewdriverMode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SonicScrewdriverMockCapability implements ISonicScrewdriverCapability {

	@Override
	public EnumActionResult screw(EntityPlayer player, World worldIn, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand, ScrewdriverMode mode) {
		return EnumActionResult.PASS;
	}

}
