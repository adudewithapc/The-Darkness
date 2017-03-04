package thatmartinguy.thedarkness.entity.mob;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import thatmartinguy.thedarkness.data.capability.IPlayerHostCapability;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;

public class EntityHuman extends EntityMob
{
	public EntityHuman(World worldIn)
	{
		super(worldIn);
		this.setAIMoveSpeed(0.2F);
		this.setSize(1.0F, 1.0F);
	}
	
	@Override
	protected void initEntityAI()
	{
		clearTasks();
		
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.5D));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.targetTasks.addTask(1, new EntityAINearestHost(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
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
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}
	
	protected static class EntityAINearestHost extends EntityAINearestAttackableTarget<EntityPlayer>
	{
		private EntityHuman human;
		private EntityPlayer player;
		private int aggroTime;
		
		public EntityAINearestHost(EntityHuman human)
		{
			super(human, EntityPlayer.class, true);
			this.human = human;
		}
		
		private boolean isPlayerHost(@Nullable EntityPlayer player)
		{
			if(player != null)
			{
				IPlayerHostCapability host = player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null);
				if(host.isHost())
				{
					return true;
				}
			}
			return false;
		}
		
		@Override
		public boolean shouldExecute()
		{
			double targetDistance = this.getTargetDistance();
			this.player = this.human.world.getNearestAttackablePlayer(this.human.posX, this.human.posY, this.human.posZ, targetDistance, targetDistance, (Function<EntityPlayer, Double>)null, new Predicate<EntityPlayer>()
			{
				
				@Override
				public boolean apply(EntityPlayer input)
				{
					return input != null && isPlayerHost(input);
				}
			});
			return this.player != null;
		}
		
		@Override
		public void startExecuting()
		{
			this.aggroTime = 5;
		}
		
		@Override
		public void resetTask()
		{
			this.player = null;
			super.resetTask();
		}
		
		@Override
		public boolean continueExecuting()
		{
			if(this.player != null)
			{
				if(!this.isPlayerHost(player))
				{
					return false;
				}
				else
				{
					this.human.faceEntity(player, 10.0F, 10.0F);
					return true;
				}
			}
			else
			{
				if(this.targetEntity != null && ((EntityPlayer)this.targetEntity).isEntityAlive() && this.isPlayerHost(player))
				{
					return true;
				}
				return false;
			}
		}
		
		@Override
		public void updateTask()
		{
			if(this.player != null)
			{
				if(this.aggroTime <= 0)
				{
					this.targetEntity = this.player;
					this.player = null;
					super.startExecuting();
				}
			}
			else
			{
				super.updateTask();
			}
		}
	}
}
