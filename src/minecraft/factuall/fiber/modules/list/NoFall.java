package factuall.fiber.modules.list;

import org.lwjgl.input.Keyboard;

import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayer;

public class NoFall extends Module{

	public NoFall() {
		super("NoFall", Category.MOVEMENT, "",
				Keyboard.KEY_N, null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onPreUpdate() {
		super.onPreUpdate();

		if(mc.thePlayer.fallDistance != 0){
			mc.thePlayer.connection.sendPacket(new CPacketPlayer(true));
			//Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new CPacketPlayer(true));
		}
	}

}
