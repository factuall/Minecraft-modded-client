package factuall.fiber.modules.list;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import factuall.fiber.Fiber;
import factuall.fiber.call.Call;
import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import factuall.fiber.options.BooleanOption;
import factuall.fiber.options.FloatOption;
import factuall.fiber.options.IntOption;
import factuall.fiber.options.ModuleOptions;
import factuall.fiber.utils.BlockUtils;
import factuall.fiber.utils.CombatUtils;
import factuall.fiber.utils.PlayerUtils;
import factuall.fiber.utils.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class CrystalAura extends Module{

	private static ModuleOptions crystalAuraOptions = new ModuleOptions();;
	
	
	public CrystalAura() {
		super("CrystalAura", Category.COMBAT, "Wpierdol",
				Keyboard.KEY_NONE, crystalAuraOptions);
		// TODO Auto-generated constructor stub
		
		crystalAuraOptions.addOption(new BooleanOption(true, "place", "Place end crystals"));
		crystalAuraOptions.addOption(new IntOption(3, "delay", "Delay between wich crystals will be placed"));
	}
	
	int delay;
	
	Entity currentEntity = null;
	
	@Override
	public void onPreUpdate() {
		super.onPreUpdate();
		delay++;
		List list = Fiber.mc.theWorld.loadedEntityList;
		for (int k = 0; k < list.size(); k++) {
			if(((Entity)list.get(k)).getName().equals("entity.EnderCrystal.name") && mc.thePlayer.getDistanceToEntity((Entity)list.get(k)) < 4 && mc.thePlayer.canEntityBeSeen((Entity)list.get(k))) {
				//PlayerUtils.CFaceBlock(((Entity)list.get(k)).getPosition().getX(), ((Entity)list.get(k)).getPosition().getY()-0.3, ((Entity)list.get(k)).getPosition().getZ());
				CombatUtils.CAttackEntity((Entity)list.get(k));
				return;
			}
			
		}
		BooleanOption placeOption = (BooleanOption) crystalAuraOptions.moduleOptionsList.get(0);
		if(!placeOption.value) return;
		EntityLivingBase entityplayer = CombatUtils.getBestPlayerByDistance();
		if(!CombatUtils.safetyCheck(entityplayer)) return;
		float f = mc.thePlayer.getDistanceToEntity(entityplayer);
		if (f < 8) {
			currentEntity = entityplayer;
			int size = 2;
			int sizeOther = Math.round(size / 2);
			double blockX;
			double blockY;
			double blockZ;
			ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
			IntOption delayOption = (IntOption) crystalAuraOptions.moduleOptionsList.get(1);
			if(delayOption.value < delay ) {
				for(int x = -size; x < size + sizeOther; x++){
					for(int z = -size; z < size + sizeOther; z++){
						for(int y = -size; y < size + sizeOther; y++){
							boolean shouldBreakBlock = true;
		        			blockX = (int) (currentEntity.posX + x);
		        			blockY = (int) (currentEntity.posY + y);
		        			blockZ = (int) (currentEntity.posZ + z);
		        			if (Block.getIdFromBlock(mc.theWorld.getBlockState(new BlockPos(blockX, blockY, blockZ)).getBlock()) == 49 || Block.getIdFromBlock(mc.theWorld.getBlockState(new BlockPos(blockX, blockY, blockZ)).getBlock()) == 7){
		        				if(Block.getIdFromBlock(mc.theWorld.getBlockState(new BlockPos(blockX, blockY + 1, blockZ)).getBlock()) == 0
		        					&& Block.getIdFromBlock(mc.theWorld.getBlockState(new BlockPos(blockX, blockY + 2, blockZ)).getBlock()) == 0){
			        				if(mc.thePlayer.getDistance(blockX, blockY, blockZ) < 5 && blockY-0.3 <= currentEntity.posY) {
		        						blocks.add(new BlockPos(blockX, blockY, blockZ));
			        				}
		        				}
		         			}
		 				}
		 			}
		 		}
				BlockPos bestBlock = BlockUtils.getClosestBlockToEntityFix(blocks, entityplayer);
				if(BlockUtils.getClosestBlockToEntityFix(blocks, entityplayer) != null) {
					if(!CombatUtils.isCrystalThere(bestBlock.getX(), bestBlock.getY(), bestBlock.getZ())) {
						//PlayerUtils.CFaceBlock(bestBlock.getX(), bestBlock.getY(), bestBlock.getZ());
    					mc.thePlayer.connection.sendPacket(new CPacketPlayerTryUseItemOnBlock(new BlockPos(bestBlock.getX(), bestBlock.getY(), bestBlock.getZ()), EnumFacing.UP, EnumHand.MAIN_HAND, bestBlock.getX(), bestBlock.getY(), bestBlock.getZ()));
    					return;
					}else {
						CombatUtils.CAttackEntity(CombatUtils.getBlocksCrystal(bestBlock.getX(), bestBlock.getY(), bestBlock.getZ()));
					}
				}
				delay = 0;
			}
		}else {
			currentEntity = null;
		}
	}
		
	public void onRender() {
		super.onRender();
		
		if(currentEntity != null) {
			RenderUtils.drawESP(currentEntity, 1, 0, 0, 1);
			int size = 3;
			int sizeOther = Math.round(size / 2);
			double blockX;
			double blockY;
			double blockZ;
			ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
			for(int x = -size; x < size + sizeOther; x++){
				for(int z = -size; z < size + sizeOther; z++){
					for(int y = -size; y < size + sizeOther; y++){
						boolean shouldBreakBlock = true;
	        			blockX = (int) (currentEntity.posX + x);
	        			blockY = (int) (currentEntity.posY + y);
	        			blockZ = (int) (currentEntity.posZ + z);
	        			if (Block.getIdFromBlock(mc.theWorld.getBlockState(new BlockPos(blockX, blockY, blockZ)).getBlock()) == 49 || Block.getIdFromBlock(mc.theWorld.getBlockState(new BlockPos(blockX, blockY, blockZ)).getBlock()) == 7){
	        				if(Block.getIdFromBlock(mc.theWorld.getBlockState(new BlockPos(blockX, blockY + 1, blockZ)).getBlock()) == 0
	        					&& Block.getIdFromBlock(mc.theWorld.getBlockState(new BlockPos(blockX, blockY + 2, blockZ)).getBlock()) == 0){
		        				if(mc.thePlayer.getDistance(blockX, blockY, blockZ) < 5 && blockY-0.3 <= currentEntity.posY) {
	        						blocks.add(new BlockPos(blockX, blockY, blockZ));
		        				}
	        				}
	         			}
	 				}
	 			}
	 		}
			BlockPos bestBlock = BlockUtils.getClosestBlockToEntityFix(blocks, currentEntity);
			if(BlockUtils.getClosestBlockToEntityFix(blocks, currentEntity) != null) {
				if(!CombatUtils.isCrystalThere(bestBlock.getX(), bestBlock.getY(), bestBlock.getZ())) {
					RenderUtils.drawESPBox(bestBlock.getX(), bestBlock.getY(), bestBlock.getZ(), 0, 1, 0, 1f);
					return;
				}else {
					RenderUtils.drawESPBox(bestBlock.getX(), bestBlock.getY(), bestBlock.getZ(), 1, 0, 0, 1f);
				}
			}	
		}
	}
}
