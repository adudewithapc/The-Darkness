package thatmartinguy.thedarkness.event;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.util.Reference;

public class CapabilityEventHandler
{
	public static final ResourceLocation IS_HOST = new ResourceLocation(Reference.MOD_ID, "isHost");
	
	//Attach player capabilities
	@SubscribeEvent
	public void attachPlayerCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event)
	{
		if(event.getObject() instanceof EntityPlayer)
		{
			event.addCapability(IS_HOST, new PlayerHostProvider());
		}
	}
	
	//Display a custom death message if the host dies
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void playerDeathMessage(LivingDeathEvent event)
	{
		if(event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer victim = (EntityPlayer) event.getEntityLiving();
			
			if(victim.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isHost())
			{
				for(int i = 0; i < victim.world.playerEntities.size(); i++)
				{
					if(victim.world.playerEntities.get(i).equals(victim))
					{
						victim.sendMessage(new TextComponentString(ChatFormatting.DARK_PURPLE + "Useless."));
					}
					else
					{
						victim.world.playerEntities.get(i).sendMessage(new TextComponentString(ChatFormatting.DARK_PURPLE + "My champion has been slain. Bring me another!"));
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void burnHostInSun(LivingUpdateEvent event)
	{
		if(event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			
			if(player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isHost() && player.world.isDaytime() && player.world.canSeeSky(new BlockPos(player)))
			{
				player.setFire(3);
			}
		}
	}
}
