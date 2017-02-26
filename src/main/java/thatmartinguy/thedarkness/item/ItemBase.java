package thatmartinguy.thedarkness.item;

import net.minecraft.item.Item;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.reference.Reference;

public class ItemBase extends Item
{
	public ItemBase(String unlocalizedName, String registryName)
	{
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.setCreativeTab(TheDarkness.tabDarkness);
	}
	
	@Override
	public Item setUnlocalizedName(String unlocalizedName)
	{
		return super.setUnlocalizedName(Reference.MOD_ID + ":" + unlocalizedName);
	}
}
