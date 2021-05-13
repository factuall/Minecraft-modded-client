package factuall.fiber.modules;

import factuall.fiber.call.Call;
import factuall.fiber.options.*;
import net.minecraft.client.Minecraft;

public class Module{

	private String name;
	private String description;
	private Category category;
	private int keyCode;
	private ModuleOptions options;
	

	private boolean enabled;
	
	public Module(String name, Category category, String description, int keyCode, ModuleOptions options) {
		this.name = name;
		this.category = category;
		this.description = description;
		this.keyCode = keyCode;
		this.options = options;
	}
	
	protected Minecraft mc = Minecraft.getMinecraft();
	
	public String getName() {
		return this.name;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if(!isEnabled()) {
			onDisable();
		}
		if(isEnabled()) {
			onEnable();
		}
	}
	
	public  void onRender() {
		
	}
	
	public  void  onPreUpdate() {
		
	}
	
	public  void onPostUpdate() {
		
	}
	
	public void onEnable() {
		
	}
	
	public  void onKeyPressed(int keyCode) {
		if(this.keyCode == keyCode) {
			this.toggle();
		}
	}
	
	public void onDisable() {
		
	}
	
	public void toggle() {
		setEnabled(!isEnabled());

	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public void onCall(Call call) {
		
	}
	
	public ModuleOptions getModuleOptions() {
		return options;
	}
	
}
