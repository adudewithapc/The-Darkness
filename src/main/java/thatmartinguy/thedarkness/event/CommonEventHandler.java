package thatmartinguy.thedarkness.event;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thatmartinguy.thedarkness.achievement.ModAchievements;
import thatmartinguy.thedarkness.block.ModBlocks;
import thatmartinguy.thedarkness.data.capability.IPlayerHostCapability;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.entity.mob.EntityLivingShadow;
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
						player.playSound(SoundEvents.BLOCK_PORTAL_TRIGGER, 1, 1);
						player.sendMessage(new TextComponentString(ChatFormatting.DARK_PURPLE + "You are one with me!"));
					}
					for(int i = 0; i < player.world.playerEntities.size(); i++)
					{
						if(player.world.playerEntities.get(i) != player)
						{
							player.world.playerEntities.get(i).sendMessage(new TextComponentString(ChatFormatting.DARK_PURPLE + "A champion has come forth. Let him be an example for all who oppose me!"));
						}
					}
				}
			}
		}
	}
	
	//Extinguish dark light if the block below it is clicked
	@SubscribeEvent
	public void extinguishDarkLight(PlayerInteractEvent.LeftClickBlock event)
	{
		BlockPos darkLightPos = event.getPos().up();
		
		if(event.getWorld().getBlockState(darkLightPos).getBlock() == ModBlocks.blockDarkLight)
		{
			event.getWorld().playSound(darkLightPos.getX(), darkLightPos.getY(), darkLightPos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 0.3f, true);
			event.getWorld().setBlockToAir(darkLightPos);
			
			if(event.getEntityPlayer() != null && !event.getEntityPlayer().getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isHost() && !event.getWorld().isRemote)
			{
				event.getEntityPlayer().addPotionEffect(new PotionEffect(MobEffects.WITHER));
			}
			event.setCanceled(true);
		}
	}
}
