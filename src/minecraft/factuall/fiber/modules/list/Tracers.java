package factuall.fiber.modules.list;

import org.lwjgl.input.Keyboard;

import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import factuall.fiber.options.*;
import factuall.fiber.utils.RenderUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class Tracers extends Module{

	private static ModuleOptions tracersOptions = new ModuleOptions();
	
	public Tracers() {
		super("Tracers", Category.RENDER, "Draws a line from your crosshair to all entities",
				Keyboard.KEY_NONE, tracersOptions);
		// TODO Auto-generated constructor stub
		
		tracersOptions.addOption(new FloatOption(2f, "width", "Width of line to draw"));
		tracersOptions.addOption(new FloatOption(1f, "red", "RGB - Red of line"));
		tracersOptions.addOption(new FloatOption(1f, "green", "RGB - Green of line"));
		tracersOptions.addOption(new FloatOption(1f, "blue", "RGB - Blue of line"));
		tracersOptions.addOption(new FloatOption(0.5f, "alpha", "Trasparency of line"));
	}
	
	@Override
	public void onRender() {
		super.onRender();
		
		for(Object theObject : mc.theWorld.loadedEntityList) {
			if(!(theObject instanceof EntityLivingBase))
				continue;
			
			EntityLivingBase entity = (EntityLivingBase) theObject;
			
			if(entity instanceof EntityPlayer) {
				if(entity != mc.thePlayer)
				{
					FloatOption widthOption = (FloatOption)tracersOptions.moduleOptionsList.get(0);
					FloatOption redOption = (FloatOption)tracersOptions.moduleOptionsList.get(1);
					FloatOption greenOption = (FloatOption)tracersOptions.moduleOptionsList.get(2);
					FloatOption blueOption = (FloatOption)tracersOptions.moduleOptionsList.get(3);
					FloatOption alphaOption = (FloatOption)tracersOptions.moduleOptionsList.get(4);
					RenderUtils.drawTracerLine(entity, redOption.value, greenOption.value, blueOption.value, alphaOption.value, widthOption.value);
				}
			}
		}
	}
}
