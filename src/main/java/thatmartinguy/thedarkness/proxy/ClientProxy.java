package thatmartinguy.thedarkness.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy implements IProxy
{
    private final Minecraft MINECRAFT = Minecraft.getMinecraft();

    @Override
    public void preInit()
    {

    }

    @Override
    public void init()
    {

    }

    @Override
    public EntityPlayer getClientPlayer()
    {
        return MINECRAFT.player;
    }

    @Override
    public EntityPlayer getPlayer(final MessageContext ctx)
    {
        if(ctx.side.isClient())
            return MINECRAFT.player;
        else
            return ctx.getServerHandler().player;
    }

    @Override
    public World getClientWorld()
    {
        return MINECRAFT.world;
    }

    @Override
    public IThreadListener getThreadListener(MessageContext ctx)
    {
        if(ctx.side.isClient())
        {
            return MINECRAFT;
        }
        else
        {
            return ctx.getServerHandler().player.mcServer;
        }
    }


}
