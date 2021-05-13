package factuall.fiber.modules.list;

import org.lwjgl.input.Keyboard;

import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import factuall.fiber.utils.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class Nuker extends Module{

	public Nuker() {
		super("Nuker", Category.MISC, "Fallout 7",
				Keyboard.KEY_N, null);
		// TODO Auto-generated constructor stub
	}
	
	int blockX;
	int blockY;
	int blockZ;
	

	public static int size = 5;
	public static int sizeOther = Math.round(size / 2);
	
	@Override
	public void onPreUpdate() {
		super.onPreUpdate();
		
		size = 3;
		
		if(mc.thePlayer.capabilities.isCreativeMode){
			for(int x = -size; x < size + sizeOther; x++){
				for(int z = -size; z < size + sizeOther; z++){
					for(int y = -size; y < size + sizeOther; y++){
						boolean shouldBreakBlock = true;
	        			blockX = (int) (mc.thePlayer.posX + x);
	        			blockY = (int) (mc.thePlayer.posY + y);
	        			blockZ = (int) (mc.thePlayer.posZ + z);
	        			if (Block.getIdFromBlock(mc.theWorld.getBlockState(new BlockPos(blockX, blockY, blockZ)).getBlock()) != 0){
	        				mc.thePlayer.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, new BlockPos(blockX, blockY, blockZ), EnumFacing.UP));
	         			}
	 				}
	 			}
	 		}
		}
		
			
	}

	public void onRender() {
		super.onRender();
		
		size = 3;
		
		if(mc.thePlayer.capabilities.isCreativeMode){
			for(int x = -size; x < size + sizeOther; x++){
				for(int z = -size; z < size + sizeOther; z++){
					for(int y = -size; y < size + sizeOther; y++){
						boolean shouldBreakBlock = true;
	        			blockX = (int) (mc.thePlayer.posX + x);
	        			blockY = (int) (mc.thePlayer.posY + y);
	        			blockZ = (int) (mc.thePlayer.posZ + z);
	        			if (Block.getIdFromBlock(mc.theWorld.getBlockState(new BlockPos(blockX, blockY, blockZ)).getBlock()) != 0){
	        				RenderUtils.drawESPBox(blockX, blockY, blockZ, 1, 0, 0, 1f);
	         			}
	 				}
	 			}
	 		}
		}
		
		try {
			
		}catch(Exception ex) {
			
		}
	}
}
