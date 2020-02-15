package de.crazypokemondev.tardismod.util.schematic;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;

import de.crazypokemondev.tardismod.TardisMod;
import de.crazypokemondev.tardismod.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants.NBT;

public class SchematicBlockInfo {
	private static final String BLOCK_META = "tdSchemaBMD";
	private static final String BLOCK_NAME = "tdSchemaBName";
	private BlockPos position;
	private IBlockState blockState;

	public SchematicBlockInfo(BlockPos pos, IBlockState state) {
		position = pos;
		blockState = state;
	}

	public BlockPos getPosition() {
		return position;
	}

	public IBlockState getBlockState() {
		return blockState;
	}

	public static SchematicBlockInfo loadFromNbt(BlockPos blockPos, NBTTagCompound nbt) throws InvalidFormatException {
		if (!nbt.hasKey(BLOCK_META, NBT.TAG_INT) || !nbt.hasKey(BLOCK_NAME, NBT.TAG_STRING)) {
			throw new InvalidFormatException();
		}
		IBlockState state;
		int meta = nbt.getInteger(BLOCK_META);
		String name = nbt.getString(BLOCK_NAME);
		state = getBlockStateFrom(meta, name);
		return new SchematicBlockInfo(blockPos, state);
	}

	public static IBlockState getBlockStateFrom(int meta, String name) throws InvalidFormatException {
		switch (name) {
			case "TardisMod:tile.TardisMod.Schema":
				return ModBlocks.SCHEMA.getStateFromMeta(0);
			case "TardisMod:tile.TardisMod.SchemaCore":
				return ModBlocks.SCHEMA.getStateFromMeta(1);
			case "TardisMod:tile.TardisMod.ColorableFloor":
				return ModBlocks.FLAT_BLOCK.getDefaultState();
			case "TardisMod:tile.TardisMod.ColorableWall":
				return ModBlocks.SOLID_BLOCK.getDefaultState();
			case "TardisMod:tile.TardisMod.ColorableRoundel":
				return ModBlocks.ROUNDEL.getStateFromMeta(meta);
			case "TardisMod:tile.TardisMod.DecoGlass":
				return ModBlocks.SOLID_GLASS.getDefaultState();
			case "TardisMod:tile.TardisMod.GravityLift":
				return ModBlocks.SOLID_GRAVITY_LIFT.getDefaultState();
			case "TardisMod:tile.TardisMod.SchemaComponent":
				return getSchemaComponentState(meta);
			case "TardisMod:tile.TardisMod.InternalDoor":
				return ModBlocks.DOOR.getDefaultState();
			case "TardisMod:tile.TardisMod.ForceField":
				return ModBlocks.SOLID_FORCE_FIELD.getDefaultState();
			case "TardisMod:tile.TardisMod.Slab":
				return ModBlocks.CORRIDOR_SLAB.getDefaultState();
			// TODO: add other schema blocks that I might be missing now
			default:
				TardisMod.LOGGER.error("Can't find a block for name " + name);
				throw new InvalidFormatException();
		}
	}

	public static IBlockState getSchemaComponentState(int meta) {
		switch (meta) {
			case 2:
				return ModBlocks.CONTROL_PANEL.getDefaultState();
			case 3:
				return ModBlocks.CONSOLE.getDefaultState();
			case 4:
				return ModBlocks.EXIT_BOTTOM.getDefaultState();
			case 5:
				return ModBlocks.EXIT_TOP.getDefaultState();
			case 6:
				return ModBlocks.CONSOLE.getDefaultState();
			case 7:
				return ModBlocks.TEMPORAL_ENGINE.getDefaultState();
			case 8:
				return ModBlocks.TIME_ROTOR.getDefaultState();
			case 0:
			default:
				return ModBlocks.DOOR_CONNECTOR.getDefaultState();
		}
	}
}
