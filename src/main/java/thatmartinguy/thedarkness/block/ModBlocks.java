package thatmartinguy.thedarkness.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks
{
	public static BlockBase blockBrighstone;
	public static BlockBase blockDarkLight;
	
	public static ItemBlock itemBlockBrightstone;
	
	public static void init()
	{
		blockBrighstone = new BlockBrightstone("blockBrightstone", "BlockBrightstone", Material.ROCK);
		blockDarkLight = new BlockDarkLight("blockDarkLight", "BlockDarkLight", Material.FIRE);
		
		itemBlockBrightstone = new ItemBlock(blockBrighstone);
		
		registerBlocks();
		registerItemBlocks();
	}
	
	private static void registerBlocks()
	{
		registerBlock(blockBrighstone);
		registerBlock(blockDarkLight);
	}
	
	private static void registerItemBlocks()
	{
		registerItemBlock(itemBlockBrightstone, blockBrighstone.getRegistryName());
	}
	
	private static void registerItemBlock(ItemBlock itemBlock, ResourceLocation registryName)
	{
		GameRegistry.register(itemBlock, registryName);
	}

	private static void registerBlock(Block block)
	{
		GameRegistry.register(block);
	}
}
