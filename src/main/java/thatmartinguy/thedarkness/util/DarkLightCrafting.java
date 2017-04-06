package thatmartinguy.thedarkness.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
		Item[] recipe = new Item[inputs.length];
		
		for(int i = 0; i < recipe.length; i++)
		{
			recipe[i] = inputs[i];
		}
		
		darkLightIngredients.add(recipe);
		darkLightOutputs.add(output);
	}
	
	@Nullable
	public static ItemStack getOutput(Item[] inputs)
	{
		for(int i = 0; i < inputs.length; i++)
		{
			if(darkLightIngredients.get(i) == inputs)
			{
				return darkLightOutputs.get(i);
			}
		}
		
		return null;
	}
}
