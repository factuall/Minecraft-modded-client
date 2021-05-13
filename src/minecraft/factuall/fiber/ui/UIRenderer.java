package factuall.fiber.ui;

import java.awt.Color;
import java.text.DecimalFormat;

import factuall.fiber.Fiber;
import factuall.fiber.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class UIRenderer extends GuiScreen {

	private long timeSinceStart = System.nanoTime() / 1000;
	private long oldTimeSinceStart = 0;
	public static double deltaTime = 1;
	public static int color;
	
	DecimalFormat df = new DecimalFormat("#############.0");
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public void draw() {
		color = 0xffbb4deb;
		
		
		timeSinceStart = System.nanoTime() / 10000;
		deltaTime = timeSinceStart - oldTimeSinceStart;
		oldTimeSinceStart = timeSinceStart;
		deltaTime /= 100;
		
		Fiber.rainbow.RainbowCycle();
		
		ScaledResolution dr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRendererObj;
		
		
		drawGradientRect(2, 2, 79, 14, 0x61000000, 0x00000000);
		Fiber.fu_default.drawStringWithShadow(Fiber.clientName + Fiber.clientVersion, 4, 2, Fiber.rainbow.getRainbow().hashCode());
		//Fiber.fu_default.drawStringWithShadow(Fiber.clientName, 4, 4, 0x80ffffff);

		//Fiber.fu_default.drawStringWithShadow(Integer.toString(Fiber.tabManager.currentTab), 40, 4, 0xffffff);
		//Fiber.fu_default.drawStringWithShadow(Fiber.tabManager.tabExpanded ? "expanded" : "not expanded", 50, 4, 0xffffff);
		
		int i = 0;
		for(Module module : Fiber.moduleManager.getEnabledModules()) {

			Fiber.fu_default.drawStringWithShadow(module.getName(), dr.getScaledWidth()-Fiber.fu_default.getWidth(module.getName())-4, 4+i*12, Fiber.rainbow.getRainbowWithOffset(i*30).hashCode());
			i++;
			
		}
		
		String coordsNether;
		
		String coords = df.format(mc.thePlayer.posX) + " " + 
				df.format(mc.thePlayer.posY) + " " + 
				df.format(mc.thePlayer.posZ);
		
		if(mc.thePlayer.dimension == 0) {
			coordsNether = "Nether: " + df.format(mc.thePlayer.posX/8) + " " + 
					df.format(mc.thePlayer.posY/8) + " " + 
					df.format(mc.thePlayer.posZ/8);
		}else {
			coordsNether = "Overworld: " + df.format(mc.thePlayer.posX*8) + " " + 
					df.format(mc.thePlayer.posY*8) + " " + 
					df.format(mc.thePlayer.posZ*8);
		}

		Fiber.fu_default.drawStringWithShadow(coords, dr.getScaledWidth()-(Fiber.fu_default.getWidth(coords))-10, dr.getScaledHeight()-14, 0xffffff);
		Fiber.fu_default.drawStringWithShadow(coordsNether, dr.getScaledWidth()-(Fiber.fu_default.getWidth(coordsNether))-10, dr.getScaledHeight()-26, 0xffffff);
		
	}
	
	
	int categoryY;
	int combatTabY;
	int movementTabY;
	int renderTabY;
	int playerTabY;
	int miscTabY;
	
	int smoothCategoryY = 30;
	int smoothCombatTabY = 30;
	int smoothMovementTabY = 30;
	int smoothRenderTabY = 30;
	int smoothPlayerTabY = 30;
	int smoothMiscTabY = 30;
	
	public void drawTabGui() {

		smoothCategoryY += (categoryY - smoothCategoryY) / 2;
		smoothCombatTabY += (combatTabY - smoothCombatTabY) / 2;
		smoothMovementTabY += (movementTabY - smoothMovementTabY) / 2;
		smoothRenderTabY += (renderTabY - smoothRenderTabY) / 2;
		smoothPlayerTabY += (playerTabY - smoothPlayerTabY) / 2;
		smoothMiscTabY += (miscTabY - smoothMiscTabY) / 2;
		
		
		categoryY = Fiber.tabManager.currentTab*12+17;
		combatTabY = Fiber.tabManager.currentCombatMod*12+17;
		movementTabY = Fiber.tabManager.currentMovementMod*12+17;
		renderTabY = Fiber.tabManager.currentRenderMod*12+17;
		playerTabY = Fiber.tabManager.currentPlayerMod*12+17;
		miscTabY = Fiber.tabManager.currentMiscMod*12+17;
		
		drawRect(2, 16, 58, 5*12+20, 0x62000000);
		for(int i = 0; i < Fiber.tabManager.tabs.length; i++) {
			
			Fiber.fu_default.drawStringWithShadow(Fiber.tabManager.tabs[i], 4, i*12+18, Fiber.rainbow.getRainbowWithOffset((i*30)).hashCode());
		}
		
		int mod = 0;
		if(Fiber.tabManager.tabExpanded) {
			switch(Fiber.tabManager.currentTab) {
			case 0:
				mod = 0;
				drawRect(58, 16, 130, Fiber.tabManager.combatModules.size()*12+20, 0x61000000);
				for(Module module : Fiber.tabManager.combatModules) {
					
					Fiber.fu_default.drawStringWithShadow(module.getName(), 58, mod*12+18, Fiber.rainbow.getRainbowWithOffset(150+(mod*30)).hashCode());
					mod++;
				}
				break;
			case 1:
				drawRect(58, 16, 130, Fiber.tabManager.movementModules.size()*12+20, 0x61000000);
				for(Module module : Fiber.tabManager.movementModules) {
					Fiber.fu_default.drawStringWithShadow(module.getName(), 58, mod*12+18, Fiber.rainbow.getRainbowWithOffset(150+(mod*30)).hashCode());
					mod++;
				}
				break;
			case 2:
				drawRect(58, 16, 118, Fiber.tabManager.renderModules.size()*12+20, 0x61000000);
				for(Module module : Fiber.tabManager.renderModules) {
					Fiber.fu_default.drawStringWithShadow(module.getName(), 58, mod*12+18, Fiber.rainbow.getRainbowWithOffset(150+(mod*30)).hashCode());
					mod++;
				}
				break;
			case 3:
				drawRect(58, 16, 118, Fiber.tabManager.playerModules.size()*12+20, 0x61000000);
				for(Module module : Fiber.tabManager.playerModules) {
					Fiber.fu_default.drawStringWithShadow(module.getName(), 58, mod*12+18, Fiber.rainbow.getRainbowWithOffset(150+(mod*30)).hashCode());
					mod++;
				}
				break;
			case 4:
				drawRect(58, 16, 118, Fiber.tabManager.miscModules.size()*12+20, 0x61000000);
				for(Module module : Fiber.tabManager.miscModules) {
					Fiber.fu_default.drawStringWithShadow(module.getName(), 58, mod*12+18, Fiber.rainbow.getRainbowWithOffset(150+(mod*30)).hashCode());
					mod++;
				}
				break;
			default:
				break;
			}
			
			
		}
		
		
		
		drawRect(2, smoothCategoryY, 3, smoothCategoryY+14, Fiber.rainbow.getRainbowWithOffset(smoothCategoryY*2).hashCode());
		//drawRect(2, smoothCategoryY, 55, smoothCategoryY+1, color);
		//drawRect(2, smoothCategoryY+13, 55, smoothCategoryY+14, color);
		if(!Fiber.tabManager.tabExpanded) {
			drawRect(57, smoothCategoryY, 58, smoothCategoryY+14, Fiber.rainbow.getRainbowWithOffset(smoothCategoryY*2).hashCode());
		}else {
			if(Fiber.tabManager.reset) {
				smoothCombatTabY = smoothCategoryY;
				smoothMovementTabY = smoothCategoryY;
				smoothRenderTabY = smoothCategoryY;
				smoothPlayerTabY = smoothCategoryY;
				smoothMiscTabY = smoothCategoryY;
				Fiber.tabManager.reset = false;
			}
			
			switch(Fiber.tabManager.currentTab) {
			case 0:
				drawRect(57, smoothCombatTabY, 58, smoothCombatTabY+14, Fiber.rainbow.getRainbowWithOffset(60+(smoothCombatTabY*2)).hashCode());
				break;
			case 1:
				drawRect(57, smoothMovementTabY, 58, smoothMovementTabY+14, Fiber.rainbow.getRainbowWithOffset(60+(smoothMovementTabY*2)).hashCode());
				break;
			case 2:
				drawRect(57, smoothRenderTabY, 58, smoothRenderTabY+14, Fiber.rainbow.getRainbowWithOffset(60+(smoothRenderTabY*2)).hashCode());
				break;
			case 3:
				drawRect(57, smoothPlayerTabY, 58, smoothPlayerTabY+14, Fiber.rainbow.getRainbowWithOffset(60+(smoothPlayerTabY*2)).hashCode());
				break;
			case 4:
				drawRect(57, smoothMiscTabY, 58, smoothMiscTabY+14, Fiber.rainbow.getRainbowWithOffset(60+(smoothMiscTabY*2)).hashCode());
				break;
			default:
				break;
			}
		}
		
	}
	
	
}
