package thatmartinguy.thedarkness.init;

import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thatmartinguy.thedarkness.potion.PotionFading;

@EventBusSubscriber
public class ModPotions
{
    public static PotionFading potionFading;
    public static final Potion[] POTIONS = {
            potionFading = new PotionFading("potionFading")
    };

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event)
    {
        event.getRegistry().registerAll(POTIONS);
    }
}
