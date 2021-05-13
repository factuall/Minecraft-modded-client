package factuall.fiber.call;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class ClickBlockCall extends Call{

	public BlockPos loc;
	public EnumFacing face;
	
	
	public ClickBlockCall(BlockPos loc, EnumFacing face) {
		this.loc = loc;
		this.face = face;
	}
	
}
