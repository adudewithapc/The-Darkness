package thatmartinguy.thedarkness.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thatmartinguy.thedarkness.block.ModBlocks;
import thatmartinguy.thedarkness.item.ModItems;

public class ModCrafting
{
	//Field for checking if the reliquary is crafted
	private static boolean isReliquaryCrafted = false;
	
	public static void init()
	{
		//Brightstone
		GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.blockBrighstone)), "GGG", "GSG", "GGG", 'G', Blocks.GLOWSTONE, 'S', Items.NETHER_STAR);
		//Brightstone sword
		GameRegistry.addRecipe(new ItemStack(ModItems.swordBrightstone), "B", "B", "S", 'B', ModBlocks.blockBrighstone, 'S', Items.STICK);
	}
}
