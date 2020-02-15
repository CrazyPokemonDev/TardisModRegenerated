package de.crazypokemondev.tardismod.util.capabilities;

import java.util.HashMap;
import java.util.Map;

import de.crazypokemondev.tardismod.api.ITardisLocationCapability;
import net.minecraft.util.math.BlockPos;

public class TardisLocationCapability implements ITardisLocationCapability {
	Map<Integer, BlockPos> tardisLocations = new HashMap<>();

	@Override
	public boolean isMaterialized(int tardisDimensionId) {
		return tardisLocations.containsKey(tardisDimensionId) && tardisLocations.get(tardisDimensionId) != null;
	}

	@Override
	public BlockPos getLocation(int tardisDimensionId) {
		return tardisLocations.get(tardisDimensionId);
	}

	@Override
	public void setLocation(int tardisDimensionId, BlockPos position) {
		if (position != null)
			tardisLocations.put(tardisDimensionId, position);
	}

	@Override
	public Map<Integer, BlockPos> getAllLocations() {
		Map<Integer, BlockPos> result = new HashMap<Integer, BlockPos>();
		result.putAll(tardisLocations);
		return result;
	}

}
