package de.crazypokemondev.tardismod.util.capabilities;

import java.util.concurrent.Callable;

import de.crazypokemondev.tardismod.api.ITardisLocationCapability;

public class TardisLocationFactory implements Callable<ITardisLocationCapability> {

	@Override
	public ITardisLocationCapability call() throws Exception {
		return new TardisLocationCapability();
	}

}
