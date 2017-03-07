package thatmartinguy.thedarkness.entity.dummy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import thatmartinguy.thedarkness.util.ShadowRecipes;

public class EntityShadowCrafter extends EntityMob
{
	public EntityShadowCrafter(World worldIn)
	{
		super(worldIn);
	}
	
	@Override
	protected void initEntityAI()
	{
		this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, Float.MAX_VALUE));
		this.tasks.addTask(0, new EntityAICraftShadowRecipe(this));
	}
	
	@Override
	public boolean hitByEntity(Entity entityIn)
	{
		this.entityDropItem(this.getHeldItemMainhand(), this.getHeldItemMainhand().getCount());
		this.entityDropItem(this.getHeldItemOffhand(), this.getHeldItemOffhand().getCount());
		this.setDropItemsWhenDead(false);
		this.setDead();
		return true;
	}
	
	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand stack)
	{
		if(!player.world.isRemote)
		{
			if(player.getHeldItemMainhand() != null)
			{
				if(this.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.AIR))
				{
					System.out.println("BEFORE: In main hand: " + this.getHeldItemMainhand().toString()); 
					this.setHeldItem(EnumHand.MAIN_HAND, player.getHeldItemMainhand());
					System.out.println("AFTER: In main hand: " + this.getHeldItemMainhand().toString());
					player.getHeldItemMainhand().setCount(player.getHeldItemMainhand().getCount() - 1);
				}
				else if(this.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.AIR))
				{
					this.setHeldItem(EnumHand.OFF_HAND, player.getHeldItemMainhand());
					player.getHeldItemMainhand().setCount(player.getHeldItemMainhand().getCount() - 1);
				}
				else
				{
					this.entityDropItem(this.getHeldItemMainhand(), this.getHeldItemMainhand().getCount());
					this.entityDropItem(this.getHeldItemOffhand(), this.getHeldItemOffhand().getCount());
				}
			}
			else
			{
				this.entityDropItem(this.getHeldItemMainhand(), this.getHeldItemMainhand().getCount());
				this.entityDropItem(this.getHeldItemOffhand(), this.getHeldItemOffhand().getCount());
			}
		}
		return EnumActionResult.SUCCESS;
	}
	
	static class EntityAICraftShadowRecipe extends EntityAIBase
	{
		private EntityShadowCrafter shadowCrafter;
		private ItemStack output;
		private BlockPos crafterPosition;
		private int craftingCountdown;
		
		public EntityAICraftShadowRecipe(EntityShadowCrafter shadowCrafter)
		{
			this.shadowCrafter = shadowCrafter;
			this.crafterPosition = new BlockPos(this.shadowCrafter);
			this.craftingCountdown = 0;
		}
		
		@Override
		public boolean shouldExecute()
		{
			Item[] items = new Item[2];
			items[0] = this.shadowCrafter.getHeldItemMainhand().getItem();
			items[1] = this.shadowCrafter.getHeldItemOffhand().getItem();
			output = ShadowRecipes.getOuput(items);
			return output != null && output.getItem() != Item.getItemFromBlock(Blocks.AIR);
		}
		
		@Override
		public void resetTask()
		{
			this.craftingCountdown = 0;
		}
		
		@Override
		public void updateTask()
		{
			if(craftingCountdown < 60)
			{
				craftingCountdown++;
			}
			else
			{
				this.shadowCrafter.world.spawnEntity(new EntityItem(this.shadowCrafter.world, crafterPosition.getX(), crafterPosition.getY(), crafterPosition.getZ(), output));
				this.shadowCrafter.setDropItemsWhenDead(false);
				this.shadowCrafter.setDead();
			}
		}
	}
}
