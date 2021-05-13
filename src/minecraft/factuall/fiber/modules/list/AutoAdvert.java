package factuall.fiber.modules.list;

import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import factuall.fiber.Fiber;
import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.util.EnumHand;

public class AutoAdvert extends Module{

	double timeMessage;
	float delayMessage;
	boolean right;
	
	public AutoAdvert() {
		super("AutoAdvert", Category.MISC, "fiber client good",
				Keyboard.KEY_NONE, null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		//mc.thePlayer.connection.sendPacket(new CPacketChatMessage("[FIBER CLIENT IS ALWAYS OWNING YOU]"));
		
		/*List list = Fiber.mc.theWorld.loadedEntityList;
		for (int k = 0; k < list.size(); k++) {
			if(((Entity)list.get(k)).getName().equals("entity.EnderCrystal.name")) {
				Fiber.addChatMessage(Double.toString(((Entity)list.get(k)).posX-0.5));
				Fiber.addChatMessage(Double.toString(((Entity)list.get(k)).posY));
				Fiber.addChatMessage(Double.toString(((Entity)list.get(k)).posZ-0.5));
			}
			
		}
		*/
		mc.thePlayer.connection.sendPacket(new CPacketChatMessage("FIBER CLIENT IS ALWAYS OWNING YOU!"));
		this.toggle();
		
	}
	
	
	@Override
	public void onPreUpdate() {
		super.onPreUpdate();
		
		
		Minecraft.getMinecraft().thePlayer.connection.sendPacket(new CPacketChatMessage("d00pa"));

	}

	@Override
	public void onDisable() {
		super.onDisable();
		
	}
}
