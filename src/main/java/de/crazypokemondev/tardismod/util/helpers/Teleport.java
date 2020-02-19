package de.crazypokemondev.tardismod.util.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class Teleport extends Teleporter {

	public final WorldServer world;
	private double posX;
	private double posY;
	private double posZ;

	public Teleport(WorldServer world, double x, double y, double z) {
		super(world);
		this.world = world;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {
		this.world.getBlockState(new BlockPos((int) posX, (int) posY, (int) posZ));
		entityIn.setPosition(posX, posY, posZ);
		entityIn.motionX = 0f;
		entityIn.motionY = 0f;
		entityIn.motionZ = 0f;
	}

	public static void teleportToDimension(EntityPlayer player, int dimension, double x, double y, double z, float yaw,
			float pitch) {
		EntityPlayerMP entityPlayerMp = (EntityPlayerMP) player;
		MinecraftServer server = player.getEntityWorld().getMinecraftServer();
		WorldServer worldServer = server.getWorld(dimension);
		if (worldServer == null) {
			throw new IllegalArgumentException("Dimension " + dimension + " doesn't exist!");
		}
		worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMp, dimension,
				new Teleport(worldServer, x, y, z));
		player.setPositionAndRotation(x, y, z, yaw, pitch);
		player.setPositionAndUpdate(x, y, z);
	}

	public static void teleportToDimension(EntityPlayer player, int dimension, BlockPos blockPos, float yaw,
			float pitch) {
		teleportToDimension(player, dimension, blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5, yaw, pitch);
	}

	public static void teleportToDimension(EntityPlayer playerIn, int dimensionId, BlockPos position, EnumFacing facing,
			int pitch) {
		int yaw = facing.getHorizontalIndex() * 90;
		teleportToDimension(playerIn, dimensionId, position, yaw, pitch);
	}
}
