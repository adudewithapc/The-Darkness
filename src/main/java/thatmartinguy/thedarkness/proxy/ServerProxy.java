package thatmartinguy.thedarkness.proxy;

import net.minecraftforge.common.MinecraftForge;
import thatmartinguy.thedarkness.event.ServerEventHandler;

public class ServerProxy extends CommonProxy
{
	@Override
	public void preInit()
	{
		
	}
	
	@Override
	public void init()
	{
		ServerEventHandler serverEventHandler = new ServerEventHandler();
		MinecraftForge.EVENT_BUS.register(serverEventHandler);
	}
	
	@Override
	public void postInit()
	{
		
	}
}
