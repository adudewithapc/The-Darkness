package thatmartinguy.thedarkness.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemNBTBase extends ItemBase
{
    public ItemNBTBase(String name)
    {
        super(name);
    }

    public NBTTagCompound getNBT(ItemStack itemStack)
    {
        NBTTagCompound nbt;

        if(itemStack.hasTagCompound())
        {
            nbt = itemStack.getTagCompound();
        }
        else
        {
            nbt = new NBTTagCompound();
        }

        itemStack.setTagCompound(nbt);
        return nbt;
    }

    public boolean hasNBTTag(ItemStack itemStack, String key)
    {
        return getNBT(itemStack).hasKey(key);
    }

    public int getNBTInt(ItemStack itemStack, String key, int defaultValue)
    {
        if(hasNBTTag(itemStack, key))
        {
            return itemStack.getTagCompound().getInteger(key);
        }
        else
        {
            getNBT(itemStack).setInteger(key, defaultValue);
            return itemStack.getTagCompound().getInteger(key);
        }
    }

    public void setNBTInt(ItemStack itemStack, String key, int value)
    {
        getNBT(itemStack).setInteger(key, value);
    }

    public boolean getNBTBool(ItemStack itemStack, String key, boolean defaultValue)
    {
        if(hasNBTTag(itemStack, key))
        {
            return itemStack.getTagCompound().getBoolean(key);
        }
        else
        {
            getNBT(itemStack).setBoolean(key, defaultValue);
            return itemStack.getTagCompound().getBoolean(key);
        }
    }

    public void setNBTBool(ItemStack itemStack, String key, boolean value)
    {
        getNBT(itemStack).setBoolean(key, value);
    }

    public String getNBTString(ItemStack itemStack, String key, String defaultValue)
    {
        if(hasNBTTag(itemStack, key))
        {
            return itemStack.getTagCompound().getString(key);
        }
        else
        {
            getNBT(itemStack).setString(key, defaultValue);
            return itemStack.getTagCompound().getString(key);
        }
    }
}
