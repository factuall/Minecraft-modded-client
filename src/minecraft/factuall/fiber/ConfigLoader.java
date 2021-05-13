package factuall.fiber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import factuall.fiber.modules.Module;

public class ConfigLoader {

	static String dataFolder = System.getenv("APPDATA");
	
	public static String loadActiveModules() {
		
		String modules = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataFolder + "\\.minecraft\\fiberConfigOne.txt"));
			
			modules = br.readLine();
			
			
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return modules;
	}
	
	
}
