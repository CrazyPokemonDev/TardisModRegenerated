package de.crazypokemondev.tardismod.block.base;

import de.crazypokemondev.tardismod.api.ITardisIdentificationCapability;
import de.crazypokemondev.tardismod.block.tileentities.TileEntityTardis;
import de.crazypokemondev.tardismod.util.TardisLocation;
import de.crazypokemondev.tardismod.util.TardisModData;
import de.crazypokemondev.tardismod.util.helpers.MessageHelper;
import de.crazypokemondev.tardismod.util.helpers.TardisHelper;
import de.crazypokemondev.tardismod.util.helpers.Teleport;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class AbstractTardisPartBlock extends AbstractDirectionalBlock {

	private static final String TARDIS_DOORS_LOCKED = "messages.tardismod.tardis_doors_locked";

	@CapabilityInject(ITardisIdentificationCapability.class)
	static Capability<ITardisIdentificationCapability> TARDIS_IDENTIFICATION_CAPABILITY = null;

	public AbstractTardisPartBlock() {
		super(Material.IRON);
		setSoundType(SoundType.METAL);
		setBlockUnbreakable();
	}

	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote && hand == EnumHand.MAIN_HAND) {
			TileEntityTardis te = getTardisTileEntity(worldIn, pos);
			if (te == null) {
				return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
			}
			TileEntityTardis tardis = (TileEntityTardis) te;
			ITardisIdentificationCapability tardisCap = tardis.getCapability(TARDIS_IDENTIFICATION_CAPABILITY, facing);
			if (!tardisCap.hasTardis()) {
				// this TARDIS hasn't been connected to a dimension yet
				ITardisIdentificationCapability playerCap = playerIn.getCapability(TARDIS_IDENTIFICATION_CAPABILITY,
						null);
				if (!playerCap.hasTardis()) {
					// this player hasn't been connected to a dimension, either
					int dimensionId = TardisHelper.generateNewTardisDim();
					playerCap.setTardisDimensionId(dimensionId);
				}
				int dimensionId = playerCap.getTardisDimensionId();
				if (TardisModData.get(worldIn).exists(dimensionId) && !TardisModData.get(worldIn)
						.getLocation(dimensionId).equals(new TardisLocation(worldIn, te.getPos()))) {
					// the player already has a TARDIS and therefore can't claim this one
					MessageHelper.sendLocalizedMessage(playerIn, worldIn, TARDIS_DOORS_LOCKED);
					return false;
				}
				tardisCap.setTardisDimensionId(dimensionId);
			}
			int dimensionId = tardisCap.getTardisDimensionId();
			Teleport.teleportToDimension(playerIn, dimensionId, 13.5, 68, 0.5, 90, 1);
			return true;
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	private TileEntityTardis getTardisTileEntity(World worldIn, BlockPos pos) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (!(te instanceof TileEntityTardis)) {
			te = worldIn.getTileEntity(pos.down());
		}
		if (!(te instanceof TileEntityTardis)) {
			return null;
		}
		return (TileEntityTardis) te;
	}

}
