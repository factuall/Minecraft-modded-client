package factuall.fiber.modules.list;

import java.util.UUID;

import org.lwjgl.input.Keyboard;

import com.mojang.authlib.GameProfile;

import factuall.fiber.ConfigLoader;
import factuall.fiber.ConfigSaver;
import factuall.fiber.Fiber;
import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;

public class Freecam extends Module{

	public Freecam() {
		super("Freecam", Category.RENDER, "Allows you to fly out of your body, like in spectator mode",
				Keyboard.KEY_U, null);
		// TODO Auto-generated constructor stub
	}
	

	EntityOtherPlayerMP fp;
	
	private EntityOtherPlayerMP entity = null;
	private double freecamX, freecamY, freecamZ, freecamPitch, freecamYaw;
	
	@Override
	public void onPreUpdate() {
		super.onPreUpdate();
		

	}

	@Override
	public void onEnable() {
		super.onEnable();

		//Fiber.addChatMessage("Freecam is not working yet!");
		
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		
	}
	
	
}
