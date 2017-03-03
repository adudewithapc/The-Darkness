package thatmartinguy.thedarkness.event;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import thatmartinguy.thedarkness.achievement.ModAchievements;
import thatmartinguy.thedarkness.block.ModBlocks;
import thatmartinguy.thedarkness.data.ModWorldData;
import thatmartinguy.thedarkness.data.capability.IPlayerHostCapability;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.entity.monster.EntityLivingShadow;
import thatmartinguy.thedarkness.item.ModItems;
import thatmartinguy.thedarkness.potion.ModPotionEffects;

public class CommonEventHandler
{
	//Hit the player with lightning if he killed a living shadow
	@SubscribeEvent
	public void entityDiedEvent(LivingDeathEvent event)
	{
		EntityLivingBase victim = event.getEntityLiving();
		if(victim instanceof EntityLivingShadow)
		{
			DamageSource source = event.getSource();
			if(source.getDamageType().equals("player"))
			{
				EntityPlayer player = (EntityPlayer) source.getEntity();
				if(player == null) return;
				
				//Check if the player is affected by any Reliquary effect
				if(!player.isPotionActive(MobEffects.BLINDNESS) && !player.isPotionActive(MobEffects.BLINDNESS) 
						&& !player.isPotionActive(MobEffects.SLOWNESS) && !player.isPotionActive(MobEffects.MINING_FATIGUE) 
						&& !player.isPotionActive(MobEffects.WEAKNESS))
				{
					if(player.isPotionActive(ModPotionEffects.effectReliquary) && !player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isHost())
					{
						if(!player.world.isRemote)
						{
							player.world.spawnEntity(new EntityLightningBolt(player.world, player.posX, player.posY, player.posZ, false));
						}
						else
						{
							player.world.playSound(player.posX, player.posY, player.posZ, SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.MASTER, 1, 1, false);
						}
					}
				}
			}
		}
	}
	
	//Make the player a host if hit by lightning
	@SubscribeEvent
	public void playerStruckByLightningEvent(EntityStruckByLightningEvent event)
	{
		if(event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.getEntity();
			if(player == null) return;
			
			//Checks if the player is holding a Brightstone sword, and makes it a Voidstone sword if it is
			if(player.getHeldItemMainhand() != null)
			{
				if(player.getHeldItemMainhand().getItem() == ModItems.swordBrightstone)
				{
					player.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ModItems.swordVoidstone));
					//Set time to night
					player.world.setWorldTime(13048);
					
					IPlayerHostCapability host = player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null);
					host.setHost(true);
					
					player.removePotionEffect(ModPotionEffects.effectReliquary);
					
					player.addStat(ModAchievements.transform);
					
					if(!player.world.isRemote)
					{
						event.getEntity().playSound(SoundEvents.BLOCK_PORTAL_TRIGGER, 1, 1);
						player.sendMessage(new TextComponentString(ChatFormatting.DARK_PURPLE + "You are one with me!"));
						
						final ModWorldData worldData = ModWorldData.get(player.world);
						worldData.setHostUUID(player.getUniqueID().toString());
					}
				}
			}
			if(player.getHeldItemOffhand() != null)
			{
				if(player.getHeldItemOffhand().getItem() == ModItems.swordBrightstone)
				{
					player.setHeldItem(EnumHand.OFF_HAND, new ItemStack(ModItems.swordVoidstone));
					//Set time to night
					player.world.setWorldTime(13048);
					
					IPlayerHostCapability host = player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null);
					host.setHost(true);
					
					player.removePotionEffect(ModPotionEffects.effectReliquary);
					
					player.addStat(ModAchievements.transform);
					
					if(!player.world.isRemote)
					{
						event.getEntity().playSound(SoundEvents.BLOCK_PORTAL_TRIGGER, 1, 1);
						player.sendMessage(new TextComponentString(ChatFormatting.DARK_PURPLE + "You are one with me!"));
						
						final ModWorldData worldData = ModWorldData.get(player.world);
						worldData.setHostUUID(player.getUniqueID().toString());
					}
				}
			}
		}
	}
}
