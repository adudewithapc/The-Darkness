package thatmartinguy.thedarkness.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import scala.Int;
import thatmartinguy.thedarkness.item.ModItems;

public class DarkLightCrafting
{
	private static List<Item[]> darkLightIngredients = new ArrayList<Item[]>();
	private static List<ItemStack> darkLightOutputs = new ArrayList<ItemStack>();
	
	public static void init()
	{
		addDarkLightRecipe(new ItemStack(ModItems.itemShadowHead), ModItems.itemHumanHeart, Items.DIAMOND_HELMET);
	}
	
	private static void addDarkLightRecipe(ItemStack output, Item... inputs)
	{
		darkLightIngredients.add(inputs);
		darkLightOutputs.add(output);
	}
	
	@Nullable
	public static ItemStack getOutput(ItemStack... inputs)
	{
		if(matches(inputs) != Int.MinValue())
		{
			return darkLightOutputs.get(matches(inputs));
		}
		
		return null;
	}
	
	private static int matches(ItemStack[] inputs)
	{
		int matchingItems = 0;
		for(int i = 0; i < darkLightIngredients.size(); i++)
		{
			for(int j = 0; i < inputs.length; j++)
			{
				System.out.print(matchingItems);
				if(darkLightIngredients.get(i)[i] == inputs[j].getItem())
				{
					matchingItems++;
				}
				if(matchingItems == darkLightIngredients.get(i).length)
				{
					return i;
				}
			}
		}
		return Int.MinValue();
	}
}
