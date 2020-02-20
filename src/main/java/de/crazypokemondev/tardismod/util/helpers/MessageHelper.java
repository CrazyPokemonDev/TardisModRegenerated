package de.crazypokemondev.tardismod.util.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class MessageHelper {

	public static void sendLocalizedMessage(EntityPlayer playerIn, World worldIn, String messageTextKey,
			Object... args) {
		if (!worldIn.isRemote) {
			playerIn.sendMessage(new TextComponentTranslation(messageTextKey, args));
		}
	}

	public static void sendLocalizedToast(EntityPlayer playerIn, World worldIn,
			TextComponentTranslation text) {
		if (!worldIn.isRemote) {
			playerIn.sendStatusMessage(text, true);
		}
	}

}
