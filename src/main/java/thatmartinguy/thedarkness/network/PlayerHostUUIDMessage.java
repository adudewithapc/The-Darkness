package thatmartinguy.thedarkness.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thatmartinguy.thedarkness.data.ModWorldData;

public class PlayerHostUUIDMessage implements IMessage
{
	private String playerUUID;
	
	public PlayerHostUUIDMessage() {}
	
	public PlayerHostUUIDMessage(String playerUUID)
	{
		this.playerUUID = playerUUID;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.playerUUID = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, playerUUID);
	}
	
	public static class Handler implements IMessageHandler<PlayerHostUUIDMessage, IMessage>
	{
		@Override
		public IMessage onMessage(PlayerHostUUIDMessage message, MessageContext ctx)
		{
			FMLCommonHandler.instance().getWorldThread(ctx.getClientHandler()).addScheduledTask(new Runnable()
			{
				@Override
				public void run()
				{
					ModWorldData worldData = ModWorldData.get(Minecraft.getMinecraft().world);
					worldData.setHostUUID(message.playerUUID);
				}
			});
			
			return null;
		}
	}
}
