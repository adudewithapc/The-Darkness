package thatmartinguy.thedarkness.entity.mob;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import thatmartinguy.thedarkness.data.capability.IPlayerHostCapability;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;

public class EntityHumanCivilian extends EntityHuman
{
	public EntityHumanCivilian(World worldIn)
	{
		super(worldIn);
	}
	
	@Override
	protected void initEntityAI()
	{
		super.initEntityAI();
		this.targetTasks.taskEntries.clear();
		this.tasks.addTask(1, new EntityAIAvoidHost(this, EntityPlayer.class, 12.0F, 0.6D, 0.6D));
		this.tasks.addTask(1, new EntityAIAvoidEntity<>(this, EntityLivingShadow.class, 12.0F, 0.6D, 0.6D));
	}
	
	static class EntityAIAvoidHost extends EntityAIAvoidEntity<EntityPlayer>
	{
		private final Predicate canBeSeenSelector;
		private EntityHumanCivilian humanCiv;
		private Class<EntityPlayer> player;
		private float avoidDistance;
		private double farSpeed;
		private double nearSpeed;
		private PathNavigate pathNavigate;
		private Path humanCivPath;
		
		public EntityAIAvoidHost(EntityHumanCivilian humanCiv, Class<EntityPlayer> classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
		{
			super(humanCiv, classToAvoidIn, avoidDistanceIn, 0, 0);
			this.canBeSeenSelector = new Predicate<EntityPlayer>()
			{
				@Override
				public boolean apply(EntityPlayer input)
				{
					return input.isEntityAlive() && EntityAIAvoidHost.this.humanCiv.getEntitySenses().canSee(input) && isPlayerHost(input);
				}	
			};
			this.humanCiv = humanCiv;
			this.player = classToAvoidIn;
			this.avoidDistance = avoidDistanceIn;
			this.farSpeed = farSpeedIn;
			this.nearSpeed = nearSpeedIn;
			this.pathNavigate = humanCiv.getNavigator();
			this.setMutexBits(1);
		}
		
		private boolean isPlayerHost(@Nullable EntityPlayer player)
		{
			if(player != null)
			{
				IPlayerHostCapability host = player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null);
				return host.isHost();
			}
			return false;
		}
		
		@Override
		public boolean shouldExecute()
		{
			List<EntityPlayer> validNearbyTargets = this.humanCiv.world.<EntityPlayer>getEntitiesWithinAABB(player, this.humanCiv.getEntityBoundingBox().expand((double)this.avoidDistance, 3.0D, (double)this.avoidDistance), Predicates.and(new Predicate[] { EntitySelectors.CAN_AI_TARGET, canBeSeenSelector}));
			
			if(validNearbyTargets.isEmpty())
			{
				return false;
			}
			else
			{
				this.closestLivingEntity = validNearbyTargets.get(0);
				Vec3d randomBlock = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.humanCiv, 16, 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));
				
				if(randomBlock == null)
				{
					return false;
				}
				else if(this.closestLivingEntity.getDistanceSq(randomBlock.xCoord, randomBlock.yCoord, randomBlock.zCoord) < this.closestLivingEntity.getDistanceSqToEntity(this.humanCiv))
				{
					return false;
				}
				else
				{
					this.humanCivPath = this.pathNavigate.getPathToXYZ(randomBlock.xCoord, randomBlock.yCoord, randomBlock.zCoord);
					return this.humanCivPath != null;
				}
			}
		}
		
		@Override
		public boolean continueExecuting()
		{
			return !this.pathNavigate.noPath();
		}
		
		@Override
		public void startExecuting()
		{
			this.pathNavigate.setPath(humanCivPath, this.farSpeed);
		}
		
		@Override
		public void resetTask()
		{
			this.closestLivingEntity = null;
			this.humanCiv.setSprinting(false);
		}
		
		@Override
		public void updateTask()
		{
			this.humanCiv.setSprinting(true);
		}
	}
}
