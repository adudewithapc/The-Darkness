package thatmartinguy.thedarkness.network;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thatmartinguy.thedarkness.data.ModWorldData;

public class ReliquaryMessage implements IMessage
{
	private boolean reliquaryCrafted;
	
	public ReliquaryMessage() {}
	
	public ReliquaryMessage(boolean reliquaryCrafted)
	{
		this.reliquaryCrafted = reliquaryCrafted;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		reliquaryCrafted = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(reliquaryCrafted);
	}

	public static class Handler implements IMessageHandler<ReliquaryMessage, IMessage>
	{
		@Nullable
		@Override
		public IMessage onMessage(final ReliquaryMessage message, MessageContext ctx)
		{
			FMLCommonHandler.instance().getWorldThread(ctx.getClientHandler()).addScheduledTask(new Runnable()
				{
					@Override
					public void run()
					{
						final ModWorldData worldData = ModWorldData.get(Minecraft.getMinecraft().world);
						worldData.setReliquaryCrafted(message.reliquaryCrafted);
					}
				});
			return null;
		}
		
	}
}
