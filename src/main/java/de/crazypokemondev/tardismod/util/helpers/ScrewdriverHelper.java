package de.crazypokemondev.tardismod.util.helpers;

import de.crazypokemondev.tardismod.api.ScrewdriverMode;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants.NBT;

public class ScrewdriverHelper {

	private static final String SCREWDRIVER_MODE = "screwdriverMode";

	public static ScrewdriverMode getMode(ItemStack screwdriver) {
		NBTTagCompound nbt = screwdriver.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
		}
		ScrewdriverMode defaultMode = ScrewdriverMode.DEFAULT;
		if (!nbt.hasKey(SCREWDRIVER_MODE, NBT.TAG_STRING)) {
			nbt.setString(SCREWDRIVER_MODE, defaultMode.getName());
			screwdriver.setTagCompound(nbt);
		}
		try {
			return Enum.valueOf(ScrewdriverMode.class, nbt.getString(SCREWDRIVER_MODE));
		} catch (IllegalArgumentException e) {
			nbt.setString(SCREWDRIVER_MODE, defaultMode.getName());
			screwdriver.setTagCompound(nbt);
			return defaultMode;
		}
	}
	
}
