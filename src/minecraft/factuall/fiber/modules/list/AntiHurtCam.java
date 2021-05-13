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

public class AntiHurtCam extends Module{

	public AntiHurtCam() {
		super("AntiHurtCam", Category.RENDER, "Disables hurt cam effect",
				Keyboard.KEY_NONE, null);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void onEnable() {
		super.onEnable();
	}

	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	
}
