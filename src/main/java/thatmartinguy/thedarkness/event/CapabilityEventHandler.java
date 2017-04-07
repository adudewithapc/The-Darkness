package thatmartinguy.thedarkness.event;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thatmartinguy.thedarkness.block.ModBlocks;
import thatmartinguy.thedarkness.data.capability.ItemDropHostProvider;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.util.Reference;

public class CapabilityEventHandler
{
	public static final ResourceLocation IS_HOST = new ResourceLocation(Reference.MOD_ID, "isHost");
	public static final ResourceLocation DROPPED_BY_HOST = new ResourceLocation(Reference.MOD_ID, "droppedByHost");
	
	//Attach capabilities
	@SubscribeEvent
	public void attachPlayerCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event)
	{
		if(event.getObject() instanceof EntityPlayer)
		{
			event.addCapability(IS_HOST, new PlayerHostProvider());
		}
		
		if(event.getObject() instanceof EntityItem)
		{
			event.addCapability(DROPPED_BY_HOST, new ItemDropHostProvider());
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
	
	//Burn the host in the sun
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
	
	//Set whether a dropped item was dropped by a host or not
	@SubscribeEvent
	public void playerDropItem(ItemTossEvent event)
	{
		if(event.getPlayer().getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isHost())
		{
			event.getEntityItem().getCapability(ItemDropHostProvider.ITEM_DROPPED_BY_HOST_CAPABILITY, null).setDroppedByHost(true);
		}
		else
		{
			event.getEntityItem().getCapability(ItemDropHostProvider.ITEM_DROPPED_BY_HOST_CAPABILITY, null).setDroppedByHost(false);
		}
	}
	
	//Create a dark light if the host uses a flint and steel
	@SubscribeEvent
	public void placeDarkLight(PlayerInteractEvent.RightClickBlock event)
	{
		if(event.getEntityPlayer().getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isHost() && event.getItemStack().getItem() == Items.FLINT_AND_STEEL && !event.getWorld().isRemote && event.getWorld().getBlockState(event.getPos()).isFullBlock())
		{
			event.getWorld().setBlockState(new BlockPos(event.getPos().getX(), event.getPos().getY() + 1, event.getPos().getZ()), ModBlocks.blockDarkLight.getDefaultState());
			event.setCanceled(true);
		}
	}
	
	//Heal the host whenever he takes damage from wither
	@SubscribeEvent
	public void healHostWithWither(LivingHurtEvent event)
	{
		if(event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntity();
			
			if(event.getSource() == DamageSource.WITHER)
			{
				player.heal(event.getAmount() * 2);
			}
		}
	}
}
