package de.crazypokemondev.tardismod.util.capabilities;

import java.util.Map;
import java.util.Map.Entry;

import de.crazypokemondev.tardismod.TardisMod;
import de.crazypokemondev.tardismod.api.ITardisLocationCapability;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.util.Constants.NBT;

public class TardisLocationStorage implements IStorage<ITardisLocationCapability> {

	@Override
	public NBTBase writeNBT(Capability<ITardisLocationCapability> capability, ITardisLocationCapability instance,
			EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		Map<Integer, BlockPos> locations = instance.getAllLocations();
		for (Entry<Integer, BlockPos> entry : locations.entrySet()) {
			nbt.setIntArray(entry.getKey().toString(), toIntArray(entry.getValue()));
		}
		return nbt;
	}

	private int[] toIntArray(BlockPos value) {
		return new int[] { value.getX(), value.getY(), value.getZ() };
	}

	private BlockPos fromIntArray(int[] array) {
		return new BlockPos(array[0], array[1], array[2]);
	}

	@Override
	public void readNBT(Capability<ITardisLocationCapability> capability, ITardisLocationCapability instance,
			EnumFacing side, NBTBase nbt) {
		if (!(nbt instanceof NBTBase)) {
			return;
		}
		NBTTagCompound tag = (NBTTagCompound) nbt;
		for (String key : tag.getKeySet()) {
			if (!tag.hasKey(key, NBT.TAG_INT_ARRAY)) {
				continue;
			}
			int[] array = tag.getIntArray(key);
			try {
				int dimensionId = Integer.parseInt(key);
				instance.setLocation(dimensionId, fromIntArray(array));
			} catch (NumberFormatException e) {
				TardisMod.LOGGER.warn("Couldn't parse entry of TardisLocationStorage NBT, might be broken.");
			}
		}
	}

}
