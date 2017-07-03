package thatmartinguy.thedarkness.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.init.ModPotions;
import thatmartinguy.thedarkness.util.Reference;

@EventBusSubscriber
public class CapabilityEventHandler
{
    public static final ResourceLocation HOST_CAPABILITY = Reference.createResourceLocation("hostCapability");

    @SubscribeEvent
    public static void attachHostCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof EntityPlayer)
        {
            event.addCapability(HOST_CAPABILITY, new PlayerHostProvider());
        }
    }

    @SubscribeEvent
    public static void handleFading(LivingUpdateEvent event)
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
}
