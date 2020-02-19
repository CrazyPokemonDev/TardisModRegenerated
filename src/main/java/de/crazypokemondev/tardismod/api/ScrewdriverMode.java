package de.crazypokemondev.tardismod.api;

import net.minecraft.util.IStringSerializable;

public enum ScrewdriverMode implements IStringSerializable {
	SCHEMATIC,
	RECONFIGURE,
	DISMANTLE,
	LINK;
	
	public static final ScrewdriverMode DEFAULT = RECONFIGURE;
	
	public static ScrewdriverMode getDefault() {
		return RECONFIGURE;
	}

	@Override
	public String getName() {
		return this.name();
	}
}
