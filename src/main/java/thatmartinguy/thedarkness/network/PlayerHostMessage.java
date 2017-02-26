package thatmartinguy.thedarkness.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.data.capability.IPlayerHostCapability;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;

public class PlayerHostMessage implements IMessage
{
	private boolean isHost;
	
	public PlayerHostMessage() {}
	
	public PlayerHostMessage(boolean isHost)
	{
		this.isHost = isHost;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.isHost = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(isHost);
	}
	
	public static class Handler implements IMessageHandler<PlayerHostMessage, IMessage>
	{
		@Override
		public IMessage onMessage(PlayerHostMessage message, MessageContext ctx)
		{
			Minecraft.getMinecraft().addScheduledTask(new Runnable()
			{
				final EntityPlayer player = TheDarkness.proxy.getClientPlayer();
				@Override
				public void run()
				{
					if(player != null)
					{
						final IPlayerHostCapability hostCapability = PlayerHostProvider.getHostCapability(player);
						if(hostCapability != null)
						{
							hostCapability.setHost(message.isHost);
						}
					}
				}
			});
			
			return null;
		}
		
	}
}