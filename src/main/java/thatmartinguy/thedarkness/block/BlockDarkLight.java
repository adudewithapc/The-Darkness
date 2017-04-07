package thatmartinguy.thedarkness.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thatmartinguy.thedarkness.data.capability.ItemDropHostProvider;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.util.DarkLightCrafting;

public class BlockDarkLight extends BlockBase
{
	public BlockDarkLight(String unlocalizedName, String registryName, Material material)
	{
		super(unlocalizedName, registryName, material);
		this.setCreativeTab(null);
		this.setHardness(0);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		EntityItem[] burningItems = new EntityItem[worldIn.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos)).size()];
		Item[] consumedItems = new Item[burningItems.length];
		
		if(burningItems.length > 0)
		{
			for(int i = 0; i < burningItems.length; i++)
			{
				burningItems[i] = worldIn.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos)).get(i);
				
				if(!burningItems[i].getCapability(ItemDropHostProvider.ITEM_DROPPED_BY_HOST_CAPABILITY, null).isDroppedByHost())
				{
					burningItems[i].setDead();
					worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1, true);
					return;
				}
				
				consumedItems[i] = burningItems[i].getEntityItem().getItem();
			}
		}
		
		ItemStack output = DarkLightCrafting.getOutput(consumedItems);
		List<EntityPlayer> playersWithinRange = worldIn.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB((double)pos.getX() + 16, (double)pos.getY() + 16, (double)pos.getZ() + 16, (double)pos.getX() - 16, (double)pos.getY() - 16, (double)pos.getZ() - 16));
		
		if(playersWithinRange.size() > 0 && output != null)
		{
			for(int i = 0; i < playersWithinRange.size(); i++)
			{
				if(playersWithinRange.get(i).getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isHost())
				{
					worldIn.spawnEntity(new EntityItem(worldIn, playersWithinRange.get(i).posX, playersWithinRange.get(i).posY, playersWithinRange.get(i).posZ, output));
				}
				else
				{
					worldIn.spawnEntity(new EntityLightningBolt(worldIn, playersWithinRange.get(i).posX, playersWithinRange.get(i).posY, playersWithinRange.get(i).posZ, false));
				}
			}
			
			worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 0.3f, true);
			worldIn.setBlockToAir(pos);
		}
		else if(playersWithinRange.size() <= 0)
		{
			for(int i = 0; i < burningItems.length; i++)
			{
				burningItems[i].setDead();
			}
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return NULL_AABB;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullyOpaque(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random)
	{
		return 0;
	}
	
	@Override
	public boolean isCollidable()
	{
		return false;
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.UP);
	}
	
	@Override
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public int tickRate(World worldIn)
	{
		return 1;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		/**if(side != EnumFacing.UP)
			return true;
		else
			return false;**/
		return true;
	}
}
