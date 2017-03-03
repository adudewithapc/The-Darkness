package thatmartinguy.thedarkness.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import thatmartinguy.thedarkness.block.ModBlocks;
import thatmartinguy.thedarkness.data.capability.IPlayerHostCapability;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.item.ModItems;
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
	
	//Give the player strength if he is holding a voidstone sword and it is daytime
	@SubscribeEvent
	public void applyVoidstoneStrength(LivingUpdateEvent event)
	{
		if(event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			IPlayerHostCapability host = player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null);
			if(host.isHost())
			{
				if(player.getHeldItemMainhand() != null)
				{
					if(player.getHeldItemMainhand().getItem() == ModItems.swordVoidstone && !player.world.isDaytime() && !player.world.isRemote)
					{
						player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20, 0, false, false));
					}
				}
				if(player.getHeldItemOffhand() != null)
				{
					if(player.getHeldItemOffhand().getItem() == ModItems.swordVoidstone && !player.world.isDaytime() && !player.world.isRemote)
					{
						player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20, 0, false, false));
					}
				}
			}
		}
	}
}
