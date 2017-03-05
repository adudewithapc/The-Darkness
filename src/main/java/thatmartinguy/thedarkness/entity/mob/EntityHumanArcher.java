package thatmartinguy.thedarkness.entity.mob;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityHumanArcher extends EntityHuman implements IRangedAttackMob
{
	public EntityHumanArcher(World worldIn)
	{
		super(worldIn);
		this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
		this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.LEATHER_LEGGINGS));
		this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
	}
	
	@Override
	protected void initEntityAI()
	{
		super.initEntityAI();
		this.tasks.addTask(1, new EntityAIAttackRanged(this, 0.2, 30, 16.0F));
		this.targetTasks.addTask(1, new EntityAINearestHost(this));
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
	{
		EntityArrow arrow = this.getArrow(distanceFactor);
		double targetX = target.posX - this.posX;
		double targetY = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - arrow.posY;
		double targetZ = target.posZ - this.posZ;
		double d0 = MathHelper.sqrt(targetX * targetX + targetZ * targetZ);
		arrow.setThrowableHeading(targetX, targetY + d0 * 0.20000000298023224D, targetZ, 1.6F, (float)(10 - this.world.getDifficulty().getDifficultyId() * 4));
		this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.world.spawnEntity(arrow);
	}
	
	protected EntityArrow getArrow(float distanceFactor)
	{
		EntityTippedArrow arrow = new EntityTippedArrow(this.world, this);
		arrow.setEnchantmentEffectsFromEntity(this, distanceFactor);
		return arrow;
	}
}
