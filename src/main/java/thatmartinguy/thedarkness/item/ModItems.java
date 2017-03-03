package thatmartinguy.thedarkness.item;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems
{
	public static ItemReliquary itemReliquary;
	public static ItemBase itemDarklingSkin;
	public static ItemHumanHeart itemHumanHeart;
	
	public static ToolMaterial brighstoneToolMaterial;
	public static ToolMaterial voidstoneToolMaterial;
	
	public static ItemSword swordBrightstone;
	public static ItemSword swordVoidstone;
	
	public static ItemBase itemDiaryOne;
	
	public static void init()
	{
		itemDarklingSkin = new ItemDarklingSkin("itemDarklingSkin", "ItemDarklingSkin");
		
		itemReliquary = new ItemReliquary("itemReliquary", "ItemReliquary", 0, false);
		itemHumanHeart = new ItemHumanHeart("itemHumanHeart", "ItemHumanHeart", 2);
		
		brighstoneToolMaterial = EnumHelper.addToolMaterial("brighstoneToolMaterial", 0, 10, 0.0F, 8.0F, 0);
		voidstoneToolMaterial = EnumHelper.addToolMaterial("voidstoneToolMaterial", 0, 1561, 0.0F, 8.0F, 15);
		
		swordBrightstone = new ItemBrighstoneSword("swordBrightstone", "SwordBrightstone", brighstoneToolMaterial);
		swordVoidstone = new ItemVoidstoneSword("swordVoidstone", "SwordVoidstone", voidstoneToolMaterial);
		
		itemDiaryOne = new ItemDiaryOne("itemDiaryOne", "ItemDiaryOne");
		
		registerItems();
	}
	
	private static void registerItems()
	{
		registerItem(itemReliquary);
		registerItem(itemDarklingSkin);
		registerItem(itemDiaryOne);
		registerItem(itemHumanHeart);
		
		GameRegistry.register(swordBrightstone);
		GameRegistry.register(swordVoidstone);
	}
	
	private static void registerItem(Item item)
	{
		GameRegistry.register(item);
	}
}
