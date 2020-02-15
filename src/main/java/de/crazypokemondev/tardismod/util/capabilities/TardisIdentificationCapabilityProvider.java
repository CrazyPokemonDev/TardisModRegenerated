package de.crazypokemondev.tardismod.util.capabilities;

import de.crazypokemondev.tardismod.api.ITardisIdentificationCapability;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class TardisIdentificationCapabilityProvider implements ICapabilitySerializable<NBTBase> {
	@CapabilityInject(ITardisIdentificationCapability.class)
	public static final Capability<ITardisIdentificationCapability> TARDIS_ID_CAP = null;
	private ITardisIdentificationCapability instance = TARDIS_ID_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == TARDIS_ID_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == TARDIS_ID_CAP ? TARDIS_ID_CAP.<T>cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return TARDIS_ID_CAP.getStorage().writeNBT(TARDIS_ID_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		TARDIS_ID_CAP.getStorage().readNBT(TARDIS_ID_CAP, this.instance, null, nbt);
	}
}
