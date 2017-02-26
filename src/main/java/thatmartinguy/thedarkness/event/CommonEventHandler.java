package thatmartinguy.thedarkness.event;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import thatmartinguy.thedarkness.item.ModItems;
import thatmartinguy.thedarkness.potion.ModPotionEffects;

public class CommonEventHandler
{
	@SuppressWarnings("unused")
	@SubscribeEvent
	public void reliquaryCraftedEvent(ItemCraftedEvent event)
	{
		if(event.crafting.isItemEqual(new ItemStack(ModItems.itemReliquary)))
		{
			
		}
	}
	
	//Hit the player with lightning if he killed a darkling
	@SubscribeEvent
	public void entityDiedEvent(LivingDeathEvent event)
	{
		//TODO: Check what was killed
		EntityLivingBase victim = event.getEntityLiving();
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
				if(player.isPotionActive(ModPotionEffects.effectReliquary))
				{
					if(!player.worldObj.isRemote)
					{
						player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, player.posX, player.posY, player.posZ, false));
						player.addChatMessage(new TextComponentString(ChatFormatting.DARK_PURPLE + "You are one with me!"));
					}
					else
					{
						player.worldObj.playSound(player.posX, player.posY, player.posZ, SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.MASTER, 1, 1, false);
					}
				}
			}
		}
	}
	
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
					player.worldObj.setWorldTime(13048);
					if(!player.worldObj.isRemote)
					{
						event.getEntity().playSound(SoundEvents.BLOCK_PORTAL_TRIGGER, 1, 1);
					}
				}
			}
			if(player.getHeldItemOffhand() != null)
			{
				if(player.getHeldItemOffhand().getItem() == ModItems.swordBrightstone)
				{
					player.setHeldItem(EnumHand.OFF_HAND, new ItemStack(ModItems.swordVoidstone));
					//Set time to night
					player.worldObj.setWorldTime(13048);
					if(!player.worldObj.isRemote)
					{
						event.getEntity().playSound(SoundEvents.BLOCK_PORTAL_TRIGGER, 1, 1);
					}
				}
			}
		}
	}
}
