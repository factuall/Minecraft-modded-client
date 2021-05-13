package factuall.fiber.modules.list;

import org.lwjgl.input.Keyboard;

import factuall.fiber.Fiber;
import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import factuall.fiber.options.FloatOption;
import factuall.fiber.options.ModuleOptions;
import factuall.fiber.utils.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class AntiKnockback extends Module{


	private static ModuleOptions AntiKnockbackOptions = new ModuleOptions();
	
	public AntiKnockback() {
		super("AntiKnockback", Category.MOVEMENT, "",
				Keyboard.KEY_NONE, AntiKnockbackOptions);
		
		AntiKnockbackOptions.addOption(new FloatOption(0f, "multiplierXZ", "Horizontal knockback multiplier (0-1 none-normal)"));
		AntiKnockbackOptions.addOption(new FloatOption(0f, "multiplierY", "Horizontal knockback multiplier (0-1 none-normal)"));
		// TODO Auto-generated constructor stub
	}
	
	double posX;
	double posY;
	double posZ;
	
	@Override
	public void onPostUpdate() {
		super.onPostUpdate();
		
		
		if(mc.thePlayer.hurtTime > 0) {
			FloatOption multiplierXZ = (FloatOption)AntiKnockbackOptions.moduleOptionsList.get(0);
			FloatOption multiplierY = (FloatOption)AntiKnockbackOptions.moduleOptionsList.get(1);
			mc.thePlayer.posX = posX;
			mc.thePlayer.posY = posY;
			mc.thePlayer.posZ = posZ;
			
			mc.thePlayer.motionX = 0;
			mc.thePlayer.motionZ = 0;
			
			mc.thePlayer.motionY = 0;
			mc.thePlayer.hurtTime = 0;
		}else {
			
			posX = mc.thePlayer.posX;
			posY = mc.thePlayer.posY;
			posZ = mc.thePlayer.posZ;
		}
		
		//mc.thePlayer.performHurtAnimation();
	}
	

}
