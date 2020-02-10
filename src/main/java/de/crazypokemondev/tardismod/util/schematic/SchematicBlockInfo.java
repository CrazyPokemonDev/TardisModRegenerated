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
		if (!nbt.hasKey(BLOCK_META, NBT.TAG_INT) || !nbt.hasKey(BLOCK_NAME, NBT.TAG_STRING))
			throw new InvalidFormatException();
		IBlockState state;
		int meta = nbt.getInteger(BLOCK_META);
		switch (nbt.getString(BLOCK_NAME)) {
		case "TardisMod:tile.TardisMod.Schema":
			state = ModBlocks.SCHEMA.getStateFromMeta(0);
			break;
		case "TardisMod:tile.TardisMod.SchemaCore":
			state = ModBlocks.SCHEMA.getStateFromMeta(1);
			break;
		case "TardisMod:tile.TardisMod.ColorableFloor":
			state = ModBlocks.FLAT_BLOCK.getDefaultState();
			break;
		case "TardisMod:tile.TardisMod.ColorableWall":
			state = ModBlocks.SOLID_BLOCK.getDefaultState();
			break;
		case "TardisMod:tile.TardisMod.ColorableRoundel":
			state = ModBlocks.ROUNDEL.getStateFromMeta(meta);
			break;
		case "TardisMod:tile.TardisMod.DecoGlass":
			state = ModBlocks.SOLID_GLASS.getDefaultState();
			break;
		case "TardisMod:tile.TardisMod.GravityLift":
			state = ModBlocks.SOLID_GRAVITY_LIFT.getDefaultState();
			break;
		case "TardisMod:tile.TardisMod.SchemaComponent":
			switch(meta) {
			case 2:
				state = ModBlocks.CONTROL_PANEL.getDefaultState();
				break;
			case 3:
				state = ModBlocks.CONSOLE.getDefaultState();
				break;
			case 4:
				state = ModBlocks.EXIT_BOTTOM.getDefaultState();
				break;
			case 5:
				state = ModBlocks.EXIT_TOP.getDefaultState();
				break;
			case 6:
				state = ModBlocks.CONSOLE.getDefaultState();
				break;
			case 7:
				state = ModBlocks.TEMPORAL_ENGINE.getDefaultState();
				break;
			case 8:
				state = ModBlocks.TIME_ROTOR.getDefaultState();
				break;
			case 0:
			default:
				state = ModBlocks.DOOR_CONNECTOR.getDefaultState();
				break;
			}
			break;
		case "TardisMod:tile.TardisMod.InternalDoor":
			state = ModBlocks.DOOR.getDefaultState();
			break;
		case "TardisMod:tile.TardisMod.ForceField":
			state = ModBlocks.SOLID_FORCE_FIELD.getDefaultState();
			break;
		case "TardisMod:tile.TardisMod.Slab":
			state = ModBlocks.CORRIDOR_SLAB.getDefaultState();
			break;
			// TODO: add other schema blocks that I might be missing now
		default:
			TardisMod.LOGGER.error("Can't find a block for name " + nbt.getString(BLOCK_NAME));
			throw new InvalidFormatException();
		}
		return new SchematicBlockInfo(blockPos, state);
	}
}
