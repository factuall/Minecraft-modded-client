package factuall.fiber.modules.list;

import org.lwjgl.input.Keyboard;

import factuall.fiber.Fiber;
import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import factuall.fiber.options.*;
import factuall.fiber.utils.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class ESP extends Module{


	private static ModuleOptions ESPOptions = new ModuleOptions();
	
	public ESP() {
		super("ESP", Category.RENDER, "",
				Keyboard.KEY_NONE, ESPOptions);
		
		ESPOptions.addOption(new FloatOption(1f, "playerRed", "RGB - Red of ESP"));
		ESPOptions.addOption(new FloatOption(0.5f, "playerGreen", "RGB - Green of ESP"));
		ESPOptions.addOption(new FloatOption(0.5f, "playerBlue", "RGB - Blue of ESP"));
		ESPOptions.addOption(new FloatOption(0.5f, "playerAlpha", "Trasparency of ESP"));
		ESPOptions.addOption(new BooleanOption(true, "showPlayers", "Draw ESPs on players"));
		
		ESPOptions.addOption(new FloatOption(1f, "mobsRed", "RGB - Red of ESP"));
		ESPOptions.addOption(new FloatOption(1f, "mobsGreen", "RGB - Green of ESP"));
		ESPOptions.addOption(new FloatOption(1f, "mobsBlue", "RGB - Blue of ESP"));
		ESPOptions.addOption(new FloatOption(0.5f, "mobsAlpha", "Trasparency of ESP"));
		ESPOptions.addOption(new BooleanOption(true, "showMobs", "Draw ESPs on mobs"));
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onRender() {
		super.onRender();
		
		for(Object theObject : mc.theWorld.loadedEntityList) {
			if(!(theObject instanceof EntityLivingBase))
				continue;
			
			EntityLivingBase entity = (EntityLivingBase) theObject;
			
			BooleanOption playersOption = (BooleanOption)ESPOptions.moduleOptionsList.get(4);
			BooleanOption mobsOption = (BooleanOption)ESPOptions.moduleOptionsList.get(9);
			
			if(mobsOption.value && !(entity instanceof EntityPlayer)) {
				if(entity != mc.thePlayer)
				{
					FloatOption redOption = (FloatOption)ESPOptions.moduleOptionsList.get(5);
					FloatOption greenOption = (FloatOption)ESPOptions.moduleOptionsList.get(6);
					FloatOption blueOption = (FloatOption)ESPOptions.moduleOptionsList.get(7);
					FloatOption alphaOption = (FloatOption)ESPOptions.moduleOptionsList.get(8);
					//Fiber.addChatMessage(entity.getName());
					RenderUtils.drawESP(entity, redOption.value, greenOption.value, blueOption.value, alphaOption.value);
				}
				continue;
			}
			
			
			if(entity instanceof EntityPlayer && playersOption.value) {
				if(entity != mc.thePlayer)
				{
					FloatOption redOption = (FloatOption)ESPOptions.moduleOptionsList.get(0);
					FloatOption greenOption = (FloatOption)ESPOptions.moduleOptionsList.get(1);
					FloatOption blueOption = (FloatOption)ESPOptions.moduleOptionsList.get(2);
					FloatOption alphaOption = (FloatOption)ESPOptions.moduleOptionsList.get(3);
					//Fiber.addChatMessage(entity.getName());
					RenderUtils.drawESP(entity, redOption.value, greenOption.value, blueOption.value, alphaOption.value);
				}
			}
			
			
		
			
		}
	}
	

}
