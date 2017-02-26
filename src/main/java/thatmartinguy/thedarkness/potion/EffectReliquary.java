package thatmartinguy.thedarkness.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import thatmartinguy.thedarkness.util.Reference;

public class EffectReliquary extends Potion
{
	
	public EffectReliquary(String potionName, String registryName)
	{
		this(true, 0);
		this.setPotionName(potionName);
		this.setRegistryName(registryName);
		this.REGISTRY.register(REGISTRY.getKeys().size() + 1, new ResourceLocation(Reference.MOD_ID, "effectReliquary"), this);
	}
	protected EffectReliquary(boolean isBadEffectIn, int liquidColorIn)
	{
		super(isBadEffectIn, liquidColorIn);
	}
	
	@Override
	public boolean shouldRender(PotionEffect effect)
	{
		return false;
	}
	
	@Override
	public boolean shouldRenderHUD(PotionEffect effect)
	{
		return false;
	}
	
	@Override
	public boolean shouldRenderInvText(PotionEffect effect)
	{
		return false;
	}
	
	public int getPotionID()
	{
		return this.getIdFromPotion(this);
	}
}
