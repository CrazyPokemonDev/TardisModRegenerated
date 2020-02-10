package de.crazypokemondev.tardismod.item;

import de.crazypokemondev.tardismod.api.ITardisIdentificationCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class ItemTardisKey extends Item{
	public ItemTardisKey() {
		setMaxStackSize(1);
	}
	
	@CapabilityInject(ITardisIdentificationCapability.class)
	static Capability<ITardisIdentificationCapability> TARDIS_IDENTIFICATION_CAPABILITY = null;
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (handIn == EnumHand.MAIN_HAND) {
			ItemStack key = playerIn.getHeldItem(handIn);
			// TODO: check NBT data whether a player is already linked to this key
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
