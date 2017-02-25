package thatmartinguy.thedarkness;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class TabDarkness extends CreativeTabs
{

	public TabDarkness(String label)
	{
		super(label);
		//TODO: Implement
		//this.setBackgroundImageName("");
	}

	@Override
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(Blocks.BEDROCK);
	}

}
