package thatmartinguy.thedarkness.item;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.achievement.ModAchievements;
import thatmartinguy.thedarkness.util.Reference;

public class ItemBrighstoneSword extends ItemSword
{
	public ItemBrighstoneSword(String unlocalizedName, String registryName, ToolMaterial material)
	{
		this(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(Reference.MOD_ID + ":" + registryName);
		this.setCreativeTab(TheDarkness.tabDarkness);
	}
	public ItemBrighstoneSword(ToolMaterial material)
	{
		super(material);
	}
	
	@Override
	public Item setUnlocalizedName(String unlocalizedName)
	{
		return super.setUnlocalizedName(Reference.MOD_ID + ":" + unlocalizedName);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add(ChatFormatting.DARK_GRAY + "Fight darkness with light...");
		tooltip.add(ChatFormatting.YELLOW + "Sun's strike I");
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
	{
		playerIn.addStat(ModAchievements.acquireBrighstoneSword);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}
}
