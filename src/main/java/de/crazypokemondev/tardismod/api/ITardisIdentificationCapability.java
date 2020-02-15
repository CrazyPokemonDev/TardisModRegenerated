package de.crazypokemondev.tardismod.api;

import javax.annotation.Nullable;

public interface ITardisIdentificationCapability {
	/**
	 * Whether the element that this capability is attached to has an assigned TARDIS or not
	 * @return True if it has, false if not
	 */
	boolean hasTardis();
	/**
	 * Returns the dimension ID linked to the attached element, or null if none is linked
	 * @return The dimension ID
	 */
	@Nullable
	Integer getTardisDimensionId();
	/**
	 * Sets the linked dimension ID to the given int value
	 * @param id The ID of the dimension to link
	 */
	void setTardisDimensionId(@Nullable Integer id);
}
