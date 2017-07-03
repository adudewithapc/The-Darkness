package thatmartinguy.thedarkness.data;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.network.ReliquaryCraftedMessage;
import thatmartinguy.thedarkness.util.Reference;

public class ModWorldData extends WorldSavedData
{
    private static final String IDENTIFIER = Reference.MOD_ID + "reliquary";

    private World world;
    private boolean reliquaryCrafted;

    private static ModWorldData instance;

    public void setReliquaryCrafted(boolean crafted)
    {
        this.reliquaryCrafted = crafted;
        if(!world.isRemote)
            TheDarkness.network.sendToAll(new ReliquaryCraftedMessage(this.reliquaryCrafted));
        this.markDirty();
    }

    public boolean isReliquaryCrafted()
    {
        return reliquaryCrafted;
    }

    public ModWorldData()
    {
        super(IDENTIFIER);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        this.reliquaryCrafted = nbt.getBoolean("reliquaryCrafted");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setBoolean("reliquaryCrafted", this.reliquaryCrafted);
        return compound;
    }

    public static ModWorldData get(World world)
    {
        ModWorldData worldData;

        if(!world.isRemote && instance != null)
        {
            worldData = instance;
        }
        else
        {
            worldData = (ModWorldData) world.loadData(ModWorldData.class, IDENTIFIER);

            if(worldData == null)
            {
                worldData = new ModWorldData();
            }

            if(worldData.world == null)
            {
                worldData.world = world;
            }
        }
        world.setData(IDENTIFIER, worldData);

        return worldData;
    }

    @EventBusSubscriber
    public static class EventHandler
    {
        private static void sendToPlayer(EntityPlayerMP player)
        {
            final ModWorldData worldData = ModWorldData.get(player.getEntityWorld());
            TheDarkness.network.sendTo(new ReliquaryCraftedMessage(worldData.isReliquaryCrafted()), player);
        }

        @SubscribeEvent
        public static void playerLoggedIn(PlayerLoggedInEvent event)
        {
            if (event.player instanceof EntityPlayerMP)
            {
                EntityPlayerMP player = (EntityPlayerMP) event.player;
                sendToPlayer(player);
            }
        }

        @SubscribeEvent
        public static void playerChangedDimension(PlayerChangedDimensionEvent event)
        {
            if (event.player instanceof EntityPlayerMP)
            {
                EntityPlayerMP player = (EntityPlayerMP) event.player;
                sendToPlayer(player);
            }
        }
    }
}
