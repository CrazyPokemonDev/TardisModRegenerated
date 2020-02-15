package de.crazypokemondev.tardismod.util.capabilities;

import java.util.concurrent.Callable;

import de.crazypokemondev.tardismod.api.ITardisIdentificationCapability;

public class TardisIdentificationFactory implements Callable<ITardisIdentificationCapability> {

	@Override
	public ITardisIdentificationCapability call() throws Exception {
		return new TardisIdentificationCapability();
	}

}
