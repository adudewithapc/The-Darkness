package thatmartinguy.thedarkness.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent.PotionShiftEvent;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.util.Reference;

public class ItemHumanHeart extends ItemFood
{
	public ItemHumanHeart(String unlocalizedName, String registryName, int amount)
	{
		super(amount, true);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.setCreativeTab(TheDarkness.tabDarkness);
	}
	
	@Override
	public Item setUnlocalizedName(String unlocalizedName)
	{
		return super.setUnlocalizedName(Reference.MOD_ID + ":" + unlocalizedName);
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
	{
		if(!worldIn.isRemote && player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isHost())
		{
			player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60));
		}
		else if(!worldIn.isRemote)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 200));
			player.addPotionEffect(new PotionEffect(MobEffects.POISON, 200));
		}
	}
}
