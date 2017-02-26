package thatmartinguy.thedarkness.item;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.reference.Reference;

public class ItemVoidstoneSword extends ItemSword
{
	public ItemVoidstoneSword(String unlocalizedName, String registryName, ToolMaterial material)
	{
		this(material);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + unlocalizedName);
		this.setRegistryName(registryName);
		this.setCreativeTab(TheDarkness.tabDarkness);
	}
	public ItemVoidstoneSword(ToolMaterial material)
	{
		super(material);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add(ChatFormatting.DARK_GRAY + "...and the darkness consumes it.");
	}
}