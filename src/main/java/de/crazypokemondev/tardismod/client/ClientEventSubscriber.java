package de.crazypokemondev.tardismod.client;

import de.crazypokemondev.tardismod.TardisMod;
import de.crazypokemondev.tardismod.init.ModBlocks;
import de.crazypokemondev.tardismod.init.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT, modid = TardisMod.MODID)
public class ClientEventSubscriber {

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		registerModel(ModItems.TARDIS_KEY, 0);
		registerModel(ModItems.KONTRON_CRYSTAL, 0);
		registerModel(Item.getItemFromBlock(ModBlocks.SOLID_BLOCK), 0);
	}

	private static void registerModel(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, 
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

}
