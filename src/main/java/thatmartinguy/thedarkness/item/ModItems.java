package thatmartinguy.thedarkness.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems
{
	public static ItemReliquary itemReliquary;
	public static ItemBase itemDarklingSkin;
	
	public static void init()
	{
		itemDarklingSkin = new ItemDarklingSkin("itemDarklingSkin", "ItemDarklingSkin");
		
		itemReliquary = new ItemReliquary("itemReliquary", "ItemReliquary", 0, false);
		
		registerItems();
	}
	
	private static void registerItems()
	{
		registerItem(itemReliquary);
		registerItem(itemDarklingSkin);
	}
	
	private static void registerItem(Item item)
	{
		GameRegistry.register(item);
	}
}
