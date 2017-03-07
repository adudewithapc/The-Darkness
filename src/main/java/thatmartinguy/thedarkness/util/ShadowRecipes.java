package thatmartinguy.thedarkness.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thatmartinguy.thedarkness.item.ModItems;

public class ShadowRecipes
{
	private static List<Item[]> shadowRecipeList = new ArrayList<Item[]>();
	private static List<ItemStack> shadowRecipeOutputs = new ArrayList<ItemStack>();
	
	public static void init()
	{
		addShadowRecipe(new ItemStack(ModItems.itemShadowHead), ModItems.itemHumanHeart, Items.DIAMOND_HELMET);
	}
	
	private static void addShadowRecipe(ItemStack output, Item... inputs)
	{
		Item[] recipe = new Item[inputs.length];
		for(int i = 0; i < recipe.length; i++)
		{
			recipe[i] = inputs[i];
		}
		shadowRecipeList.add(recipe);
		shadowRecipeOutputs.add(output);
	}
	
	@Nullable
	public static ItemStack getOuput(Item[] items)
	{
		for(int i = 0; i < shadowRecipeList.size(); i++)
		{
			if(shadowRecipeList.get(i) == items)
			{
				return shadowRecipeOutputs.get(i);
			}
		}
		return null;
	}
}
