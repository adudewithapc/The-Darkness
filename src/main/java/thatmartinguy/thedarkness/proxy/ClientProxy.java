package thatmartinguy.thedarkness.proxy;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import thatmartinguy.thedarkness.client.ModModelManager;

public class ClientProxy extends CommonProxy
{
	private final Minecraft MINECRAFT = Minecraft.getMinecraft();
	
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
	
	@Nullable
	@Override
	public EntityPlayer getClientPlayer()
	{
		return MINECRAFT.thePlayer;
	}
}
