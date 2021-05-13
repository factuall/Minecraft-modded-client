package factuall.fiber.modules.list;

import java.util.List;

import org.lwjgl.input.Keyboard;

import factuall.fiber.modules.Category;
import factuall.fiber.modules.Module;
import factuall.fiber.options.FloatOption;
import factuall.fiber.options.IntOption;
import factuall.fiber.options.ModuleOptions;
import factuall.fiber.utils.CombatUtils;
import factuall.fiber.utils.PlayerUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;

public class KillAura extends Module{

	private static ModuleOptions killAuraOptions = new ModuleOptions();
	
	
	public KillAura() {
		super("KillAura", Category.COMBAT, "Wpierdol",
				Keyboard.KEY_R, killAuraOptions);
		// TODO Auto-generated constructor stub
		
		killAuraOptions.addOption(new IntOption(13, "Delay", "Delay betwen each attack"));
		killAuraOptions.addOption(new FloatOption(4f, "Range", "Range in wich you can reach players"));
	}
	
	int delay;
	
	
	
	@Override
	public void onPreUpdate() {
		super.onPreUpdate();
		List list = mc.theWorld.playerEntities;
		delay++;

		IntOption delayOption = (IntOption)killAuraOptions.moduleOptionsList.get(0);
		FloatOption rangeOption = (FloatOption)killAuraOptions.moduleOptionsList.get(1);
		
		EntityLivingBase entityplayer = CombatUtils.getBestPlayerByDistance();
		if(!CombatUtils.safetyCheck(entityplayer)) return;
		
		float f = mc.thePlayer.getDistanceToEntity(entityplayer);
			

		if (f < rangeOption.value && mc.thePlayer.canEntityBeSeen(entityplayer) && entityplayer.posY < mc.thePlayer.posY + 2) {

			//PlayerUtils.CFaceEntity(entityplayer);

			//TODO PACKET AURA OPTION AND MORE OPTIONS
			//PlayerUtils.CFaceEntity(entityplayer);
			if(delay > delayOption.value && entityplayer.posY < mc.thePlayer.posY + 2) {
				//mc.thePlayer.connection.sendPacket(new SPacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, getRotationsNeeded(entityplayer)[0], getRotationsNeeded(entityplayer)[1], null, 0));
				
				PlayerUtils.PFaceEntity(entityplayer);
				CombatUtils.CAttackEntity(entityplayer);
				delay = 0;
			}
		}
		//Fiber.addChatMessage(Integer.toString(delayOption.value) + " " + Float.toString(rangeOption.value));
	}
	



}
