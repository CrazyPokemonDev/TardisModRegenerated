package de.crazypokemondev.tardismod.util;

import java.util.HashMap;
import java.util.Map;

import de.crazypokemondev.tardismod.TardisMod;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class TardisModData extends WorldSavedData {

	private static final String DATA_NAME = TardisMod.MODID + "_ModData";

	private static final String LOCATIONS = "locations";

	private static final String X = "x";

	private static final String Y = "y";

	private static final String Z = "z";

	private static final String DIMENSION = "dimension";

	private Map<Integer, TardisLocation> tardises = new HashMap<Integer, TardisLocation>();

	public TardisModData() {
		super(DATA_NAME);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		NBTTagCompound locations = nbt.getCompoundTag(LOCATIONS);
		for (String key : locations.getKeySet()) {
			int dimensionId = Integer.parseInt(key);
			NBTTagCompound locationTag = locations.getCompoundTag(key);
			TardisLocation location = getLocationFromTag(locationTag);
			tardises.put(dimensionId, location);
		}
	}

	private TardisLocation getLocationFromTag(NBTTagCompound position) {
		return new TardisLocation(position.getInteger(DIMENSION),
				new BlockPos(position.getInteger(X), position.getInteger(Y), position.getInteger(Z)));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagCompound locations = new NBTTagCompound();
		for (Integer dimensionId : tardises.keySet()) {
			TardisLocation location = tardises.get(dimensionId);
			NBTTagCompound position = getTagCompoundFromLocation(location);
			locations.setTag(dimensionId.toString(), position);
		}
		compound.setTag(LOCATIONS, locations);
		return compound;
	}

	private NBTTagCompound getTagCompoundFromLocation(TardisLocation location) {
		NBTTagCompound tagCompound = new NBTTagCompound();
		tagCompound.setInteger(X, location.getPosition().getX());
		tagCompound.setInteger(Y, location.getPosition().getY());
		tagCompound.setInteger(Z, location.getPosition().getZ());
		tagCompound.setInteger(DIMENSION, location.getDimensionId());
		return tagCompound;
	}

	public static TardisModData get(World world) {
		MapStorage storage = world.getMapStorage();
		TardisModData instance = (TardisModData) storage.getOrLoadData(TardisModData.class, DATA_NAME);

		if (instance == null) {
			instance = new TardisModData();
			storage.setData(DATA_NAME, instance);
		}
		return instance;
	}

	public void setLocation(int tardisDimensionId, TardisLocation location) {
		tardises.put(tardisDimensionId, location);
		markDirty();
	}

	public TardisLocation getLocation(int dimensionId) {
		return tardises.get(dimensionId);
	}

	public boolean exists(int dimensionId) {
		return tardises.containsKey(dimensionId);
	}

	public Map<Integer, TardisLocation> getAllLocations() {
		Map<Integer, TardisLocation> map = new HashMap<Integer, TardisLocation>();
		map.putAll(tardises);
		return map;
	}
}
