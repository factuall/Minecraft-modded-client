package factuall.fiber.utils;


import java.util.List;

import factuall.fiber.Fiber;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;


public class CombatUtils {

	public static EntityLivingBase getBestPlayerByDistance() {
		List list = Fiber.mc.theWorld.playerEntities;
		EntityLivingBase target = (EntityLivingBase)list.get(list.size()-1);
		for (int k = 0; k < list.size(); k++) {
			if (((EntityPlayer) list.get(k)).getName() == Fiber.mc.thePlayer.getName()) {
				continue;
			}
			if (Fiber.mc.thePlayer.getDistanceToEntity(target) > Fiber.mc.thePlayer.getDistanceToEntity((Entity)list.get(k))) {
				target = (EntityLivingBase)list.get(k);
			}
		}
		return target;
	}
	
	public static boolean safetyCheck(EntityLivingBase target) {
		if(target == Fiber.mc.theWorld.playerEntities.get(0)) {
			return false;
		}else {
			return true;
		}
	}
	
	public static boolean isCrystalThere(double blockX, double blockY, double blockZ) {
		List list = Fiber.mc.theWorld.loadedEntityList;
		for (int k = 0; k < list.size(); k++) {
			if(((Entity)list.get(k)).getName().equals("entity.EnderCrystal.name")) {
				double crystalX = ((Entity)list.get(k)).posX-0.5;
				double crystalY = ((Entity)list.get(k)).posY-1;
				double crystalZ = ((Entity)list.get(k)).posZ-0.5;
				if(blockX == crystalX && blockY == crystalY && blockZ == crystalZ) return true;
			}
			
		}
		return false;
	}
	
	public static Entity getBlocksCrystal(double blockX, double blockY, double blockZ) {
		List list = Fiber.mc.theWorld.loadedEntityList;
		for (int k = 0; k < list.size(); k++) {
			if(((Entity)list.get(k)).getName().equals("entity.EnderCrystal.name")) {
				double crystalX = ((Entity)list.get(k)).posX-0.5;
				double crystalY = ((Entity)list.get(k)).posY-1;
				double crystalZ = ((Entity)list.get(k)).posZ-0.5;
				if(blockX == crystalX && blockY == crystalY && blockZ == crystalZ) return (Entity)list.get(k);
			}
		}
		return null;
	}

	public static float[] getRotationsNeeded(Entity entity) {
		if (entity == null) {
			return null;
		}

		final double diffX = entity.posX - Minecraft.getMinecraft().thePlayer.posX;
		final double diffZ = entity.posZ - Minecraft.getMinecraft().thePlayer.posZ;
		double diffY;

		if (entity instanceof EntityLivingBase) {
			final EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
			diffY = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight() + 0.3f);
		} else {
			diffY = (entity.boundingBox.minY + entity.boundingBox.maxY) / 2.0D - (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
		}

		final double dist = MathHelper.sqrt(diffX * diffX + diffZ * diffZ);
		final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
		final float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
		return new float[] { Minecraft.getMinecraft().thePlayer.rotationYaw + MathHelper.wrapDegrees(yaw - Minecraft.getMinecraft().thePlayer.rotationYaw), Minecraft.getMinecraft().thePlayer.rotationPitch + MathHelper.wrapDegrees(pitch - Minecraft.getMinecraft().thePlayer.rotationPitch) };
	}
	
	public static float[] getRotationsNeeded(double blockX, double blockY, double blockZ) {

		

		final double diffX = blockX + 0.5 - Minecraft.getMinecraft().thePlayer.posX;
		final double diffZ = blockZ + 0.5 - Minecraft.getMinecraft().thePlayer.posZ;
		double diffY = blockY + 0.5 - (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
		final double dist = MathHelper.sqrt(diffX * diffX + diffZ * diffZ);
		final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
		final float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
		return new float[] { Minecraft.getMinecraft().thePlayer.rotationYaw + MathHelper.wrapDegrees(yaw - Minecraft.getMinecraft().thePlayer.rotationYaw), Minecraft.getMinecraft().thePlayer.rotationPitch + MathHelper.wrapDegrees(pitch - Minecraft.getMinecraft().thePlayer.rotationPitch) };
	}

	public static void CAttackEntity(Entity entity) {
		Fiber.mc.playerController.attackEntity(Fiber.mc.thePlayer, entity);
		Fiber.mc.thePlayer.swingArm(EnumHand.MAIN_HAND);
	}
	
}
