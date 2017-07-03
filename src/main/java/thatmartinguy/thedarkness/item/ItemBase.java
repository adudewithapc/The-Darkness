package thatmartinguy.thedarkness.item;

import net.minecraft.item.Item;
import thatmartinguy.thedarkness.TheDarkness;

public class ItemBase extends Item
{
    public ItemBase(String name)
    {
        this.setRegistryName(name);
        this.setUnlocalizedName();
        this.setCreativeTab(TheDarkness.TAB_DARKNESS);
    }

    public Item setUnlocalizedName()
    {
        return super.setUnlocalizedName(getRegistryName().toString());
    }
}
