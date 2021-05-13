package factuall.fiber.modules.list;

import org.lwjgl.input.Keyboard;

import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;

public class Flight extends Module{

	public Flight() {
		super("Flight", Category.MOVEMENT, "Makes your character fly",
				Keyboard.KEY_G, null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onPreUpdate() {
		super.onPreUpdate();
		

		if(mc.gameSettings.keyBindJump.isKeyDown()) {
			mc.thePlayer.motionY=0.5f;
		}else if (mc.gameSettings.keyBindSneak.isKeyDown()) {
			mc.thePlayer.motionY=-0.5f;
		}else {
			mc.thePlayer.motionY=0;
		}
		mc.thePlayer.onGround = true;
		mc.thePlayer.motionZ*= 1.7f;
		mc.thePlayer.motionX*= 1.7f;
		
			
	}

}
