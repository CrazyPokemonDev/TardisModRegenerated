package de.crazypokemondev.tardismod.block;

import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.SoundType;

public class BlockCorridorSlab extends BlockStoneSlab {

	public BlockCorridorSlab() {
		setSoundType(SoundType.METAL);
		setBlockUnbreakable();
	}
	
	@Override
	public boolean isDouble() {
		return false;
	}

}
