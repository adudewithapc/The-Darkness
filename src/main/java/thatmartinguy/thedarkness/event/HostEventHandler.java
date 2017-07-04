package thatmartinguy.thedarkness.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import thatmartinguy.thedarkness.data.capability.IPlayerHostCapability;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.init.ModPotions;

@EventBusSubscriber
public class HostEventHandler
{
    @SubscribeEvent
    public static void handleFading(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntityLiving() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if(player.isPotionActive(ModPotions.potionFading) && !player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isTransforming() && !player.getEntityWorld().isRemote)
            {
                if(player.getEntityWorld().isDaytime())
                {
                    player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 19, 3));
                    player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 19, 3));
                    player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 19, 3));
                    player.addPotionEffect(new PotionEffect(MobEffects.UNLUCK, 19, 3));
                }
                else
                {
                    player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 19));
                    player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 19));
                    player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 19));
                    player.addPotionEffect(new PotionEffect(MobEffects.UNLUCK, 19));
                }
            }
        }
    }

    @SubscribeEvent
    public static void playerTransforming(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntityLiving() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if(player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isTransforming())
            {
                World world = player.getEntityWorld();
                if(!world.isRemote)
                    player.addPotionEffect(new PotionEffect(ModPotions.potionFading, 19));
            }
        }
    }

    @SubscribeEvent
    public static void hostDeathMessage(PlayerEvent.PlayerRespawnEvent event)
    {
        IPlayerHostCapability capability = event.player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null);

        if(capability.isHost())
        {
            event.player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "You awake from a bad dream"));
        }
        if(capability.isTransforming() || capability.isHost())
        {
            for(EntityPlayer player : event.player.world.playerEntities)
            {
                player.sendMessage(new TextComponentString(TextFormatting.GRAY + "A loud \"clang\" could be heard from the end"));
                if(player.world.isRemote)
                    player.world.playSound(player, player.getPosition(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 1.0f, 0.8f);
            }
        }
        capability.setHost(false);
        capability.setTransforming(false);
    }
}