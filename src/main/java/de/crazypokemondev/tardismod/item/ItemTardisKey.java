package de.crazypokemondev.tardismod.item;

import java.util.UUID;

import de.crazypokemondev.tardismod.api.ITardisIdentificationCapability;
import de.crazypokemondev.tardismod.block.tileentities.TileEntityTardis;
import de.crazypokemondev.tardismod.init.ModWorldGen;
import de.crazypokemondev.tardismod.util.TardisModData;
import de.crazypokemondev.tardismod.util.helpers.CalculationHelper;
import de.crazypokemondev.tardismod.util.helpers.MessageHelper;
import de.crazypokemondev.tardismod.util.helpers.TardisHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.Constants.NBT;

public class ItemTardisKey extends Item {
	private static final String OWNER_UUID = "ownerUUID";
	private static final String DIMENSION_ID = "dimensionID";
	private static final String TARDIS_CANT_SPAWN_NO_SPACE = "messages.tardismod.tardis_cant_spawn_no_space";
	private static final String TARDIS_SPAWN_SUCCESSFUL = "messages.tardismod.tardis_spawn_successful";

	public ItemTardisKey() {
		setMaxStackSize(1);
	}

	@CapabilityInject(ITardisIdentificationCapability.class)
	static Capability<ITardisIdentificationCapability> TARDIS_IDENTIFICATION_CAPABILITY = null;

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (handIn == EnumHand.MAIN_HAND && !worldIn.isRemote
				&& worldIn.provider.getDimensionType() != ModWorldGen.TARDIS_DIM_TYPE) {
			ItemStack key = playerIn.getHeldItem(handIn);
			UUID ownerUuid = getOrSetOwnerUuid(playerIn, key);
			EntityPlayer owner = worldIn.getPlayerEntityByUUID(ownerUuid);
			int connectedDimensionId;
			if (owner == null) {
				if (key.getTagCompound().hasKey(DIMENSION_ID, NBT.TAG_INT)) {
					connectedDimensionId = key.getTagCompound().getInteger(DIMENSION_ID);
				} else {
					// this key seems to be broken, someone probably messed with the nbt data
					return super.onItemRightClick(worldIn, playerIn, handIn);
				}
			} else {
				ITardisIdentificationCapability cap = owner.getCapability(TARDIS_IDENTIFICATION_CAPABILITY, null);
				if (!cap.hasTardis()) {
					int newDimId = TardisHelper.generateNewTardisDim();
					cap.setTardisDimensionId(newDimId);
				}
				connectedDimensionId = cap.getTardisDimensionId();
			}
			key.getTagCompound().setInteger(DIMENSION_ID, connectedDimensionId);

			if (TardisModData.get(worldIn).exists(connectedDimensionId)) {
				return new ActionResult<ItemStack>(EnumActionResult.PASS, key);
			}
			return spawnTardisIfPossible(worldIn, playerIn, key, connectedDimensionId);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	public ActionResult<ItemStack> spawnTardisIfPossible(World worldIn, EntityPlayer playerIn, ItemStack key,
			int connectedDimensionId) {
		EnumFacing playerFacing = CalculationHelper.getDirectionFromYaw(playerIn.rotationYaw);
		BlockPos posToSpawnTardis = getFreeDoubleBlockPosAroundPosition(playerIn.getPosition(), worldIn, playerFacing);
		if (posToSpawnTardis == null) {
			MessageHelper.sendLocalizedMessage(playerIn, worldIn, TARDIS_CANT_SPAWN_NO_SPACE);
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, key);
		}

		TardisHelper.moveTardisTo(worldIn, posToSpawnTardis, connectedDimensionId,
				playerIn.getHorizontalFacing().getOpposite());

		TileEntity te = worldIn.getTileEntity(posToSpawnTardis);
		TileEntityTardis tardis = (TileEntityTardis) te;
		ITardisIdentificationCapability cap = tardis.getCapability(TARDIS_IDENTIFICATION_CAPABILITY, null);
		cap.setTardisDimensionId(connectedDimensionId);

		MessageHelper.sendLocalizedMessage(playerIn, worldIn, TARDIS_SPAWN_SUCCESSFUL);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, key);
	}

	public UUID getOrSetOwnerUuid(EntityPlayer playerIn, ItemStack key) {
		if (key.getTagCompound() == null) {
			key.setTagCompound(new NBTTagCompound());
		}
		if (!key.getTagCompound().hasUniqueId(OWNER_UUID)) {
			key.getTagCompound().setUniqueId(OWNER_UUID, playerIn.getUniqueID());
		}
		UUID ownerUuid = key.getTagCompound().getUniqueId(OWNER_UUID);
		return ownerUuid;
	}

	private BlockPos getFreeDoubleBlockPosAroundPosition(BlockPos position, World worldIn,
			EnumFacing preferredDirection) {
		BlockPos[] possiblePositions = { position.offset(preferredDirection),
				position.offset(preferredDirection.rotateY()), position.offset(preferredDirection.rotateYCCW()),
				position.offset(preferredDirection.getOpposite()) };
		for (BlockPos tryPos : possiblePositions) {
			if (isFreePositionAndAbove(tryPos, worldIn)) {
				return tryPos;
			}
		}
		return null;
	}

	private boolean isFreePositionAndAbove(BlockPos tryPos, World worldIn) {
		return worldIn.isAirBlock(tryPos) && worldIn.isAirBlock(tryPos.up());
	}
}
