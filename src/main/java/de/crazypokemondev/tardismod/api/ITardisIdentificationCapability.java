package de.crazypokemondev.tardismod.api;

import javax.annotation.Nullable;

public interface ITardisIdentificationCapability {
	boolean hasTardis();
	@Nullable
	Integer getTardisDimensionId();
	void setTardisDimensionId(@Nullable Integer id);
}
