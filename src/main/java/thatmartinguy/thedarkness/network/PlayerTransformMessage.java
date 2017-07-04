package thatmartinguy.thedarkness.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.data.capability.IPlayerHostCapability;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.util.LogHelper;

public class PlayerTransformMessage implements IMessage
{
    private boolean transforming;

    public PlayerTransformMessage() {}

    public PlayerTransformMessage(boolean isTransforming)
    {
        transforming = isTransforming;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        transforming = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(transforming);
    }

    public static class Handler implements IMessageHandler<PlayerTransformMessage, IMessage>
    {
        @Override
        public IMessage onMessage(PlayerTransformMessage message, MessageContext ctx)
        {
            TheDarkness.proxy.getThreadListener(ctx).addScheduledTask(() ->
            {
                final EntityPlayer player = TheDarkness.proxy.getClientPlayer();

                if(player != null)
                {
                    final IPlayerHostCapability capability = player.getCapability(PlayerHostProvider.PLAYER_HOST_CAPABILITY, null);

                    if(capability != null)
                    {
                        capability.setTransforming(message.transforming);
                        LogHelper.info(player.getName() + " transforming = " + message.transforming);
                    }
                }
            });
            return null;
        }
    }
}
