package thatmartinguy.thedarkness.event;

import java.util.Iterator;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import thatmartinguy.thedarkness.crafting.ModCrafting;
import thatmartinguy.thedarkness.item.ModItems;
import thatmartinguy.thedarkness.potion.ModPotionEffects;

public class CommonEventHandler
{
	@SubscribeEvent
	public void reliquaryCraftedEvent(ItemCraftedEvent event)
	{
		if(event.crafting.isItemEqual(new ItemStack(ModItems.itemReliquary)))
		{
			
		}
	}
	
	@SubscribeEvent
	public void entityDiedEvent(LivingDeathEvent event)
	{
		//TODO: Check what was killed
		EntityLivingBase victim = event.getEntityLiving();
		DamageSource source = event.getSource();
		if(source.getDamageType() == "player")
		{
			EntityPlayer player = (EntityPlayer) source.getEntity();
			if(player.getActivePotionEffect(Potion.getPotionById(9)) == null && player.getActivePotionEffect(Potion.getPotionById(15)) == null && player.getActivePotionEffect(Potion.getPotionById(2)) == null && player.getActivePotionEffect(Potion.getPotionById(4)) == null && player.getActivePotionEffect(Potion.getPotionById(18)) == null)
			{
				if(player.getActivePotionEffect(Potion.getPotionById(Potion.getIdFromPotion(ModPotionEffects.effectReliquary))) != null)
				{
					if(!player.worldObj.isRemote)
					{
						player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, player.posX, player.posY, player.posZ, false));
						player.addChatMessage(new TextComponentString(ChatFormatting.DARK_PURPLE + "You are one with me!"));
					}
					if(player.worldObj.isRemote)
					{
						player.worldObj.playSound(player.posX, player.posY, player.posZ, SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.MASTER, 1, 1, false);
					}
				}
			}
		}
	}
}