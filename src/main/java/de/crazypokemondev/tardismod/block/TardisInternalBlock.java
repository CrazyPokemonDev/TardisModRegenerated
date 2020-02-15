package de.crazypokemondev.tardismod.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TardisInternalBlock extends Block {
	public TardisInternalBlock() {
		super(Material.IRON);
		setSoundType(SoundType.METAL);
		setBlockUnbreakable();
		setLightLevel(1.0F);
	}
}
