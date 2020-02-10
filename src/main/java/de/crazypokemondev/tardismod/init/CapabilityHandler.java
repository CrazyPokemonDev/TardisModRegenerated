package de.crazypokemondev.tardismod.init;

import de.crazypokemondev.tardismod.TardisMod;
import de.crazypokemondev.tardismod.api.ITardisIdentificationCapability;
import de.crazypokemondev.tardismod.block.tileentities.TileEntityTardis;
import de.crazypokemondev.tardismod.util.capabilities.TardisIdentificationCapabilityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = TardisMod.MODID)
public class CapabilityHandler {
	public static final ResourceLocation TARDIS_ID_CAP = new ResourceLocation(TardisMod.MODID, "tardisIdCap");

	@SubscribeEvent
	public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (!(event.getObject() instanceof EntityPlayer))
			return;
		event.addCapability(TARDIS_ID_CAP, new TardisIdentificationCapabilityProvider());
	}

	@SubscribeEvent
	public static void attachTileEntityCapabilities(AttachCapabilitiesEvent<TileEntity> event) {
		if (event.getObject() instanceof TileEntityTardis) {
			event.addCapability(TARDIS_ID_CAP, new TardisIdentificationCapabilityProvider());
		}
	}
	
	/**
	 * Copy data from dead player to the new player
	 */
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {
		if (!event.isWasDeath()) return;
		EntityPlayer player = event.getEntityPlayer();
		ITardisIdentificationCapability newCap = player
				.getCapability(TardisIdentificationCapabilityProvider.TARDIS_ID_CAP, null);
		ITardisIdentificationCapability oldCap = event.getOriginal()
				.getCapability(TardisIdentificationCapabilityProvider.TARDIS_ID_CAP, null);
		newCap.setTardisDimensionId(oldCap.getTardisDimensionId());
	}
}
