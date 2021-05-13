package factuall.fiber.call;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class AttackEntityCall extends Call{

	public EntityPlayer playerIn;
	public Entity targetEntity;
	
	
	public AttackEntityCall(EntityPlayer playerIn, Entity targetEntity) {
		this.playerIn = playerIn;
		this.targetEntity = targetEntity;
	}
	
}
