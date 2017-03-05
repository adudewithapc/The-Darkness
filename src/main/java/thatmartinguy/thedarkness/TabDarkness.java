package thatmartinguy.thedarkness;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import thatmartinguy.thedarkness.item.ModItems;

public class TabDarkness extends CreativeTabs
{

	public TabDarkness(String label)
	{
		super(label);
		//TODO: Implement
		//this.setBackgroundImageName("");
	}

	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(ModItems.itemReliquary);
	}

}
