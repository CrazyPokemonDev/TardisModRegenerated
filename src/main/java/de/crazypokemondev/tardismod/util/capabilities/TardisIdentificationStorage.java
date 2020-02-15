package de.crazypokemondev.tardismod.util.capabilities;

import de.crazypokemondev.tardismod.api.ITardisIdentificationCapability;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.util.Constants.NBT;

public class TardisIdentificationStorage implements IStorage<ITardisIdentificationCapability> {

	private static final String TARDIS_DIMENSION_ID = "tardisDimensionId";
	private static final String HAS_TARDIS = "hasTardis";

	@Override
	public NBTBase writeNBT(Capability<ITardisIdentificationCapability> capability,
			ITardisIdentificationCapability instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		boolean hasTardis = instance.hasTardis();
		nbt.setBoolean(HAS_TARDIS, hasTardis);
		if (hasTardis) {
			nbt.setInteger(TARDIS_DIMENSION_ID, instance.getTardisDimensionId());
		}
		return nbt;
	}

	@Override
	public void readNBT(Capability<ITardisIdentificationCapability> capability,
			ITardisIdentificationCapability instance, EnumFacing side, NBTBase nbt) {
		if (!(nbt instanceof NBTTagCompound)) {
			return;
		}
		NBTTagCompound nbtc = (NBTTagCompound) nbt;
		if (nbtc.getBoolean(HAS_TARDIS) && nbtc.hasKey(TARDIS_DIMENSION_ID, NBT.TAG_INT)) {
			instance.setTardisDimensionId(nbtc.getInteger(TARDIS_DIMENSION_ID));
		} else {
			instance.setTardisDimensionId(null);
		}
	}

}
