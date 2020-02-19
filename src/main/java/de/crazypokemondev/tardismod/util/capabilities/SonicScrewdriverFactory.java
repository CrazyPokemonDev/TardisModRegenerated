package de.crazypokemondev.tardismod.util.capabilities;

import java.util.concurrent.Callable;

import de.crazypokemondev.tardismod.api.ISonicScrewdriverCapability;

public class SonicScrewdriverFactory implements Callable<ISonicScrewdriverCapability> {

	@Override
	public ISonicScrewdriverCapability call() throws Exception {
		return new SonicScrewdriverMockCapability();
	}

}
