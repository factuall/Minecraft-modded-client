package factuall.fiber;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import factuall.fiber.modules.Module;

public class ConfigSaver {

	static String dataFolder = System.getenv("APPDATA");
	
	public static void saveActiveModules(ArrayList<Module> moduleList) {
		
		String modules = "";
		
		for(Module module : moduleList) {
			modules += module.getName()+",";
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(dataFolder + "\\.minecraft\\fiberConfigOne.txt"));
			
			
			bw.write(modules);
			
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
