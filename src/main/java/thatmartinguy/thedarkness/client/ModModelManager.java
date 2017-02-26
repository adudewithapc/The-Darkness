package thatmartinguy.thedarkness.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import thatmartinguy.thedarkness.block.ModBlocks;
import thatmartinguy.thedarkness.item.ModItems;
import thatmartinguy.thedarkness.reference.Reference;

public class ModModelManager
{
	public static void registerAllModels()
	{
		registerItemModels();
		registerBlockModels();
	}

	private static void registerItemModels()
	{
		registerItemModel(ModItems.itemReliquary);
		registerItemModel(ModItems.itemDarklingSkin);
		registerItemModel(ModItems.swordBrightstone);
		registerItemModel(ModItems.swordVoidstone);
	}
	
	private static void registerItemModel(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	private static void registerBlockModels()
	{
		registerBlockModel(ModBlocks.blockBrighstone);
	}
	
	private static void registerBlockModel(Block block)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
}
