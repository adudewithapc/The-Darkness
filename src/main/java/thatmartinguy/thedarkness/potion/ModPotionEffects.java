package thatmartinguy.thedarkness.potion;

import net.minecraft.potion.Potion;

public class ModPotionEffects
{
	public static Potion effectReliquary;
	
	public static void init()
	{
		effectReliquary = new EffectReliquary("effect.reliquary", "EffectReliquary");
	}
}
