package de.crazypokemondev.tardismod.tabs;

import de.crazypokemondev.tardismod.TardisMod;
import de.crazypokemondev.tardismod.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TardisModTab extends CreativeTabs {
	public TardisModTab() {
		super(TardisMod.MODID);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.TARDIS_KEY);
	}
}
