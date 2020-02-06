package de.crazypokemondev.tardismod.util.capabilities;

import javax.annotation.Nullable;

import de.crazypokemondev.tardismod.api.ITardisIdentificationCapability;

public class TardisIdentificationCapability implements ITardisIdentificationCapability {
	@Nullable
	private Integer tardisDimensionId;
	
	@Override
	public boolean hasTardis() {
		return tardisDimensionId != null;
	}

	@Override
	public Integer getTardisDimensionId() {
		return tardisDimensionId;
	}

	@Override
	public void setTardisDimensionId(Integer id) {
		tardisDimensionId = id;
	}

}
