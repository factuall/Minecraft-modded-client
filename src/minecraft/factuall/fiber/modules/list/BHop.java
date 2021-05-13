package factuall.fiber.modules.list;

import java.util.UUID;

import org.lwjgl.input.Keyboard;

import com.mojang.authlib.GameProfile;

import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;

public class BHop extends Module{

	public BHop() {
		super("BHop", Category.MOVEMENT, "Jump like bunny!",
				Keyboard.KEY_X, null);
		// TODO Auto-generated constructor stub
	}
	
	boolean canJump;
	
	@Override
	public void onPreUpdate() {
		super.onPreUpdate();

		mc.thePlayer.stepHeight = 50;
		
		if(mc.thePlayer.isInWater() || mc.thePlayer.isInLava() || mc.thePlayer.isOnLadder()) return;
		
		if(mc.gameSettings.keyBindForward.isKeyDown() && mc.thePlayer.onGround) {
			mc.thePlayer.jump();
		}
		mc.thePlayer.setSprinting(true);
		if(mc.thePlayer.isAirBorne) {
			mc.thePlayer.motionX*= 1.08f;
			mc.thePlayer.motionZ*= 1.08f;
		}

	}
	
	@Override
	public void onPostUpdate() {
		super .onPostUpdate();
		

		

		
	}

	@Override
	public void onDisable() {
		super .onDisable();
		
	}
}
