package factuall.fiber;

import java.awt.Font;

import factuall.fiber.call.Call;
import factuall.fiber.font.FontUtils;
import factuall.fiber.modules.Module;
import factuall.fiber.modules.ModuleManager;
import factuall.fiber.ui.Rainbow;
import factuall.fiber.ui.UIRenderer;
import factuall.fiber.utils.LoginUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class Fiber {
	
	public static String clientName = "fiber";
	public static String clientVersion = " b1 private";
	public static String commandPrefix = ".";
	
	public static UIRenderer uiRenderer;
	public static ModuleManager moduleManager;
	public static TabManager tabManager;
	public static Rainbow rainbow;
	public static CommandManager commandManager;
	
	public static FontUtils fu_default = new FontUtils("Microsoft New Tai Lue", Font.PLAIN, 22);
	public static FontUtils fu_big = new FontUtils("Microsoft New Tai Lue", Font.PLAIN, 100);
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void init() {
		//mc.thePlayer.gameProfile = new GameProfile(mc.thePlayer.getUUID(mc.thePlayer.gameProfile), "factuall");
		
		uiRenderer = new UIRenderer();
		
		moduleManager = new ModuleManager();
		moduleManager.init();
		
		tabManager = new TabManager();
		tabManager.init();
		
		rainbow = new Rainbow();
		rainbow.init();
		
		commandManager = new CommandManager();
		
		try {
			moduleManager.loadModules(ConfigLoader.loadActiveModules());
		}catch(Exception ex) {
			
		}

		LoginUtils.changeCrackedName("kozakvlop");
		//LoginUtils.changeCrackedName("AdjnGHkerAvnEjpd");
		//LoginUtils.loginAlt("username", "password");
	}

	public static void onGui() {
		uiRenderer.draw();
		uiRenderer.drawTabGui();
	}
	
	public static void onRender() {
		for(Module module : moduleManager.getEnabledModules()) {
			module.onRender();
		}
		rainbow.RainbowCycle();
	}
	
	public static void  onPreUpdate() {
		Minecraft.getMinecraft().gameSettings.limitFramerate = 60;
		for(Module module : moduleManager.getEnabledModules()) {
			module.onPreUpdate();
		}
	}
	
	public static void onPostUpdate() {
		for(Module module : moduleManager.getEnabledModules()) {
			module.onPostUpdate();
		}
	}
	
	
	public static void onKeyPressed(int keyCode) {
		for(Module module : moduleManager.moduleList) {
			module.onKeyPressed(keyCode);
		}
		tabManager.keyPressed(keyCode);
	}
	
	public static void addChatMessage(String msg) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new TextComponentString("§5[fiber]§b " + msg));
	}
	
	//EntityPlayerSP.java hooked
	public static boolean onChatMessage(String msg) {
		if(msg.startsWith(commandPrefix)) {
			commandManager.onCommand(msg);
			return false;
		}
		return true;
	}
	
	public static void call(Call call) {
		for(Module module : moduleManager.getEnabledModules()) {
			module.onCall(call);
		}
	}
	
}
