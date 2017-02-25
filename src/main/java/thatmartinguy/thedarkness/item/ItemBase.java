package thatmartinguy.thedarkness.item;

import net.minecraft.item.Item;
import thatmartinguy.thedarkness.TheDarkness;

public class ItemBase extends Item
{
	public ItemBase(String unlocalizedName, String registryName)
	{
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.setCreativeTab(TheDarkness.tabDarkness);
	}
}
