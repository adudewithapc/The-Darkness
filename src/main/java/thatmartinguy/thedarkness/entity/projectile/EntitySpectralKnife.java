package thatmartinguy.thedarkness.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import thatmartinguy.thedarkness.entity.dummy.EntityLivingShadowSlave;

public class EntitySpectralKnife extends EntityThrowable
{
	public EntitySpectralKnife(World world)
	{
		super(world);
	}
	
	public EntitySpectralKnife(World worldIn, EntityLivingBase thrower)
	{
		super(worldIn, thrower);
	}
	@Override
	protected void onImpact(RayTraceResult result)
	{
		if(result.entityHit != null)
		{
			EntityLivingBase target = (EntityLivingBase) result.entityHit;
			
			if(!world.isRemote)
			{
				target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100));
				world.spawnEntity(new EntityLivingShadowSlave(world, target, target.posX, target.posY, target.posZ));
			}
		}
		if(!world.isRemote)
		{
			this.world.setEntityState(this, (byte)3);
			this.setDead();
		}
	}
}
