package thatmartinguy.thedarkness.data.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerHostProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IPlayerHostCapability.class)
    public static final Capability<IPlayerHostCapability> PLAYER_HOST_CAPABILITY = null;

    private final IPlayerHostCapability instance = PLAYER_HOST_CAPABILITY.getDefaultInstance();

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IPlayerHostCapability.class, new Capability.IStorage<IPlayerHostCapability>()
        {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<IPlayerHostCapability> capability, IPlayerHostCapability instance, EnumFacing side)
            {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setBoolean("Transforming", instance.isTransforming());
                nbt.setBoolean("Host", instance.isHost());
                return nbt;
            }

            @Override
            public void readNBT(Capability<IPlayerHostCapability> capability, IPlayerHostCapability instance, EnumFacing side, NBTBase nbt)
            {
                NBTTagCompound compound = (NBTTagCompound) nbt;
                instance.setTransforming(compound.getBoolean("Transforming"));
                instance.setHost(compound.getBoolean("Host"));
            }
        }, PlayerHostCapability::new);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == PLAYER_HOST_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        if(capability == PLAYER_HOST_CAPABILITY)
        {
            return PLAYER_HOST_CAPABILITY.cast(instance);
        }
        return null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return PLAYER_HOST_CAPABILITY.getStorage().writeNBT(PLAYER_HOST_CAPABILITY, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        PLAYER_HOST_CAPABILITY.getStorage().readNBT(PLAYER_HOST_CAPABILITY, instance, null, nbt);
    }
}
