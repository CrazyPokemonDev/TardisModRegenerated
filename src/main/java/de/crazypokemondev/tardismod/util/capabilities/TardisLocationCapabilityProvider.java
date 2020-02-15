package de.crazypokemondev.tardismod.util.capabilities;

import de.crazypokemondev.tardismod.api.ITardisLocationCapability;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class TardisLocationCapabilityProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(ITardisLocationCapability.class)
	public static final Capability<ITardisLocationCapability> TARDIS_LOCATION_CAP = null;
	private ITardisLocationCapability instance = TARDIS_LOCATION_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == TARDIS_LOCATION_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == TARDIS_LOCATION_CAP ? TARDIS_LOCATION_CAP.cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return TARDIS_LOCATION_CAP.getStorage().writeNBT(TARDIS_LOCATION_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		TARDIS_LOCATION_CAP.getStorage().readNBT(TARDIS_LOCATION_CAP, this.instance, null, nbt);
	}

}
