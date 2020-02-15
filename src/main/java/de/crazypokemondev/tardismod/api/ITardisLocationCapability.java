package de.crazypokemondev.tardismod.api;

import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;

public interface ITardisLocationCapability {
	/**
	 * Whether the TARDIS is materialized in the world that this capability is
	 * attached to.
	 * 
	 * @param tardisDimensionId The ID of the dimension linked to the TARDIS.
	 * @return True if there is a materialized TARDIS
	 */
	boolean isMaterialized(int tardisDimensionId);

	/**
	 * Gets the position where the specified TARDIS is materialized, or null if it
	 * isn't (in this world).
	 * 
	 * @param tardisDimensionId The ID of the dimension linked to the TARDIS.
	 * @return The position of the materialized TARDIS
	 */
	@Nullable
	BlockPos getLocation(int tardisDimensionId);

	/**
	 * Sets the location of the TARDIS linked to the given dimension to the given
	 * BlockPosition (only saving data, doesn't actually move the TARDIS!)
	 * 
	 * @param tardisDimensionId The ID of the dimension linked to the TARDIS
	 * @param position The position to save for lookup
	 */
	void setLocation(int tardisDimensionId, BlockPos position);
	
	/**
	 * Get all the locations of materialized TARDISes in this world.
	 * @return
	 */
	Map<Integer, BlockPos> getAllLocations();
}
