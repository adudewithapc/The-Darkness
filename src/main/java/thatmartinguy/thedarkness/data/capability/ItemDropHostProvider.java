package thatmartinguy.thedarkness.data.capability;

import javax.annotation.Nullable;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.network.DroppedByHostMessage;
import thatmartinguy.thedarkness.util.CapabilityUtils;
import thatmartinguy.thedarkness.util.Reference;

public class ItemDropHostProvider implements ICapabilitySerializable<NBTBase>
{
	@CapabilityInject(IItemDropHostCapability.class)
	public static Capability<IItemDropHostCapability> ITEM_DROPPED_BY_HOST_CAPABILITY = null;
	
	private static IItemDropHostCapability instance = ITEM_DROPPED_BY_HOST_CAPABILITY.getDefaultInstance();
	private static final ResourceLocation IDENTIFIER = new ResourceLocation(Reference.MOD_ID, "droppedByHost");
	
	public void register()
	{
		CapabilityManager.INSTANCE.register(IItemDropHostCapability.class, new Capability.IStorage<IItemDropHostCapability>()
		{
			@Override
			public NBTBase writeNBT(Capability<IItemDropHostCapability> capability, IItemDropHostCapability instance, EnumFacing side)
			{
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setBoolean("droppedByHost", instance.isDroppedByHost());
				return nbt;
			}

			@Override
			public void readNBT(Capability<IItemDropHostCapability> capability, IItemDropHostCapability instance, EnumFacing side, NBTBase nbt)
			{
				NBTTagCompound compound = (NBTTagCompound) nbt;
				instance.setDroppedByHost(compound.getBoolean("droppedByHost"));
			}
		}, ItemDropHostCapability::new);
	}
	
	@Nullable
	public static IItemDropHostCapability getDroppedCapability(EntityItem item)
	{
		return CapabilityUtils.getCapability(item, ITEM_DROPPED_BY_HOST_CAPABILITY, null);
	}
	
	private void sendToPlayer(ItemDropHostCapability capability, EntityPlayerMP player, boolean droppedByHost)
	{
		TheDarkness.NETWORK.sendTo(new DroppedByHostMessage(capability.isDroppedByHost()), player);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == ITEM_DROPPED_BY_HOST_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if(capability == ITEM_DROPPED_BY_HOST_CAPABILITY)
		{
			return ITEM_DROPPED_BY_HOST_CAPABILITY.cast(instance);
		}
		
		return null;
	}

	@Override
	public NBTBase serializeNBT()
	{
		return ITEM_DROPPED_BY_HOST_CAPABILITY.getStorage().writeNBT(ITEM_DROPPED_BY_HOST_CAPABILITY, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		ITEM_DROPPED_BY_HOST_CAPABILITY.getStorage().readNBT(ITEM_DROPPED_BY_HOST_CAPABILITY, this.instance, null, nbt);
	}

}
