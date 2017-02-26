package thatmartinguy.thedarkness.item;

import net.minecraft.block.BlockDoor.EnumHingePosition;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems
{
	public static ItemReliquary itemReliquary;
	public static ItemBase itemDarklingSkin;
	
	public static ToolMaterial brighstoneToolMaterial;
	public static ToolMaterial voidstoneToolMaterial;
	
	public static ItemSword swordBrightstone;
	public static ItemSword swordVoidstone;
	
	public static void init()
	{
		itemDarklingSkin = new ItemDarklingSkin("itemDarklingSkin", "ItemDarklingSkin");
		
		itemReliquary = new ItemReliquary("itemReliquary", "ItemReliquary", 0, false);
		
		brighstoneToolMaterial = EnumHelper.addToolMaterial("brighstoneToolMaterial", 0, 10, 0.0F, 8.0F, 0);
		voidstoneToolMaterial = EnumHelper.addToolMaterial("voidstoneToolMaterial", 0, 1561, 0.0F, 8.0F, 15);
		
		swordBrightstone = new ItemBrighstoneSword("swordBrightstone", "SwordBrightstone", brighstoneToolMaterial);
		swordVoidstone = new ItemVoidstoneSword("swordVoidstone", "SwordVoidstone", voidstoneToolMaterial);
		
		registerItems();
	}
	
	private static void registerItems()
	{
		registerItem(itemReliquary);
		registerItem(itemDarklingSkin);
		
		GameRegistry.register(swordBrightstone);
		GameRegistry.register(swordVoidstone);
	}
	
	private static void registerItem(Item item)
	{
		GameRegistry.register(item);
	}
}
