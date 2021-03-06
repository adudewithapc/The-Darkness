package thatmartinguy.thedarkness.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thatmartinguy.thedarkness.item.*;

@EventBusSubscriber
public class ModItems
{
    public static ItemNBTBase itemReliquary;
    public static ItemBase itemShadowSkin;

    public static final Item[] ITEMS = {
            itemReliquary = new ItemReliquary("itemReliquary"),
            itemShadowSkin = new ItemBase("itemShadowSkin")
    };

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ITEMS);
    }
}
