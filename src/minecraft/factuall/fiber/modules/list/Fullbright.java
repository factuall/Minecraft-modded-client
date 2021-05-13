package factuall.fiber.modules.list;

import org.lwjgl.input.Keyboard;

import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;

public class Fullbright extends Module{

	public Fullbright() {
		super("Fullbright", Category.RENDER, "Let the light shine in!",
				Keyboard.KEY_P, null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onRender() {
		super.onRender();
		
		mc.gameSettings.gammaSetting += (100 - mc.gameSettings.gammaSetting) / 100;
	}
	
	@Override
	public void onDisable() {
		super .onDisable();
		
		mc.gameSettings.gammaSetting = 0;
	}
	
}
