package de.crazypokemondev.tardismod.client.models;

import de.crazypokemondev.tardismod.api.ScrewdriverMode;
import de.crazypokemondev.tardismod.init.ModItems;
import de.crazypokemondev.tardismod.util.helpers.ScrewdriverHelper;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ScrewdriverCustomMesh implements ItemMeshDefinition {

	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) {
		ScrewdriverMode mode = ScrewdriverHelper.getMode(stack);
		ModelResourceLocation location = new ModelResourceLocation(ModItems.SONIC_SCREWDRIVER.getRegistryName(),
				mode.getName());
		return location;
	}

	public static ResourceLocation[] getItemVariants() {
		ScrewdriverMode[] values = ScrewdriverMode.values();
		int count = values.length;
		ResourceLocation[] result = new ResourceLocation[count];
		for (int i = 0; i < count; i++) {
			result[i] = new ModelResourceLocation(ModItems.SONIC_SCREWDRIVER.getRegistryName(), values[i].getName());
		}
		return result;
	}
}
