package factuall.fiber;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;

public class TabManager {

	public static int currentTab = 0;
	public static boolean tabExpanded = false;
	public String[] tabs = {"Combat", "Movement", "Render", "Player", "Misc"};
	public static ArrayList<Module> combatModules = new ArrayList<Module>();
	public static ArrayList<Module> movementModules = new ArrayList<Module>();
	public static ArrayList<Module> renderModules = new ArrayList<Module>();
	public static ArrayList<Module> playerModules = new ArrayList<Module>();
	public static ArrayList<Module> miscModules = new ArrayList<Module>();
	
	public static boolean reset;
	
	public static int currentCombatMod;
	public static int currentMovementMod;
	public static int currentRenderMod;
	public static int currentPlayerMod;
	public static int currentMiscMod;
	
	
	public void init() {
		for(Module module : Fiber.moduleManager.moduleList) {
			if(module.getCategory() == Category.COMBAT) combatModules.add(module);
			if(module.getCategory() == Category.MOVEMENT) movementModules.add(module);
			if(module.getCategory() == Category.RENDER) renderModules.add(module);
			if(module.getCategory() == Category.PLAYER) playerModules.add(module);
			if(module.getCategory() == Category.MISC) miscModules.add(module);
		}
	}
	
	
	public static void keyPressed(int keyCode) {
		if(tabExpanded) {
			if(keyCode == Keyboard.KEY_LEFT) tabExpanded = false;
			if(keyCode == Keyboard.KEY_DOWN && currentTab == 0 && currentCombatMod < combatModules.size() -1) {
				currentCombatMod++;
			}
			if(keyCode == Keyboard.KEY_UP && currentTab == 0 && currentCombatMod > 0) {
				currentCombatMod--;
			}
			
			if(keyCode == Keyboard.KEY_DOWN && currentTab == 1 && currentMovementMod < movementModules.size() -1) {
				currentMovementMod++;
			}
			if(keyCode == Keyboard.KEY_UP && currentTab == 1 && currentMovementMod > 0) {
				currentMovementMod--;
			}
			
			if(keyCode == Keyboard.KEY_DOWN && currentTab == 2 && currentRenderMod < renderModules.size() -1) {
				currentRenderMod++;
			}
			if(keyCode == Keyboard.KEY_UP && currentTab == 2 && currentRenderMod > 0) {
				currentRenderMod--;
			}
			
			if(keyCode == Keyboard.KEY_DOWN && currentTab == 3 && currentPlayerMod < playerModules.size() -1) {
				currentPlayerMod++;
			}
			if(keyCode == Keyboard.KEY_UP && currentTab == 3 && currentPlayerMod > 0) {
				currentPlayerMod--;
			}
			
			if(keyCode == Keyboard.KEY_DOWN && currentTab == 4 && currentMiscMod < miscModules.size() -1) {
				currentMiscMod++;
			}
			if(keyCode == Keyboard.KEY_UP && currentTab == 4 && currentMiscMod > 0) {
				currentMiscMod--;
			}
			
			if(keyCode == Keyboard.KEY_RIGHT && currentTab == 0) combatModules.get(currentCombatMod).toggle();
			if(keyCode == Keyboard.KEY_RIGHT && currentTab == 1) movementModules.get(currentMovementMod).toggle();
			if(keyCode == Keyboard.KEY_RIGHT && currentTab == 2) renderModules.get(currentRenderMod).toggle();
			if(keyCode == Keyboard.KEY_RIGHT && currentTab == 3) playerModules.get(currentPlayerMod).toggle();
			if(keyCode == Keyboard.KEY_RIGHT && currentTab == 4) miscModules.get(currentMiscMod).toggle();
		}
		if(!tabExpanded) {
			if(keyCode == Keyboard.KEY_UP && currentTab > 0) currentTab--;
			if(keyCode == Keyboard.KEY_DOWN && currentTab < 4) currentTab++;
			if(keyCode == Keyboard.KEY_RIGHT) {
				tabExpanded = true;
				reset = true;
			}

		}
	}
	
}
