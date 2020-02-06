package de.crazypokemondev.tardismod.block;

import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockSolidGlass extends BlockGlass {

	public BlockSolidGlass() {
		super(Material.GLASS, false);
		setSoundType(SoundType.GLASS);
		setBlockUnbreakable();
	}
	
}
