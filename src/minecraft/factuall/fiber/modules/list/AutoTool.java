package factuall.fiber.modules.list;

import org.lwjgl.input.Keyboard;

import factuall.fiber.Fiber;
import factuall.fiber.call.AttackEntityCall;
import factuall.fiber.call.Call;
import factuall.fiber.call.ClickBlockCall;
import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class AutoTool extends Module{

	public AutoTool() {
		super("AutoTool", Category.PLAYER, "Automatically picks appropriate tool for block that you are currently mining",
				Keyboard.KEY_NONE, null);
		// TODO Auto-generated constructor stub
		
	}

    
	@Override
	public void onPreUpdate() {
		super.onPreUpdate();

		ItemStack currentItem = mc.thePlayer.getHeldItemMainhand();
		
		
		
		

		
		//autoArmor();
		
	}
	
	@Override
	public void onCall(Call call) {
		super.onCall(call);
		if(call.getClass() == AttackEntityCall.class) {
			ItemStack[] swords = new ItemStack[9];
			boolean haveSword = false;
			ItemStack[] axes = new ItemStack[9];
			boolean haveAxe = false;
			for(int slot = 0; slot < 9; slot++) {
				if(mc.thePlayer.inventory.getStackInSlot(slot).getUnlocalizedName().startsWith("item.sword")) {
					swords[slot] = mc.thePlayer.inventory.getStackInSlot(slot);
					haveSword = true;
				}
				if(mc.thePlayer.inventory.getStackInSlot(slot).getUnlocalizedName().startsWith("item.hatchet")) {
					axes[slot] = mc.thePlayer.inventory.getStackInSlot(slot);
					haveAxe = true;
				}
			}
			int best = 0;
			int bestID = 0;
			if(haveSword) {
				for(int slot = 0; slot < 9; slot++) {
					if(swords[slot] != null) {
						if(swords[slot].getMaxDamage() > best) {
							best = swords[slot].getMaxDamage();
							bestID = slot;
						}
					}

				}
			}else if(haveAxe){
				for(int slot = 0; slot < 9; slot++) {
					if(axes[slot] != null) {
						if(axes[slot].getMaxDamage() > best) {
							best = axes[slot].getMaxDamage();
							bestID = slot;
						}
					}
				}
			}else {
				for(int slot = 0; slot < 9; slot++) {
					if(mc.thePlayer.inventory.getStackInSlot(slot) != null) {
						if(mc.thePlayer.inventory.getStackInSlot(slot).getMaxDamage() > best) {
							best = mc.thePlayer.inventory.getStackInSlot(slot).getMaxDamage();
							bestID = slot;
						}
					}

				}
			}
			mc.thePlayer.inventory.currentItem = bestID;
		}
		if(call.getClass() == ClickBlockCall.class) {
			ClickBlockCall cbCall = (ClickBlockCall) call;
			Block block = mc.theWorld.getBlockState(cbCall.loc).getBlock();
	        int slot = mc.thePlayer.inventory.currentItem;
	        float dmg = 0.1F;
	        for (int index = 36; index < 45; index++) {
	            ItemStack itemStack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
	            if (itemStack != null && block != null && itemStack.getItem().getStrVsBlock(itemStack, block.getDefaultState()) > dmg) {
	                slot = index - 36;
	                dmg = itemStack.getItem().getStrVsBlock(itemStack, block.getDefaultState());
	            }
	        }
	        if (dmg > 0.1F) mc.thePlayer.inventory.currentItem = slot;
	        
	        
		}

	}


}
