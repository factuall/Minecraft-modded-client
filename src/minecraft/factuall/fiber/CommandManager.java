package factuall.fiber;

import java.net.InetAddress;

import factuall.fiber.modules.Module;
import factuall.fiber.options.*;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketChatMessage;

public class CommandManager {

	public void init() {
		Fiber.addChatMessage("[fiber] CommandManager started");
	}
	
	public void onCommand(String cmd) {
		
		String[] cmdArgs = cmd.split(" ");
		cmdArgs[0] = cmdArgs[0].substring(1);
		
		switch(cmdArgs[0]) {
		case "say":
			String message = "";
			for(int i = 1; i < cmdArgs.length; i++) {
				message += cmdArgs[i] + " ";
			}
			Minecraft.getMinecraft().thePlayer.connection.sendPacket(new CPacketChatMessage(message));
			break;
		case "toggle":
			boolean b = false;
			try {
				String s = cmdArgs[1];
			}catch(Exception ex) {
				Fiber.addChatMessage("Insert module to toggle!");
				break;
			}
			for(Module module : Fiber.moduleManager.moduleList) {
				if(module.getName().equalsIgnoreCase(cmdArgs[1])) {
					module.toggle();
					Fiber.addChatMessage(module.getName() + " toggled!");
					b = true;
					break;
				}
			}
			if(!b) Fiber.addChatMessage("Module '" + cmdArgs[1] + "' doesn't exist...");
			break;
		case "t":
			boolean bb = false;
			try {
				String s = cmdArgs[1];
			}catch(Exception ex) {
				Fiber.addChatMessage("Insert module to toggle!");
				break;
			}
			for(Module module : Fiber.moduleManager.moduleList) {
				if(module.getName().equalsIgnoreCase(cmdArgs[1])) {
					module.toggle();
					Fiber.addChatMessage(module.getName() + " toggled!");
					bb = true;
					break;
				}
			}
			if(!bb) Fiber.addChatMessage("Module '" + cmdArgs[1] + "' doesn't exist...");
			break;
		case "option":
			boolean c = false;
			try {
				String mod = cmdArgs[1];
				String opt = cmdArgs[2];
				String val = cmdArgs[3];
			}catch(Exception ex) {
				Fiber.addChatMessage("Wrong syntax!");
				break;
			}
			for(Module module : Fiber.moduleManager.moduleList) {
				if(module.getName().equalsIgnoreCase(cmdArgs[1])) {
					for(Option option : module.getModuleOptions().moduleOptionsList) {
						if(option.optionName.equalsIgnoreCase(cmdArgs[2])) {
							try {
								IntOption iOption = (IntOption)option;
								iOption.value = Integer.parseInt(cmdArgs[3]);
							}catch(Exception ex){
								
							}
							
							try {
								FloatOption fOption = (FloatOption)option;
								fOption.value = Float.parseFloat(cmdArgs[3]);
							}catch(Exception ex){
								
							}
							
							try {
								BooleanOption bOption = (BooleanOption)option;
								bOption.value = Boolean.parseBoolean(cmdArgs[3]);
							}catch(Exception ex){
								
							}
							
							Fiber.addChatMessage(module.getName() + "'s " + option.optionName + " option is now set to: " + cmdArgs[3]);
						}
					}
					c = true;
					break;
				}
			}
			break;
		default:
			Fiber.addChatMessage("Command '" + cmdArgs[0] + "' doesn't exist...");
			break;
		}
	}
	
}
