package thatmartinguy.thedarkness.entity.mob;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thatmartinguy.thedarkness.item.ModItems;
import thatmartinguy.thedarkness.loot.ModLootTableList;

public class EntityLivingShadow extends EntityMob
{
	public EntityLivingShadow(World worldIn)
	{
		super(worldIn);
		this.setAIMoveSpeed(0.3F);
		this.setSize(1.0F, 1.4F);
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ModItems.swordVoidstone));
		this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(ModItems.swordVoidstone));
		//this.inventoryHandsDropChances[0] = 0;
		//this.inventoryHandsDropChances[1] = 0;
		//this.inventoryHandsDropChances[2] = 0;
	}

	
	@Override
	protected void initEntityAI()
	{
		clearTasks();
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(3, new EntityAIWander(this, 1.5D));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityLivingShadow.EntityAIFindPlayer(this));
	}
	
	protected void clearTasks()
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
	
	@Override
	public void onLivingUpdate()
	{
		if(this.world.isDaytime() && !this.world.isRemote && this.world.canSeeSky(new BlockPos(this)))
		{
			this.setDropItemsWhenDead(false);
			this.setDead();
		}
		
		super.onLivingUpdate();
	}
	
	//Only allows the player to hit the shadow if he is holding a brighstone sword or is in creative
	@Override
	public boolean hitByEntity(Entity entity)
	{
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			if(player.isCreative())
			{
				return false;
			}
			else if(player.getHeldItemMainhand() != null)
			{
				if(player.getHeldItemMainhand().getItem() == ModItems.swordBrightstone)
				{
					return false;
				}
			}
			else if(player.getHeldItemOffhand() != null)
			{
				if(player.getHeldItemOffhand().getItem() == ModItems.swordBrightstone)
				{
					return false;
				}
			}
			else if(player.isCreative())
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	protected ResourceLocation getLootTable()
	{
		return ModLootTableList.LIVING_SHADOW_LOOT;
	}
	//The AI for checking if the living shadow should attack the player
	static class EntityAIFindPlayer extends EntityAINearestAttackableTarget<EntityPlayer>
	{
		private EntityLivingShadow livingShadow;
		private EntityPlayer player;
		private int aggroTime;
		
		public EntityAIFindPlayer(EntityLivingShadow livingShadow)
		{
			super(livingShadow, EntityPlayer.class, false);
			this.livingShadow = livingShadow;
		}
		
		private boolean shouldAttackPlayer(@Nullable EntityPlayer player)
		{
			if(player != null)
			{
				if(player.getHeldItemMainhand() != null)
				{
					if(player.getHeldItemMainhand().getItem() == ModItems.swordBrightstone)
					{
						return true;
					}
				}
				else if(player.getHeldItemOffhand() != null)
				{
					if(player.getHeldItemOffhand().getItem() == ModItems.swordBrightstone)
					{
						return true;
					}
				}
			}
			return false;
		}
		
		@Override
		public boolean shouldExecute()
		{
			double targetDistance = this.getTargetDistance();
			this.player = this.livingShadow.world.getNearestAttackablePlayer(this.livingShadow.posX, this.livingShadow.posY, this.livingShadow.posZ, targetDistance, targetDistance, (Function<EntityPlayer, Double>)null, new Predicate<EntityPlayer>()
			{
				@Override
				public boolean apply(EntityPlayer input)
				{
					return input != null && shouldAttackPlayer(input);
				}
			});
			return this.player != null;
		}
		
		@Override
		public void startExecuting()
		{
			this.aggroTime = 2;
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
				if(!this.shouldAttackPlayer(player))
				{
					return false;
				}
				else 
				{
					this.livingShadow.faceEntity(player, 10.0F, 10.0F);
					return true;
				}
			}
			else
			{
				if(this.targetEntity != null && ((EntityPlayer)this.targetEntity).isEntityAlive() && this.shouldAttackPlayer(targetEntity))
				{
					return true;
				}
				this.livingShadow.heal(Float.MAX_VALUE);
				return false;
			}
		}
		
		@Override
		public void updateTask()
		{
			if(this.player != null)
			{
				if(--this.aggroTime <= 0)
				{
					this.targetEntity = player;
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