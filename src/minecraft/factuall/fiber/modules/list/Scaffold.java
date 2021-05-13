package factuall.fiber.modules.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import factuall.fiber.Fiber;
import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import factuall.fiber.utils.BlockUtils;
import factuall.fiber.utils.PlayerUtils;
import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction.Action;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import net.minecraft.util.SoundCategory;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.math.Vec3d;

public class Scaffold extends Module{

	private HashMap<BlockPos, Integer> lastPlaced = new HashMap<>();
	
	public Scaffold() {
		super("Scaffold", Category.MOVEMENT, "Places blocks under you",
				Keyboard.KEY_NONE, null);
		// TODO Auto-generated constructor stub
	}


	public int delay = 0;
	
	@Override
	public void onEnable() {
		super.onEnable();

		
	}
	
	
	@Override
	public void onPreUpdate() {
		super.onPreUpdate();
		
		if(delay > 1) {
			BlockPos playerBlock = new BlockPos(mc.thePlayer.posX, mc.thePlayer.getEntityBoundingBox().minY, mc.thePlayer.posZ);
			if(mc.theWorld.isAirBlock(playerBlock.add(0, -1, 0))) {
					  if(BlockUtils.isValidBlock(playerBlock.add(0, -2, 0))) {
					PlayerUtils.placeBlock(playerBlock.add(0, -1, 0), EnumFacing.DOWN);
				}else if(BlockUtils.isValidBlock(playerBlock.add(-1, -1, 0))) {
					PlayerUtils.placeBlock(playerBlock.add(0, -1, 0), EnumFacing.EAST);
				}else if(BlockUtils.isValidBlock(playerBlock.add(1, -1, 0))) {
					PlayerUtils.placeBlock(playerBlock.add(0, -1, 0), EnumFacing.WEST);
				}else if(BlockUtils.isValidBlock(playerBlock.add(0, -1, -1))) {
					PlayerUtils.placeBlock(playerBlock.add(0, -1, 0), EnumFacing.SOUTH);
				}else if(BlockUtils.isValidBlock(playerBlock.add(0, -1, 1))) {
					PlayerUtils.placeBlock(playerBlock.add(0, -1, 0), EnumFacing.NORTH);
				}
				else if(BlockUtils.isValidBlock(playerBlock.add(1, -1, 1))) {		
						PlayerUtils.placeBlock(playerBlock.add(1, -1, 0), EnumFacing.SOUTH);
						PlayerUtils.placeBlock(playerBlock.add(0, -1, 0), EnumFacing.EAST);
				}
				else if(BlockUtils.isValidBlock(playerBlock.add(-1, -1, -1))) {		
					PlayerUtils.placeBlock(playerBlock.add(-1, -1, 0), EnumFacing.NORTH);
					PlayerUtils.placeBlock(playerBlock.add(0, -1, 0), EnumFacing.WEST);
				}
				else if(BlockUtils.isValidBlock(playerBlock.add(1, -1, -1))) {		
					PlayerUtils.placeBlock(playerBlock.add(1, -1, 0), EnumFacing.NORTH);
					PlayerUtils.placeBlock(playerBlock.add(0, -1, 0), EnumFacing.EAST);
				}
				else if(BlockUtils.isValidBlock(playerBlock.add(-1, -1, 1))) {		
					PlayerUtils.placeBlock(playerBlock.add(-1, -1, 0), EnumFacing.SOUTH);
					PlayerUtils.placeBlock(playerBlock.add(0, -1, 0), EnumFacing.WEST);
				}
			}
			delay = 0;
		}else {
			delay++;
		}
		
		
			
		
	}
	
	
	@Override
    public void onDisable() {

    }


    
}
