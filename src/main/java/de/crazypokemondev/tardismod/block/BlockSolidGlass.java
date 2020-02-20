package de.crazypokemondev.tardismod.block;

import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSolidGlass extends BlockGlass {

	public BlockSolidGlass() {
		super(Material.GLASS, false);
		setSoundType(SoundType.GLASS);
		setBlockUnbreakable();
	}

	@Override
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
		return !(entity instanceof EntityWither) && !(entity instanceof EntityWitherSkull);
	}

	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
	}

	@Override
	public boolean canDropFromExplosion(Explosion explosion) {
		return false;
	}

}
