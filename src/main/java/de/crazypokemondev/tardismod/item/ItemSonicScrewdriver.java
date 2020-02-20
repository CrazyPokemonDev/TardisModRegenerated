package de.crazypokemondev.tardismod.item;

import de.crazypokemondev.tardismod.api.ISonicScrewdriverCapability;
import de.crazypokemondev.tardismod.api.ScrewdriverMode;
import de.crazypokemondev.tardismod.util.helpers.MessageHelper;
import de.crazypokemondev.tardismod.util.helpers.ScrewdriverHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
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
		if (te == null || !te.hasCapability(SONIC_SCREWDRIVER_CAPABILITY, side)) {
			return EnumActionResult.PASS;
		} else {
			ISonicScrewdriverCapability cap = te.getCapability(SONIC_SCREWDRIVER_CAPABILITY, side);
			ItemStack screwdriver = player.getHeldItem(hand);
			ScrewdriverMode mode = ScrewdriverHelper.getMode(screwdriver);
			return cap.screw(player, world, pos, side, hitX, hitY, hitZ, hand, mode);
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (playerIn.isSneaking()) {
			ItemStack screwdriver = playerIn.getHeldItem(handIn);
			ScrewdriverHelper.cycleMode(screwdriver);
			MessageHelper.sendLocalizedToast(playerIn, worldIn, ScrewdriverHelper.getModeDescription(screwdriver));
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, screwdriver);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
