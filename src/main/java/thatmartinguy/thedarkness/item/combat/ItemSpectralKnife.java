package thatmartinguy.thedarkness.item.combat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import thatmartinguy.thedarkness.entity.projectile.EntitySpectralKnife;
import thatmartinguy.thedarkness.item.ItemBase;

public class ItemSpectralKnife extends ItemBase
{
	public ItemSpectralKnife(String unlocalizedName, String registryName)
	{
		super(unlocalizedName, registryName);
		this.setMaxStackSize(1);
		this.setMaxDamage(10);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		ItemStack daggerStack = player.getHeldItem(hand);
		
		if(!player.capabilities.isCreativeMode)
		{
			daggerStack.damageItem(1, player);
			player.getCooldownTracker().setCooldown(this, 400);
		}
		
		world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		
		if(!world.isRemote)
		{
			EntitySpectralKnife spectralKnife = new EntitySpectralKnife(world, player);
			spectralKnife.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.spawnEntity(spectralKnife);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, daggerStack);
	}
}
