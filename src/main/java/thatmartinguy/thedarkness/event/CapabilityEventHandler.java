package thatmartinguy.thedarkness.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thatmartinguy.thedarkness.data.capability.IPlayerHostCapability;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.init.ModPotions;
import thatmartinguy.thedarkness.util.LogHelper;
import thatmartinguy.thedarkness.util.Reference;

import static net.minecraft.entity.player.EntityPlayer.SleepResult.OK;

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
    public static void presistCababilities(PlayerEvent.Clone event)
    {
        if(event.isWasDeath())
        {
            IPlayerHostCapability oldCapability = event.getOriginal().getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null);
            IPlayerHostCapability newCapability = event.getEntityPlayer().getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null);

            if(oldCapability.isHost())
            {
                newCapability.setHost(true);
            }
            if(oldCapability.isTransforming())
            {
                newCapability.setTransforming(true);
            }
        }
    }
}
