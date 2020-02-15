package de.crazypokemondev.tardismod.util.schematic;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants.NBT;

public class Schematic {
	private static final String DOORS = "doors";
	private BlockPos[] doors;
	private static final String STORAGE = "storage";
	private SchematicBlockInfo[] storage;
	private static final String NAME = "name";
	private String name;
	private static final String PRIMARY_DOOR = "primaryDoor";
	private BlockPos primaryDoor;
	private static final String PRIMARY_DOOR_FACE = "primaryDoorFace";
	private int primaryDoorFace;
	private static final String BOUNDS = "bounds";
	private int[] bounds;

	public Schematic(BlockPos[] doors, SchematicBlockInfo[] storage, String name, BlockPos primaryDoor,
			int primaryDoorFace, int[] bounds) {
		this.doors = doors;
		this.storage = storage;
		this.name = name;
		this.primaryDoor = primaryDoor;
		this.primaryDoorFace = primaryDoorFace;
		this.bounds = bounds;
	}

	public BlockPos[] getDoors() {
		return doors;
	}

	public SchematicBlockInfo[] getStorage() {
		return storage;
	}

	public String getName() {
		return name;
	}

	public BlockPos getPrimaryDoor() {
		return primaryDoor;
	}

	public int getPrimaryDoorFace() {
		return primaryDoorFace;
	}

	public int[] getBounds() {
		return bounds;
	}

	public static Schematic loadFromNbt(NBTTagCompound nbt) throws InvalidFormatException {
		if (!nbt.hasKey(DOORS, NBT.TAG_COMPOUND) || !nbt.hasKey(STORAGE, NBT.TAG_COMPOUND)
				|| !nbt.hasKey(NAME, NBT.TAG_STRING) || !nbt.hasKey(PRIMARY_DOOR, NBT.TAG_STRING)
				|| !nbt.hasKey(PRIMARY_DOOR_FACE, NBT.TAG_INT) || !nbt.hasKey(BOUNDS, NBT.TAG_INT_ARRAY)) {
			throw new InvalidFormatException();
		}
		NBTTagCompound doorTag = nbt.getCompoundTag(DOORS);
		BlockPos[] doors = new BlockPos[doorTag.getKeySet().size()];
		int i = 0;
		for (String key : doorTag.getKeySet()) {
			doors[i++] = getBlockPos(doorTag.getString(key));
		}

		NBTTagCompound storageTag = nbt.getCompoundTag(STORAGE);
		SchematicBlockInfo[] storage = new SchematicBlockInfo[storageTag.getKeySet().size()];
		i = 0;
		for (String key : storageTag.getKeySet()) {
			storage[i++] = SchematicBlockInfo.loadFromNbt(getBlockPos(key), storageTag.getCompoundTag(key));
		}

		String name = nbt.getString(NAME);
		BlockPos primaryDoor = getBlockPos(nbt.getString(PRIMARY_DOOR));
		int primaryDoorFace = nbt.getInteger(PRIMARY_DOOR_FACE);
		int[] bounds = nbt.getIntArray(BOUNDS);

		return new Schematic(doors, storage, name, primaryDoor, primaryDoorFace, bounds);
	}

	private static BlockPos getBlockPos(String string) throws InvalidFormatException {
		String[] split = string.split(",");
		if (split.length < 3) {
			throw new InvalidFormatException();
		}
		try {
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			int z = Integer.parseInt(split[2]);
			return new BlockPos(x, y, z);
		} catch (NumberFormatException e) {
			throw new InvalidFormatException();
		}
	}
}
