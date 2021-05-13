package factuall.fiber.utils;


import factuall.fiber.Fiber;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;


public class PlayerUtils {

	public static void CFaceEntity(EntityLivingBase entity) {
		final float[] rotations = CombatUtils.getRotationsNeeded(entity);

		if (rotations != null) {
			Minecraft.getMinecraft().thePlayer.rotationYaw = rotations[0];
			Minecraft.getMinecraft().thePlayer.rotationPitch = rotations[1] + 1.0F;// 14
		}
	}
	
	public static void PFaceEntity(EntityLivingBase entity) {
		final float[] rotations = CombatUtils.getRotationsNeeded(entity);

		if (rotations != null) {
			PacketUtils.overrideRotations(rotations[0], rotations[1] + 1.0F);
		}
	}
	
	public static void CFaceBlock(double blockX, double blockY, double blockZ) {
		final float[] rotations = CombatUtils.getRotationsNeeded(blockX, blockY, blockZ);

		if (rotations != null) {
			Minecraft.getMinecraft().thePlayer.rotationYaw = rotations[0];
			Minecraft.getMinecraft().thePlayer.rotationPitch = rotations[1] + 1.0F;// 14
		}
	}
	
	public static void CFaceBlock(BlockPos bPos) {
		final float[] rotations = CombatUtils.getRotationsNeeded(bPos.getX(), bPos.getY(), bPos.getZ());

		if (rotations != null) {
			Minecraft.getMinecraft().thePlayer.rotationYaw = rotations[0];
			Minecraft.getMinecraft().thePlayer.rotationPitch = rotations[1] + 1.0F;// 14
		}
	}
	
	public static void placeBlock(BlockPos bPos, EnumFacing face) {
		if(face == EnumFacing.UP) bPos.add(0, -1, 0);
		if(face == EnumFacing.DOWN) bPos.add(0, 1, 0);
		if(face == EnumFacing.WEST) bPos.add(1, 0, 0);
		if(face == EnumFacing.EAST) bPos.add(-1, 0, 0);
		if(face == EnumFacing.SOUTH) bPos.add(-1, 0, 0);
		if(face == EnumFacing.NORTH) bPos.add(1, 0, 0);
		
		if(Fiber.mc.thePlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemBlock) {
			Fiber.mc.thePlayer.swingArm(EnumHand.MAIN_HAND);
			Fiber.mc.playerController.processRightClickBlock(Fiber.mc.thePlayer, Fiber.mc.theWorld, bPos, face, new Vec3d(0.5d, 0.5d, 0.5d), EnumHand.MAIN_HAND);
			
			float[] rotations = CombatUtils.getRotationsNeeded(bPos.getX(), bPos.getY(), bPos.getZ());
			
			PacketUtils.overrideRotations(rotations[0], rotations[1]);
			
			
		}
	}
	
	public static BlockPos getClosestBlock() {
		BlockPos closest = null;
		
		return closest;
	}
	
	
	
}
