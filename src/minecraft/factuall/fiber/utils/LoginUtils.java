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

public class LoginUtils {

    public static String loginAlt(String email, String password) {

        YggdrasilAuthenticationService authenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication) authenticationService.createUserAuthentication(Agent.MINECRAFT);
        authentication.setUsername(email);
        authentication.setPassword(password);
        String displayText = null;

        try {
            authentication.logIn();
            Minecraft.getMinecraft().session = new Session(authentication.getSelectedProfile().getName(), authentication.getSelectedProfile().getId().toString(), authentication.getAuthenticatedToken(), "mojang");
            displayText = "";
        } catch (AuthenticationUnavailableException e) {
            displayText = "§4§lCannot contact authentication server!";
        } catch (AuthenticationException e)    // wrong password account migrated
        {
            if (e.getMessage().contains("Invalid username or password.") || e.getMessage().toLowerCase().contains("account migrated")) {
                displayText = "§4§lWrong password!";
            } else {
                displayText = "§4§lCannot contact authentication server!";
            }

            
        } catch (NullPointerException e) {
            displayText = "§4§lWrong password!";
        }

        return displayText;
    }
    
    public static void changeCrackedName(String name) {

        Fiber.mc.session = new Session(name, "", "", "mojang");
    }
	
	
}
