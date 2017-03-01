package thatmartinguy.thedarkness.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import thatmartinguy.thedarkness.block.ModBlocks;
import thatmartinguy.thedarkness.entity.monster.EntityLivingShadow;
import thatmartinguy.thedarkness.entity.monster.RenderLivingShadow;
import thatmartinguy.thedarkness.item.ModItems;

public class ModModelManager
{
	public static void registerAllModels()
	{
		registerItemModels();
		registerBlockModels();
		registerMobModels();
	}

	private static void registerItemModels()
	{
		registerItemModel(ModItems.itemReliquary);
		registerItemModel(ModItems.itemDarklingSkin);
		registerItemModel(ModItems.swordBrightstone);
		registerItemModel(ModItems.swordVoidstone);
		registerItemModel(ModItems.itemDiary1);
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
	
	private static void registerMobModels()
	{
		registerMobModel(EntityLivingShadow.class, RenderLivingShadow::new);
	}
	
	private static <T extends Entity> void registerMobModel(Class<T> entity, IRenderFactory<? super T> renderFactory)
	{
		RenderingRegistry.registerEntityRenderingHandler(entity, renderFactory);
	}
}
