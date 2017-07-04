package thatmartinguy.thedarkness.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerProxy implements IProxy
{
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
        throw new WrongSideException("Tried to get the client player on the server side");
    }

    @Override
    public EntityPlayer getPlayer(MessageContext ctx)
    {
        if(ctx.side.isServer())
        {
            return ctx.getServerHandler().player;
        }
        throw new WrongSideException("Tried to get the client player on the server side from a client side message");
    }

    @Override
    public World getClientWorld()
    {
        throw new WrongSideException("Tried to get the client world on the server side");
    }

    @Override
    public IThreadListener getThreadListener(MessageContext ctx)
    {
        if(ctx.side.isServer())
            return ctx.getServerHandler().player.mcServer;
        throw new WrongSideException("Tried to get the client thread listener on the server");
    }
}
