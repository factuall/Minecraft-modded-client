package factuall.fiber.modules.list;

import org.lwjgl.input.Keyboard;

import factuall.fiber.Fiber;
import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;

public class Sprint extends Module{

	public Sprint() {
		super("Sprint", Category.MOVEMENT, "Makes your character sprint automatically",
				Keyboard.KEY_V, null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onPreUpdate() {
		super.onPreUpdate();

		if(mc.thePlayer.movementInput.moveForward > 0) {
			mc.thePlayer.setSprinting(true);
		}
	}

	public void onEnable() {
		super.onEnable();
		
		
	}
	
}
