package factuall.fiber.modules.list;

import org.lwjgl.input.Keyboard;

import factuall.fiber.Fiber;
import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import factuall.fiber.options.IntOption;
import factuall.fiber.options.ModuleOptions;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;

public class AutoSoup extends Module{

	private static ModuleOptions autoSoupOptions = new ModuleOptions();
	
	public AutoSoup() {
		super("AutoSoup", Category.COMBAT, "Automatically eats soup when your health is low. /n (this hack ignores hunger and assumes that eating soup directly refills your health)",
				Keyboard.KEY_N, autoSoupOptions);
		// TODO Auto-generated constructor stub
		autoSoupOptions.addOption(new IntOption(7, "health", "Dupa"));

	}
	
	private int oldSlot = -1;
	private int delay = 0;

	@Override 
	public void onPostUpdate(){
		super.onPostUpdate();
		
		if(delay > 0) {
			for(int i = 0; i < 36 ; i++)
			{
				ItemStack slot = mc.thePlayer.inventory.getStackInSlot(i);
				
				if(slot == null) continue;
				
				if(slot.getItem() == Items.BOWL) {
					if(i < 9) {
						int u = i + 36;
						try {
							mc.playerController.windowClick(((GuiInventory)mc.currentScreen).inventorySlots.windowId, u, 1, ClickType.QUICK_MOVE, mc.thePlayer);
							break;
						}catch(Exception ex) {}
						
					}
				}
				
				if(!(isToolBarFull()) && slot.getItem() == Items.MUSHROOM_STEW){
					if(i > 8) {
						try {
							mc.playerController.windowClick(((GuiInventory)mc.currentScreen).inventorySlots.windowId, i, 0, ClickType.QUICK_MOVE, mc.thePlayer);
							break;
						}catch(Exception ex) {}
					}
				}
				
				
				
				
			}
			delay = 0;
		}else {
			delay++;
		}
		
		
		
		
	}
	
	@Override
	public void onPreUpdate() {
		super.onPreUpdate();
		
		boolean isSoupInHotbar = false;
		int soupInHotbar = 0;
		// search soup in hotbar
		
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = mc.thePlayer.inventory.getStackInSlot(i);
			if(stack != null && stack.getItem() instanceof ItemSoup) {
				isSoupInHotbar = true;
				soupInHotbar = i;
			}
			
		}
		
		if(isSoupInHotbar)
		{

			if(!shouldEatSoup())
			{
				stopIfEating();
				return;
			}
			
			if(oldSlot == -1)
				oldSlot = mc.thePlayer.inventory.currentItem;
			

			mc.thePlayer.inventory.currentItem = soupInHotbar;
			mc.gameSettings.keyBindUseItem.pressed = true;
			return;
		}
		stopIfEating();
	}
	
	private boolean isToolBarFull() {
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = mc.thePlayer.inventory.getStackInSlot(i);
				if(stack.getItem() instanceof ItemAir) return false;
		}
		return true;
	}
	
	private boolean shouldEatSoup()
	{
		IntOption healthOption = (IntOption) autoSoupOptions.moduleOptionsList.get(0);
		
		if(mc.thePlayer.getHealth() > healthOption.value * 2F)
			return false;
		
		return true;
	}

	private void stopIfEating()
	{
		// check if eating
		if(oldSlot == -1)
			return;
		
		// stop eating
		mc.gameSettings.keyBindUseItem.pressed = false;
		
		// reset slot
		mc.thePlayer.inventory.currentItem = oldSlot;
		oldSlot = -1;
	}


}
