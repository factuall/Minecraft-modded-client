package factuall.fiber.modules.list;

import org.lwjgl.input.Keyboard;

import factuall.fiber.Fiber;
import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class AutoArmor extends Module{

	public AutoArmor() {
		super("AutoArmor", Category.COMBAT, "Automatically puts on the best armor in your inventory",
				Keyboard.KEY_N, null);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void onPreUpdate() {
		super.onPreUpdate();

		autoArmor();
		
	}

    public void autoArmor() {
    	if(mc.currentScreen instanceof GuiInventory) {
			int[] bestArmorSlots = new int[4];
			int[] bestArmorValues = new int[4];
				
			for(int armorType = 0; armorType < 4; armorType++)
			{
				ItemStack oldArmor = mc.thePlayer.inventory.armorItemInSlot(armorType);
				if(oldArmor != null && oldArmor.getItem() instanceof ItemArmor)
				bestArmorValues[armorType] = ((ItemArmor)oldArmor.getItem()).damageReduceAmount;
					
				bestArmorSlots[armorType] = -1;
			}
				
			for(int slot = 0; slot < 36; slot++)
			{
				ItemStack stack = mc.thePlayer.inventory.getStackInSlot(slot);
				if(stack == null || !(stack.getItem() instanceof ItemArmor))
					continue;
					
					ItemArmor armor = (ItemArmor)stack.getItem();
					int armorType = armor.armorType.getIndex();
					int armorValue = armor.damageReduceAmount;
					
					if(armorValue > bestArmorValues[armorType])
					{
						bestArmorSlots[armorType] = slot;
						bestArmorValues[armorType] = armorValue;
					}
				}
				
				for(int armorType = 0; armorType < 4; armorType++)
				{
					int slot = bestArmorSlots[armorType];
					if(slot == -1)
						continue;
						
					ItemStack oldArmor = mc.thePlayer.inventory.armorItemInSlot(armorType);
					if(oldArmor == null || mc.thePlayer.inventory.getFirstEmptyStack() != -1)
					{
						if(slot < 9)
							slot += 36;
						
						mc.playerController.windowClick(((GuiInventory)mc.currentScreen).inventorySlots.windowId, 8 - armorType, 0, ClickType.QUICK_MOVE, mc.thePlayer);
						mc.playerController.windowClick(((GuiInventory)mc.currentScreen).inventorySlots.windowId, slot, 0, ClickType.QUICK_MOVE, mc.thePlayer);


						
						break;
					}
				}
    	}
    }



}
