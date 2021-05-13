package factuall.fiber.modules.list;

import org.lwjgl.input.Keyboard;

import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import net.minecraft.network.play.client.CPacketChatMessage;

public class Jesus extends Module{

	public Jesus() {
		super("Jesus", Category.MOVEMENT, "Basically Jesus...",
				Keyboard.KEY_J, null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onPreUpdate() {
		super.onPreUpdate();
		
		if(mc.thePlayer.isInWater()) {
			mc.thePlayer.motionY=0.03f;
			mc.thePlayer.setSprinting(false);
			if(mc.thePlayer.isCollidedHorizontally) mc.thePlayer.motionY=0.3f;
			
		
		}
	}

}
