package thatmartinguy.thedarkness.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface IProxy
{
    public void preInit();

    public void init();

    public EntityPlayer getClientPlayer();

    public EntityPlayer getPlayer(MessageContext ctx);

    public World getClientWorld();

    public IThreadListener getThreadListener(MessageContext ctx);

    class WrongSideException extends RuntimeException
    {
        public WrongSideException(final String message)
        {
            super(message);
        }

        public WrongSideException(final String message, final Throwable cause)
        {
            super(message, cause);
        }
    }
}
