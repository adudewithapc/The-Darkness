package thatmartinguy.thedarkness.data;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.network.PlayerHostMessage;
import thatmartinguy.thedarkness.network.PlayerHostUUIDMessage;
import thatmartinguy.thedarkness.network.ReliquaryMessage;
import thatmartinguy.thedarkness.util.Reference;

public class ModWorldData extends WorldSavedData
{
	private static final String IDENTIFIER = Reference.MOD_ID + "reliquary";

	private boolean isReliquaryCrafted;
	private String hostUUID;
	
	private World world;
	
	private static ModWorldData instance;
	
	public ModWorldData()
	{
		super(IDENTIFIER);
	}

	public void setReliquaryCrafted(boolean isReliquaryCrafted)
	{
		this.isReliquaryCrafted = isReliquaryCrafted;
		
		if(!world.isRemote)
		{
			TheDarkness.NETWORK.sendToAll(new ReliquaryMessage(this.isReliquaryCrafted));
		}
		this.markDirty();
	}
	
	public boolean isReliquaryCrafted()
	{
		return isReliquaryCrafted;
	}
	
	public void setHostUUID(String UUID)
	{
		this.hostUUID = UUID;
		
		if(!world.isRemote)
		{
			TheDarkness.NETWORK.sendToAll(new PlayerHostUUIDMessage(this.hostUUID));
		}
		this.markDirty();
	}
	
	public String getHostUUID()
	{
		return this.hostUUID;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		this.isReliquaryCrafted = compound.getBoolean("isReliquaryCrafted");
		this.hostUUID = compound.getString("hostUUID");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setBoolean("isReliquaryCrafted", this.isReliquaryCrafted);
		compound.setString("hostUUID", this.hostUUID);
		return compound;
	}
	
	public static ModWorldData get(World world)
	{
		ModWorldData worldData;
			
		if(!world.isRemote && instance != null)
		{
			worldData = instance;
		}
		else
		{
			worldData = (ModWorldData) world.loadData(ModWorldData.class, IDENTIFIER);
			if(worldData == null)
			{
				worldData = new ModWorldData();
			}
			
			if(worldData.world == null)
			{
				worldData.world = world;
			}
		}
		world.setData(IDENTIFIER, worldData);
		
		return worldData;
	}
	
	public static void clearInstance()
	{
		instance = null;
	}
	
	@Mod.EventBusSubscriber
	private static class EventHandler
	{
		private static void sendToPlayer(EntityPlayerMP player)
		{
			final ModWorldData worldData = ModWorldData.get(player.getEntityWorld());
			TheDarkness.NETWORK.sendTo(new ReliquaryMessage(worldData.isReliquaryCrafted), player);
			TheDarkness.NETWORK.sendTo(new PlayerHostUUIDMessage(worldData.hostUUID), player);
		}
		
		@SubscribeEvent
		public static void playerLoggedInEvent(PlayerLoggedInEvent event)
		{
			if(event.player instanceof EntityPlayerMP)
			{
				sendToPlayer((EntityPlayerMP) event.player);
			}
		}
		
		@SubscribeEvent
		public static void playerChangedDimensionEvent(PlayerChangedDimensionEvent event)
		{
			if(event.player instanceof EntityPlayerMP)
			{
				sendToPlayer((EntityPlayerMP) event.player);
			}
		}
	}
}
