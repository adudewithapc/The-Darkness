package thatmartinguy.thedarkness.crafting;

import java.util.Iterator;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thatmartinguy.thedarkness.item.ModItems;

public class ModCrafting
{
	//Field for checking if the reliquary is crafted
	private static boolean isReliquaryCrafted = false;
	
	public static void init()
	{
		//GameRegistry.addRecipe(new ItemStack(ModItems.itemReliquary), "SGS", "SNS", "SSS", 'G', Item.getItemFromBlock(Blocks.GLASS), 'N', Items.NETHER_STAR, 'S', ModItems.itemDarklingSkin);
	}
	
	/**public static void setReliquaryCrafable(boolean isCrafted)
	{
		isReliquaryCrafted = isCrafted;
		
		//Make reliquary uncraftable if it has been crafted
		if(!isReliquaryCrafted)
		{
			Iterator recipes = CraftingManager.getInstance().getRecipeList().iterator();
			while(recipes.hasNext())
			{
				ItemStack output = ((IRecipe) recipes.next()).getRecipeOutput();
				if(output != null && output.getItem() != null)
				{
					if(ItemStack.areItemsEqual(output, new ItemStack(ModItems.itemReliquary)))
						recipes.remove();
				}
			}
		}
	}**/
}
