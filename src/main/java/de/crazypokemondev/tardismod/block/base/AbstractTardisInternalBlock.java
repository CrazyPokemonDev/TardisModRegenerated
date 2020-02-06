package de.crazypokemondev.tardismod.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public abstract class AbstractTardisInternalBlock extends Block {

	public AbstractTardisInternalBlock() {
		super(Material.IRON);
		setSoundType(SoundType.METAL);
		setBlockUnbreakable();
	}
	
}
