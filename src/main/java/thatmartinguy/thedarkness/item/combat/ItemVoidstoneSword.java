package thatmartinguy.thedarkness.item.combat;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import scala.Int;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.util.Reference;

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
		tooltip.add(ChatFormatting.DARK_BLUE + "...and I will consume you.");
	}
	
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if(entityIn instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entityIn;
			if(player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isHost())
			{
				if(!worldIn.isDaytime())
				{
					if(player.getHeldItemMainhand() != null)
					{
						if(player.getHeldItemMainhand().getItem() == this)
						{
							player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH));
						}
					}
				}
			}
			else
			{
				if(player.getHeldItemMainhand() != null)
				{
					if(player.getHeldItemMainhand().getItem() == this)
					{
						player.attackEntityFrom(new DamageSource("wieldsword"), Int.MaxValue());
					}
				}
			}
		}
	}
}