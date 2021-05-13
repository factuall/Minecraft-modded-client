package factuall.fiber.modules.list;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import factuall.fiber.Fiber;
import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.util.EnumHand;

public class AntiAFK extends Module{

	double timeMessage;
	double timeTurn;
	double timeSwing;
	double timeUse;
	float delayMessage;
	float delayTurn;
	float delaySwing;
	float delayUse;
	boolean right;
	
	public AntiAFK() {
		super("AntiAFK", Category.MISC, "Performs actions that prevents you from being kicked",
				Keyboard.KEY_NONE, null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
			delayMessage = 10000000;
			timeMessage = 0;
		
	}
	
	
	@Override
	public void onPreUpdate() {
		super.onPreUpdate();

		Random r = new Random();
		
		if(timeMessage < delayMessage) {
			timeMessage += Fiber.uiRenderer.deltaTime;
		}else {
			delayMessage = 10000000 + r.nextInt(1000000);
			timeMessage = 0;
			mc.thePlayer.connection.sendPacket(new CPacketChatMessage(
					Integer.toString(r.nextInt(999999999) + 1000000000) 
					+ " [Fiber Client] " 
					+ Integer.toString(r.nextInt(999999999) + 1000000000)));
		}
		
		if(timeTurn < delayTurn / 2) {
			timeTurn += Fiber.uiRenderer.deltaTime;
		}else{
			delayTurn = 1000000 + r.nextInt(1000000);
			timeTurn = 0;
			right = r.nextInt(2) == 0 ? false : true;
		}
		
		if(timeSwing < delaySwing / 2) {
			timeSwing += Fiber.uiRenderer.deltaTime;
		}else{
			delaySwing = 1000000 + r.nextInt(1000000);
			timeSwing = 0;
			mc.thePlayer.swingArm(EnumHand.MAIN_HAND);
		}
		
		mc.gameSettings.keyBindForward.pressed = true;
		mc.gameSettings.autoJump = true;
		

		
		mc.thePlayer.rotationYaw += right? 6f : -6f;
	}

	@Override
	public void onDisable() {
		super.onDisable();
		
		mc.gameSettings.keyBindForward.pressed = false;
		mc.gameSettings.autoJump = false;
	}
}
