package thatmartinguy.thedarkness.potion;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;

public class PotionFading extends Potion
{
    public PotionFading(String name)
    {
        super(true, 5);
        this.setRegistryName(name);
        this.setPotionName();
    }

    public Potion setPotionName()
    {
        return super.setPotionName(getRegistryName().toString());
    }

    @Override
    public int getStatusIconIndex()
    {
        return MobEffects.INVISIBILITY.getStatusIconIndex();
    }
}
