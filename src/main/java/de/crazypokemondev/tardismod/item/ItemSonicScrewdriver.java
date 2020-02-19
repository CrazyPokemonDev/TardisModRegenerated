package de.crazypokemondev.tardismod.item;

import de.crazypokemondev.tardismod.api.ISonicScrewdriverCapability;
import de.crazypokemondev.tardismod.api.ScrewdriverMode;
import de.crazypokemondev.tardismod.util.helpers.ScrewdriverHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class ItemSonicScrewdriver extends Item {
	@CapabilityInject(ISonicScrewdriverCapability.class)
	static Capability<ISonicScrewdriverCapability> SONIC_SCREWDRIVER_CAPABILITY = null;

	public ItemSonicScrewdriver() {
		setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		TileEntity te = world.getTileEntity(pos);
		EnumActionResult result;
		if (te == null || !te.hasCapability(SONIC_SCREWDRIVER_CAPABILITY, side)) {
			result = EnumActionResult.PASS;
		} else {
			ISonicScrewdriverCapability cap = te.getCapability(SONIC_SCREWDRIVER_CAPABILITY, side);
			ItemStack screwdriver = player.getHeldItem(hand);
			ScrewdriverMode mode = ScrewdriverHelper.getMode(screwdriver);
			result = cap.screw(player, world, pos, side, hitX, hitY, hitZ, hand, mode);
		}
		// TODO: if result is PASS, check for whether the player is sneaking, and if
		// they are, cycle modes
		return result;
	}
}
