package factuall.fiber.utils;


import java.util.ArrayList;
import java.util.Arrays;

import factuall.fiber.Fiber;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;


public class BlockUtils {

	public static BlockPos getClosestBlockToPlayer(ArrayList<BlockPos> blocks) {
		if(blocks.size() == 0) return null;
		BlockPos closest = blocks.get(0);
		for(BlockPos blockpos : blocks) {
			if(Fiber.mc.thePlayer.getDistance(blockpos.getX(), blockpos.getY(), blockpos.getZ()) < Fiber.mc.thePlayer.getDistance(closest.getX(), closest.getY(), closest.getZ()))
				closest = blockpos;
		}
		return closest;
	}
	
	public static BlockPos getClosestBlockToEntity(ArrayList<BlockPos> blocks, Entity entity) {
		if(blocks.size() == 0) return null;
		BlockPos closest = blocks.get(0);
		for(BlockPos blockpos : blocks) {
			if(entity.getDistance(blockpos.getX(), blockpos.getY(), blockpos.getZ()) < entity.getDistance(closest.getX(), closest.getY(), closest.getZ()))
				closest = blockpos;
		}
		return closest;
	}
	
	public static BlockPos getClosestBlockToEntityFix(ArrayList<BlockPos> blocks, Entity entity) {
		if(blocks.size() == 0) return null;
		BlockPos closest = blocks.get(0);
		for(BlockPos blockpos : blocks) {
			if(entity.getDistance(blockpos.getX(), blockpos.getY(), blockpos.getZ()) < entity.getDistance(closest.getX(), closest.getY(), closest.getZ()) &&
					entity.getDistance(blockpos.getX(), blockpos.getY(), blockpos.getZ()) > 1.5)
				closest = blockpos;
		}
		return closest;
	}
	
	public static ArrayList<Block> NONSOLIDBLOCKS = new ArrayList<Block>(Arrays.asList(
			Blocks.AIR, Blocks.LAVA,
			Blocks.WATER, Blocks.VINE,
			Blocks.SNOW, Blocks.TALLGRASS, Blocks.FIRE, Blocks.GRASS));
	
	public static ArrayList<Block> CLICKABLEBLOCKS = new ArrayList<Block>(Arrays.asList(
			Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST,
			Blocks.ANVIL, Blocks.STONE_BUTTON, Blocks.WOODEN_BUTTON, 
			Blocks.UNPOWERED_COMPARATOR,
			Blocks.POWERED_COMPARATOR, Blocks.UNPOWERED_REPEATER, Blocks.POWERED_REPEATER,
			Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE,
			Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE,
			Blocks.BREWING_STAND, Blocks.DISPENSER, Blocks.DROPPER,
			Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.JUKEBOX,
			Blocks.BEACON, Blocks.BED, Blocks.TRAPDOOR,
			Blocks.FURNACE, Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR,
			Blocks.BIRCH_DOOR, Blocks.JUNGLE_DOOR, Blocks.ACACIA_DOOR,
			Blocks.DARK_OAK_DOOR, Blocks.CAKE, Blocks.ENCHANTING_TABLE,
			Blocks.DRAGON_EGG, Blocks.HOPPER, Blocks.REPEATING_COMMAND_BLOCK,
			Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.CRAFTING_TABLE,
			Blocks.IRON_TRAPDOOR));
		
	public static boolean doesAABBTouchBlock(AxisAlignedBB aabb, Block block) {
		for(int x = (int) Math.floor(aabb.minX); x < Math.ceil(aabb.maxX); x++) {
			for(int y = (int) Math.floor(aabb.minY); y < Math.ceil(aabb.maxY); y++) {
				for(int z = (int) Math.floor(aabb.minZ); z < Math.ceil(aabb.maxZ); z++) {
					if(Fiber.mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock() == block) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean isValidBlock(BlockPos bPos) {
		Block block = Fiber.mc.theWorld.getBlockState(bPos).getBlock();
		if(NONSOLIDBLOCKS.contains(block)) return false;
		else return true;
	}
	
}
