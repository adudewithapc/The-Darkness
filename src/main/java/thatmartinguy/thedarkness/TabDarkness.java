package thatmartinguy.thedarkness;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
		return new ItemStack(Item.getItemFromBlock(Blocks.BEDROCK));
	}

}
