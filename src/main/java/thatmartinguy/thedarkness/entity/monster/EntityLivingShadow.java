package thatmartinguy.thedarkness.entity.monster;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thatmartinguy.thedarkness.item.ModItems;

public class EntityLivingShadow extends EntityMob
{

	public EntityLivingShadow(World worldIn)
	{
		super(worldIn);
		this.setSize(1.0F, 1.4F);
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ModItems.swordVoidstone));
		this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(ModItems.swordVoidstone));
	}
	
	@Override
	protected void initEntityAI()
	{
		clearAITasks();
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 4, true));
		this.tasks.addTask(3, new EntityAIBreakDoor(this));
		this.tasks.addTask(5, new EntityAIWander(this, 1));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityLivingShadow.AIFindPlayer(this));
	}
	
	private void clearAITasks()
	{
		this.tasks.taskEntries.clear();
		this.targetTasks.taskEntries.clear();
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0F);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3F);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0F);
	}
	
	@Override
	public boolean canBeAttackedWithItem()
	{
		if(getAttackingEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) getAttackingEntity();
			//if(player.getHeldItemMainhand() != null)
			//{
				return player.getHeldItemMainhand() == new ItemStack(ModItems.swordBrightstone) || player.getHeldItemOffhand() == new ItemStack(ModItems.swordBrightstone);
			//}
			//if(player.getHeldItemOffhand() != null)
			//{
				//return player.getHeldItemOffhand() == new ItemStack(ModItems.swordBrightstone);
			//}
		}
		return false;
	}
	
	@Override
	public boolean hitByEntity(Entity entityIn)
	{
		return super.hitByEntity(entityIn);
	}
	
	private boolean shouldAttackPlayer(@Nullable EntityPlayer player)
	{
		ItemStack heldItemMain = player.getHeldItemMainhand();
		ItemStack heldItemOff = player.getHeldItemOffhand();
		
		if(heldItemMain != null)
		{
			return heldItemMain == new ItemStack(ModItems.swordBrightstone);
		}
		if(heldItemOff != null)
		{
			return heldItemMain == new ItemStack(ModItems.swordBrightstone);
		}
		return false;
	}
	
	@Override
	public void onLivingUpdate()
	{
		if(this.worldObj.isDaytime() && !this.worldObj.isRemote)
		{
			this.setDropItemsWhenDead(false);
			//TODO: Add smoke particles
			this.setDead();
		}
		/**if(this.worldObj.getLight(new BlockPos(this), false) < 7 && !this.worldObj.isRemote)
		{
			this.setFire(2);
		}**/
		
		super.onLivingUpdate();
	}
	
	//AI for finding the nearest player and attacking it if he has a brighstone sword
	static class AIFindPlayer extends EntityAINearestAttackableTarget<EntityPlayer>
	{
		private final EntityLivingShadow livingShadow;
		private EntityPlayer player;
		private int aggroTime;
		
		public AIFindPlayer(EntityLivingShadow entity)
		{
			super(entity, EntityPlayer.class, false);
			this.livingShadow = entity;
		}
		
		@Override
		public boolean shouldExecute()
		{
			double targetDistance = getTargetDistance();
			this.player = this.livingShadow.worldObj.getNearestAttackablePlayer(this.livingShadow.posX, this.livingShadow.posY, this.livingShadow.posZ, targetDistance, targetDistance, (Function<EntityPlayer, Double>)null, new Predicate<EntityPlayer>()
			{
				@Override
				public boolean apply(@Nullable EntityPlayer player)
				{
					System.out.println("Is player null: " + player != null);
					System.out.println("Should the AI attack the player: " + AIFindPlayer.this.livingShadow.shouldAttackPlayer(player));
					return player != null && AIFindPlayer.this.livingShadow.shouldAttackPlayer(player);
				}
			});
			
			return false;
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
				if(!this.livingShadow.shouldAttackPlayer(player))
				{
					return false;
				}
				else
				{
					this.livingShadow.faceEntity(livingShadow, 10.0F, 10.0F);
					return true;
				}
			}
			return false;
		}
		
		@Override
		public void updateTask()
		{
			if(this.player != null)
			{
				if(--this.aggroTime <= 0)
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
