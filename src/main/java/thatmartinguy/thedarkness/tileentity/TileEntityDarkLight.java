package thatmartinguy.thedarkness.tileentity;

import java.util.List;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.util.DarkLightCrafting;

public class TileEntityDarkLight extends TileEntity implements ITickable
{
	ItemStackHandler stackHandler;
	int occupiedSlots;
	
	public TileEntityDarkLight()
	{
		stackHandler = new ItemStackHandler(2);
		occupiedSlots = 0;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		stackHandler.deserializeNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setTag("burnedItems", stackHandler.serializeNBT());
		return compound;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return true;
		}
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return (T) stackHandler;
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void update()
	{
		List<EntityItem> burningItems = this.world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(this.pos));
		List<EntityPlayer> burningPlayers = this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.pos));
		List<EntityPlayer> playersInRange = this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.pos.getX() + 16, this.pos.getY(), (double)this.pos.getZ() + 16, (double)this.pos.getX() - 16, this.pos.getY(), (double)this.pos.getZ() - 16));
		
		IItemHandler stackHandlerCapability = this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		if(!this.world.isRemote)
		{
			if(burningItems.size() > 0)
			{
				for(int i = 0; i < burningItems.size(); i++)
				{
					System.out.println(burningItems.get(i).getEntityItem().toString());
					stackHandlerCapability.insertItem(occupiedSlots++, burningItems.get(i).getEntityItem(), true);
					burningItems.get(i).setDead();
				}
			}
		}
		
		ItemStack output = DarkLightCrafting.getOutput(stackHandlerCapability.getStackInSlot(0), stackHandlerCapability.getStackInSlot(1));
		
		if(output != null)
		{
			System.out.println(output.toString());
			for(int i = 0; i < playersInRange.size(); i++)
			{
				EntityPlayer currentPlayer = playersInRange.get(i);
				if(currentPlayer.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null).isHost())
				{
					ItemHandlerHelper.giveItemToPlayer(currentPlayer, output);
					currentPlayer.playSound(SoundEvents.BLOCK_PORTAL_TRAVEL, 1.0f, 0.2f);
				}
				else
				{
					this.world.spawnEntity(new EntityLightningBolt(this.world, currentPlayer.posX, currentPlayer.posY, currentPlayer.posZ, false));
				}
				this.world.setBlockToAir(this.pos);
			}
		}
		
		for(int i = 0; i < burningPlayers.size(); i++)
		{
			burningPlayers.get(i).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100));
		}
	}

}