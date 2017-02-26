package thatmartinguy.thedarkness.proxy;

import net.minecraft.entity.player.EntityPlayer;

public interface IProxy
{
	public void preInit();
	
	public void init();
	
	public void postInit();
	
	public EntityPlayer getClientPlayer();
}
