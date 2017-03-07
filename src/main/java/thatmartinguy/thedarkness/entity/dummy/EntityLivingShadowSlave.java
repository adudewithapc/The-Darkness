package thatmartinguy.thedarkness.entity.dummy;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thatmartinguy.thedarkness.item.ModItems;

public class EntityLivingShadowSlave extends EntityMob
{
	private EntityLivingBase target;
	
	public EntityLivingShadowSlave(World world)
	{
		super(world);
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ModItems.swordVoidstone));
		this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(ModItems.swordVoidstone));
	}
	
	public EntityLivingShadowSlave(World worldIn, @Nullable EntityLivingBase target, double posX, double posY, double posZ)
	{
		super(worldIn);
		this.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
		this.target = target;
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ModItems.swordVoidstone));
		this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(ModItems.swordVoidstone));
	}
	
	@Override
	protected void initEntityAI()
	{
		clearTasks();
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.2, true));
	}
	
	private void clearTasks()
	{
		this.tasks.taskEntries.clear();
		this.targetTasks.taskEntries.clear();
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
	}
	
	static class EntityAIFindTarget extends EntityAINearestAttackableTarget<EntityLivingBase>
	{
		private EntityLivingShadowSlave shadowSlave;
		private EntityLivingBase target;
		
		public EntityAIFindTarget(EntityLivingShadowSlave shadowSlave, Class<EntityLivingBase> classTarget, @Nullable EntityLivingBase target)
		{
			super(shadowSlave, classTarget, false, false);
			this.shadowSlave = shadowSlave;
			this.target = target;
		}
		
		@Override
		public boolean shouldExecute()
		{
			if(target != null)
			{
				this.shadowSlave.faceEntity(target, 10.0F, 10.0F);
				return true;
			}
			return false;
		}
		
		@Override
		public void resetTask()
		{
			this.shadowSlave.setDropItemsWhenDead(false);
			this.shadowSlave.setDead();
		}
		
		@Override
		public boolean continueExecuting()
		{
			if(this.targetEntity != null && this.targetEntity.isEntityAlive())
			{
				return true;
			}
			return false;
		}
		
		@Override
		public void updateTask()
		{
			if(this.target != null)
			{
				this.targetEntity = target;
				this.target = null;
				super.startExecuting();
			}
		}
	}
}
