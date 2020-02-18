package de.crazypokemondev.tardismod.block;

import java.util.Random;

import de.crazypokemondev.tardismod.init.ModBlocks;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public abstract class BlockCorridorSlab extends BlockSlab {
	public static final PropertyEnum<Variant> VARIANT = PropertyEnum.<Variant>create("variant", Variant.class);

	public BlockCorridorSlab() {
		super(Material.IRON);
		setSoundType(SoundType.METAL);
		setBlockUnbreakable();
		setLightLevel(1.0F);

		IBlockState iblockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);

		if (!this.isDouble()) {
			iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
		}

		this.setDefaultState(iblockstate);
		this.useNeighborBrightness = !this.isDouble();
	}

	@Override
	public String getUnlocalizedName(int meta) {
		return super.getUnlocalizedName();
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return Variant.DEFAULT;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModBlocks.CORRIDOR_SLAB_HALF.getItemDropped(state, rand, fortune);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState blockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);

		if (!this.isDouble()) {
			blockstate = blockstate.withProperty(HALF, ((meta & 8) != 0) ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
		}

		return blockstate;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;

		if (!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) {
			meta |= 8;
		}

		return meta;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		if (!this.isDouble()) {
			return new BlockStateContainer(this, new IProperty[] { VARIANT, HALF });
		}
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	public enum Variant implements IStringSerializable {
		DEFAULT;

		@Override
		public String getName() {
			return "default";
		}

	}

	public static class Double extends BlockCorridorSlab {

		@Override
		public boolean isDouble() {
			return true;
		}

	}

	public static class Half extends BlockCorridorSlab {

		@Override
		public boolean isDouble() {
			return false;
		}

	}
}
