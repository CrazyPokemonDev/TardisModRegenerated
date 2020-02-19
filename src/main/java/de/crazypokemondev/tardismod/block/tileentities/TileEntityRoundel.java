package de.crazypokemondev.tardismod.block.tileentities;

import de.crazypokemondev.tardismod.api.ISonicScrewdriverCapability;
import de.crazypokemondev.tardismod.api.ScrewdriverMode;
import de.crazypokemondev.tardismod.block.BlockRoundel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class TileEntityRoundel extends TileEntity implements ISonicScrewdriverCapability {
	@CapabilityInject(ISonicScrewdriverCapability.class)
	static Capability<ISonicScrewdriverCapability> SONIC_SCREWDRIVER_CAPABILITY = null;

	@Override
	public EnumActionResult screw(EntityPlayer player, World worldIn, BlockPos pos, EnumFacing facing, float hitX,
			float hitY, float hitZ, EnumHand hand, ScrewdriverMode mode) {
		IBlockState state = worldIn.getBlockState(pos);
		if (mode == ScrewdriverMode.RECONFIGURE && !state.getValue(BlockRoundel.OPEN)) {
			worldIn.setBlockState(pos, state.withProperty(BlockRoundel.OPEN, true));
			return EnumActionResult.SUCCESS;
		} else if (mode == ScrewdriverMode.DISMANTLE && state.getValue(BlockRoundel.OPEN)) {
			worldIn.setBlockState(pos, state.withProperty(BlockRoundel.OPEN, false));
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == SONIC_SCREWDRIVER_CAPABILITY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == SONIC_SCREWDRIVER_CAPABILITY) {
			return SONIC_SCREWDRIVER_CAPABILITY.cast(this);
		}
		return super.getCapability(capability, facing);
	}
}
