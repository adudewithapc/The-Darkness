package thatmartinguy.thedarkness.item.combat;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import scala.Int;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.item.ModItems;
import thatmartinguy.thedarkness.util.Reference;

public class ItemShadowArmor extends ItemArmor
{
	public ItemShadowArmor(String unlocalizedName, String registryName, EntityEquipmentSlot slot)
	{
		super(ModItems.shadowArmorMaterial, 0, slot);
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
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		if(player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isHost() && !world.isDaytime())
		{
			if(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem().equals(ModItems.itemShadowHead))
			{
				if(!world.isRemote)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 0, false, false));
				}
			}
			if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem().equals(ModItems.itemShadowChest));
			{
				if(!world.isRemote)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 19, 0, false, false));
				}
			}
			if(player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem().equals(ModItems.itemShadowLegs))
			{
				if(!world.isRemote)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 19, 0, false, false));
				}
			}
			if(player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem().equals(ModItems.itemShadowFeet))
			{
				if(!world.isRemote)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 19, 0, false, false));
				}
			}
		}
		else if(!player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isHost())
		{
			player.attackEntityFrom(TheDarkness.equipShadowArmor, Int.MaxValue());
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		if(stack.getItem() == ModItems.itemShadowHead)
		{
			tooltip.add(ChatFormatting.DARK_BLUE + "Gaze into the darkness...");
		}
		if(stack.getItem() == ModItems.itemShadowChest)
		{
			tooltip.add(ChatFormatting.DARK_BLUE + "...stand against the light...");
		}
		if(stack.getItem() == ModItems.itemShadowLegs)
		{
			tooltip.add(ChatFormatting.DARK_BLUE + "...and you will reach new heights.");
		}
		if(stack.getItem() == ModItems.itemShadowFeet)
		{
			tooltip.add(ChatFormatting.DARK_BLUE + "Fail to endure...");
		}
	}
}
