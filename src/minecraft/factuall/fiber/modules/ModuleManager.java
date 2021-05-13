package factuall.fiber.modules;

import java.util.ArrayList;

import factuall.fiber.modules.list.*;


public class ModuleManager {

	public ArrayList<Module> moduleList = new ArrayList<Module>();
	
	public void init() {
		moduleList.add(new AntiKnockback());
		moduleList.add(new AntiHurtCam());
		moduleList.add(new CrystalAura());
		moduleList.add(new AutoArmor());
		moduleList.add(new AutoAdvert());
		moduleList.add(new Scaffold());
		moduleList.add(new AutoSoup());
		moduleList.add(new AutoTool());
		moduleList.add(new Fullbright());
		moduleList.add(new Freecam());
		moduleList.add(new AntiAFK());
		moduleList.add(new KillAura());
		moduleList.add(new Tracers());
		moduleList.add(new Nuker());
		moduleList.add(new NoFall());
		moduleList.add(new Sprint());
		moduleList.add(new BHop());
		moduleList.add(new Flight());
		moduleList.add(new Jesus());
		moduleList.add(new ESP());

	}
	
	public ArrayList<Module> getEnabledModules(){
		ArrayList<Module> toggledModules = new ArrayList<Module>();
		for(Module module : moduleList) {
			if(module.isEnabled()) {
				toggledModules.add(module);
			}
		}
		return toggledModules;
	}
	
	public void loadModules(String modules) {
		String[] mods = modules.split(",");
		
		for(int i = 0; i < mods.length; i++) {
			for(Module module : moduleList) {
				if(module.getName().equals(mods[i])) module.toggle();
			}
		}
	}
	
}
