package thatmartinguy.thedarkness.item;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.achievement.ModAchievements;
import thatmartinguy.thedarkness.client.gui.GuiDiary;

public class ItemDiaryOne extends ItemBase
{
	public ItemDiaryOne(String unlocalizedName, String registryName)
	{
		super(unlocalizedName, registryName);
		this.setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add("by Mr. Lorem Ipsum");
		tooltip.add("Original");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(world.isRemote)
		{
			player.openGui(TheDarkness.instance, 0, world, (int) player.posX, (int) player.posY, (int) player.posZ);
			
		}
		player.addStat(ModAchievements.acquireDiary);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
}
