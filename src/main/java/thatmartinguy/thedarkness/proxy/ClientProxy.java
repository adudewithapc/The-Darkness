package thatmartinguy.thedarkness.proxy;

import thatmartinguy.thedarkness.client.ModModelManager;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit()
	{
		ModModelManager.registerAllModels();
		System.out.println("Pre init in ClientProxy");
	}
	
	@Override
	public void init()
	{
		
	}
	
	@Override
	public void postInit()
	{
		
	}
}
