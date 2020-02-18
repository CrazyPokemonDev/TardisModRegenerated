package de.crazypokemondev.tardismod.util.schematic;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;

import de.crazypokemondev.tardismod.util.helpers.CalculationHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
	private EnumFacing primaryDoorFacing;
	private static final String BOUNDS = "bounds";
	private int[] bounds;
	private static final int OPPOSITE_FACING = 0;
	private static final int NINETY_DEG_CCW_FACING = 1;
	private static final int SAME_FACING = 2;
	private static final int NINETY_DEG_CW_FACING = 3;
	private static final int UP_FACING = 4;

	public Schematic(BlockPos[] doors, SchematicBlockInfo[] storage, String name, BlockPos primaryDoor,
			EnumFacing primaryDoorFace, int[] bounds) {
		this.doors = doors;
		this.storage = storage;
		this.name = name;
		this.primaryDoor = primaryDoor;
		this.primaryDoorFacing = primaryDoorFace;
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

	public EnumFacing getPrimaryDoorFace() {
		return primaryDoorFacing;
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
		EnumFacing primaryDoorFace = CalculationHelper.getEnumFacingFromMeta(nbt.getInteger(PRIMARY_DOOR_FACE));
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

	public boolean hasEnoughSpace(World world, BlockPos clickedDoorPosition, EnumFacing clickedDoorFacing) {
		BlockPos corePosition = getCorePosition(clickedDoorPosition, clickedDoorFacing);
		for (int x = -bounds[OPPOSITE_FACING]; x <= bounds[SAME_FACING]; x++) {
			for (int y = 0; y <= bounds[UP_FACING]; y++) {
				for (int z = -bounds[NINETY_DEG_CCW_FACING]; z <= bounds[NINETY_DEG_CW_FACING]; z++) {
					BlockPos relativePosition = new BlockPos(x, y, z);
					if (!world.isAirBlock(getRotatedBlockPosition(clickedDoorFacing, corePosition, relativePosition))) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void pasteInWorld(World world, BlockPos clickedDoorPosition, EnumFacing clickedDoorFacing) {
		BlockPos corePosition = getCorePosition(clickedDoorPosition, clickedDoorFacing);
		for (SchematicBlockInfo block : storage) {
			world.setBlockState(getRotatedBlockPosition(clickedDoorFacing, corePosition, block.getPosition()),
					block.getBlockState(getRotation(clickedDoorFacing)));
		}
	}

	private BlockPos getRotatedBlockPosition(EnumFacing clickedDoorFacing, BlockPos corePosition,
			BlockPos relativePosition) {
		return corePosition.add(rotate(relativePosition, clickedDoorFacing));
	}

	private BlockPos getCorePosition(BlockPos clickedDoorPosition, EnumFacing clickedDoorFacing) {
		BlockPos corePosition = clickedDoorPosition.add(clickedDoorFacing.getDirectionVec())
				.subtract(inverse(rotate(primaryDoor, clickedDoorFacing)));
		return corePosition;
	}

	private BlockPos inverse(BlockPos position) {
		return new BlockPos(-position.getX(), -position.getY(), -position.getZ());
	}

	private BlockPos rotate(BlockPos position, EnumFacing clickedDoorFacing) {
		Rotation rotation = getRotation(clickedDoorFacing);
		return position.rotate(rotation);
	}

	private Rotation getRotation(EnumFacing clickedDoorFacing) {
		return CalculationHelper.getRotation(primaryDoorFacing, clickedDoorFacing);
	}
}
