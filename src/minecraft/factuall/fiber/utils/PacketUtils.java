package factuall.fiber.utils;

import java.net.Proxy;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import factuall.fiber.Fiber;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class PacketUtils {

	
	private static float rotationYaw, rotationPitch;
	public static boolean overrideRotations = false;
    public static void overrideRotations(float rotationYawNew, float rotationPitchNew) {
    	rotationYaw = rotationYawNew;
    	rotationPitch = rotationPitchNew;
    	overrideRotations = true;
    }
    //EntityPlayerSP
	public static float[] overrittenRotations() {
		float[] rotations = {rotationYaw, rotationPitch};
		overrideRotations = false;
		return rotations;
	}
    
	
}
