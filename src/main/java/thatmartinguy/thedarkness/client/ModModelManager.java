package thatmartinguy.thedarkness.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import thatmartinguy.thedarkness.item.ModItems;

public class ModModelManager
{
	public static void registerAllModels()
	{
		registerItemModels();
	}

	private static void registerItemModels()
	{
		registerItemModel(ModItems.itemReliquary);
		registerItemModel(ModItems.itemDarklingSkin);
	}
	
	private static void registerItemModel(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
