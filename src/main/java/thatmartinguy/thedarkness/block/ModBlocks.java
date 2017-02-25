package thatmartinguy.thedarkness.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks
{
	public static BlockBase blockVoidstone;
	
	public static void init()
	{
		blockVoidstone = new BlockVoidstone("blockVoidstone", "BlockVoidstone", Material.ROCK);
		
		registerBlocks();
	}
	
	private static void registerBlocks()
	{
		registerBlock(blockVoidstone);
	}
	
	private static void registerBlock(Block block)
	{
		GameRegistry.register(block);
	}
}
