package thatmartinguy.thedarkness.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import thatmartinguy.thedarkness.block.ModBlocks;
import thatmartinguy.thedarkness.client.render.RenderHuman;
import thatmartinguy.thedarkness.client.render.RenderHumanArcher;
import thatmartinguy.thedarkness.client.render.RenderHumanCivilian;
import thatmartinguy.thedarkness.client.render.RenderHumanFighter;
import thatmartinguy.thedarkness.client.render.RenderLivingShadow;
import thatmartinguy.thedarkness.entity.dummy.EntityLivingShadowSlave;
import thatmartinguy.thedarkness.entity.dummy.RenderLivingShadowSlave;
import thatmartinguy.thedarkness.entity.mob.EntityHuman;
import thatmartinguy.thedarkness.entity.mob.EntityHumanArcher;
import thatmartinguy.thedarkness.entity.mob.EntityHumanCivilian;
import thatmartinguy.thedarkness.entity.mob.EntityHumanFighter;
import thatmartinguy.thedarkness.entity.mob.EntityLivingShadow;
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
		registerItemModel(ModItems.itemHumanHeart);
		registerItemModel(ModItems.itemDiaryOne);
		registerItemModel(ModItems.itemSpectralKnife);
		
		registerItemModel(ModItems.swordBrightstone);
		registerItemModel(ModItems.swordVoidstone);
		
		registerItemModel(ModItems.itemShadowHead);
		registerItemModel(ModItems.itemShadowChest);
		registerItemModel(ModItems.itemShadowLegs);
		registerItemModel(ModItems.itemShadowFeet);
	}
	
	private static void registerItemModel(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	private static void registerBlockModels()
	{
		registerBlockModel(ModBlocks.blockBrighstone);
		registerBlockModel(ModBlocks.blockDarkLight);
	}
	
	private static void registerBlockModel(Block block)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
	}
	
	private static void registerMobModels()
	{
		registerMobModel(EntityLivingShadow.class, RenderLivingShadow::new);
		registerMobModel(EntityHuman.class, RenderHuman::new);
		registerMobModel(EntityHumanCivilian.class, RenderHumanCivilian::new);
		registerMobModel(EntityHumanFighter.class, RenderHumanFighter::new);
		registerMobModel(EntityHumanArcher.class, RenderHumanArcher::new);
		
		registerMobModel(EntityLivingShadowSlave.class, RenderLivingShadowSlave::new);
	}
	
	private static <T extends Entity> void registerMobModel(Class<T> entity, IRenderFactory<? super T> renderFactory)
	{
		RenderingRegistry.registerEntityRenderingHandler(entity, renderFactory);
	}
}
