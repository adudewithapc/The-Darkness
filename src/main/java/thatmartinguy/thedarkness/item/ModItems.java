package thatmartinguy.thedarkness.item;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thatmartinguy.thedarkness.item.combat.ItemBrighstoneSword;
import thatmartinguy.thedarkness.item.combat.ItemShadowArmor;
import thatmartinguy.thedarkness.item.combat.ItemSpectralKnife;
import thatmartinguy.thedarkness.item.combat.ItemVoidstoneSword;

public class ModItems
{
	public static ItemReliquary itemReliquary;
	public static ItemBase itemDarklingSkin;
	public static ItemHumanHeart itemHumanHeart;
	public static ItemBase itemDiaryOne;
	public static ItemBase itemSpectralKnife;
	
	public static ToolMaterial brighstoneToolMaterial;
	public static ToolMaterial voidstoneToolMaterial;
	
	public static ArmorMaterial shadowArmorMaterial;
	
	public static ItemSword swordBrightstone;
	public static ItemSword swordVoidstone;
	
	public static ItemShadowArmor itemShadowHead;
	public static ItemShadowArmor itemShadowChest;
	public static ItemShadowArmor itemShadowLegs;
	public static ItemShadowArmor itemShadowFeet;
	
	public static void init()
	{
		itemDarklingSkin = new ItemDarklingSkin("itemDarklingSkin", "ItemDarklingSkin");
		itemDiaryOne = new ItemDiaryOne("itemDiaryOne", "ItemDiaryOne");
		itemSpectralKnife = new ItemSpectralKnife("itemSpectralKnife", "ItemSpectralKnife");
		
		itemReliquary = new ItemReliquary("itemReliquary", "ItemReliquary", 0, false);
		itemHumanHeart = new ItemHumanHeart("itemHumanHeart", "ItemHumanHeart", 2);
		
		brighstoneToolMaterial = EnumHelper.addToolMaterial("brighstoneToolMaterial", 0, 10, 0.0F, 8.0F, 0);
		voidstoneToolMaterial = EnumHelper.addToolMaterial("voidstoneToolMaterial", 0, 1561, 0.0F, 8.0F, 15);
		
		shadowArmorMaterial = EnumHelper.addArmorMaterial("shadowArmorMaterial", "", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0F);
		
		swordBrightstone = new ItemBrighstoneSword("swordBrightstone", "SwordBrightstone", brighstoneToolMaterial);
		
		itemShadowHead = new ItemShadowArmor("itemShadowHead", "ItemShadowHead", EntityEquipmentSlot.HEAD);
		itemShadowChest = new ItemShadowArmor("itemShadowChest", "ItemShadowChest", EntityEquipmentSlot.CHEST);
		itemShadowLegs = new ItemShadowArmor("itemShadowLegs", "ItemShadowLegs", EntityEquipmentSlot.LEGS);
		itemShadowFeet = new ItemShadowArmor("itemShadowFeet", "ItemShadowFeet", EntityEquipmentSlot.FEET);
		swordVoidstone = new ItemVoidstoneSword("swordVoidstone", "SwordVoidstone", voidstoneToolMaterial);
		
		registerItems();
	}
	
	private static void registerItems()
	{
		registerItem(itemReliquary);
		registerItem(itemDarklingSkin);
		registerItem(itemDiaryOne);
		registerItem(itemHumanHeart);
		registerItem(itemSpectralKnife);
		
		registerItem(itemShadowHead);
		registerItem(itemShadowChest);
		registerItem(itemShadowLegs);
		registerItem(itemShadowFeet);
		
		GameRegistry.register(swordBrightstone);
		GameRegistry.register(swordVoidstone);
	}
	
	private static void registerItem(Item item)
	{
		GameRegistry.register(item);
	}
}
