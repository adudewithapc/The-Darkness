package thatmartinguy.thedarkness.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thatmartinguy.thedarkness.data.ModWorldData;

public class ReliquaryCraftedMessage implements IMessage
{
    private boolean crafted;

    public ReliquaryCraftedMessage() {}

    public ReliquaryCraftedMessage(boolean isCrafted)
    {
        this.crafted = isCrafted;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        crafted = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(crafted);
    }

    public static class Handler implements IMessageHandler<ReliquaryCraftedMessage, IMessage>
    {
        @Override
        public IMessage onMessage(ReliquaryCraftedMessage message, MessageContext ctx)
        {
            FMLCommonHandler.instance().getWorldThread(ctx.getClientHandler()).addScheduledTask(() ->
            {
                final ModWorldData worldData = ModWorldData.get(Minecraft.getMinecraft().world);
                worldData.setReliquaryCrafted(message.crafted);
            });
            return null;
        }
    }
}
