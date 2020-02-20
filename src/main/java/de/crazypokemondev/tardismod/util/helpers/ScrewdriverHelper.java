package de.crazypokemondev.tardismod.util.helpers;

import de.crazypokemondev.tardismod.api.ScrewdriverMode;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.Constants.NBT;

public class ScrewdriverHelper {

	private static final String SCREWDRIVER_MODE = "screwdriverMode";
	private static final String SCREWDRIVER_TOOLTIP_DISMANTLE = "tardismod.screwdriver.tooltip.dismantle";
	private static final String SCREWDRIVER_TOOLTIP_LINK = "tardismod.screwdriver.tooltip.link";
	private static final String SCREWDRIVER_TOOLTIP_RECONFIGURE = "tardismod.screwdriver.tooltip.reconfigure";
	private static final String SCREWDRIVER_TOOLTIP_SCHEMATIC = "tardismod.screwdriver.tooltip.schematic";

	public static ScrewdriverMode getMode(ItemStack screwdriver) {
		NBTTagCompound nbt = getOrCreateTagCompound(screwdriver);
		ScrewdriverMode defaultMode = ScrewdriverMode.DEFAULT;
		if (!nbt.hasKey(SCREWDRIVER_MODE, NBT.TAG_STRING)) {
			setMode(screwdriver, defaultMode);
		}
		try {
			return Enum.valueOf(ScrewdriverMode.class, nbt.getString(SCREWDRIVER_MODE));
		} catch (IllegalArgumentException e) {
			setMode(screwdriver, defaultMode);
			return defaultMode;
		}
	}

	private static NBTTagCompound getOrCreateTagCompound(ItemStack screwdriver) {
		NBTTagCompound nbt = screwdriver.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
		}
		return nbt;
	}

	public static void setMode(ItemStack screwdriver, ScrewdriverMode mode) {
		NBTTagCompound nbt = getOrCreateTagCompound(screwdriver);
		nbt.setString(SCREWDRIVER_MODE, mode.getName());
		screwdriver.setTagCompound(nbt);
	}

	public static void cycleMode(ItemStack screwdriver) {
		ScrewdriverMode mode = getMode(screwdriver);
		ScrewdriverMode newMode = getNextMode(mode, screwdriver);
		setMode(screwdriver, newMode);
	}

	private static ScrewdriverMode getNextMode(ScrewdriverMode mode, ItemStack screwdriver) {
		switch (mode) {
			case RECONFIGURE:
				return ScrewdriverMode.DISMANTLE;
			case DISMANTLE:
				return ScrewdriverMode.LINK;
			case LINK:
				return ScrewdriverMode.SCHEMATIC;
			case SCHEMATIC:
				return ScrewdriverMode.RECONFIGURE;
			default:
				return ScrewdriverMode.DEFAULT;
		}
	}

	public static TextComponentTranslation getModeDescription(ItemStack screwdriver) {
		ScrewdriverMode mode = getMode(screwdriver);
		// TODO: extend description by additional information like selected schematic,
		// selected link position etc
		TextComponentTranslation translationComponent;
		Style style = new Style();
		switch (mode) {
			case DISMANTLE:
				translationComponent = new TextComponentTranslation(SCREWDRIVER_TOOLTIP_DISMANTLE);
				style.setColor(TextFormatting.RED);
				break;
			case LINK:
				translationComponent = new TextComponentTranslation(SCREWDRIVER_TOOLTIP_LINK);
				style.setColor(TextFormatting.DARK_RED);
				break;
			case RECONFIGURE:
				translationComponent = new TextComponentTranslation(SCREWDRIVER_TOOLTIP_RECONFIGURE);
				style.setColor(TextFormatting.DARK_BLUE);
				break;
			case SCHEMATIC:
				translationComponent = new TextComponentTranslation(SCREWDRIVER_TOOLTIP_SCHEMATIC);
				style.setColor(TextFormatting.YELLOW);
				break;
			default:
				translationComponent = new TextComponentTranslation("tardismod.screwdriver.tooltip.missing");
				style.setObfuscated(true);
				break;
		}
		translationComponent.setStyle(style);
		return translationComponent;
	}
}
