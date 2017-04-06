package thatmartinguy.thedarkness.network;

import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thatmartinguy.thedarkness.data.capability.IItemDropHostCapability;
import thatmartinguy.thedarkness.data.capability.ItemDropHostProvider;

public class DroppedByHostMessage implements IMessage
{
	private boolean droppedByHost;
	
	public DroppedByHostMessage() {}
	
	public DroppedByHostMessage(boolean hostDropped)
	{
		this.droppedByHost = hostDropped;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.droppedByHost = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(this.droppedByHost);
	}
	
	public static class Handler implements IMessageHandler<DroppedByHostMessage, IMessage>
	{
		@Override
		public IMessage onMessage(DroppedByHostMessage message, MessageContext ctx)
		{
			FMLCommonHandler.instance().getWorldThread(ctx.getClientHandler()).addScheduledTask(new Runnable()
			{
				@Override
				public void run()
				{
					List<EntityItem> worldItems = Minecraft.getMinecraft().player.world.getEntities(EntityItem.class, null);
					
					for(int i = 0; i < worldItems.size(); i++)
					{
						final IItemDropHostCapability dropCapability = ItemDropHostProvider.getDroppedCapability(worldItems.get(i));
						if(dropCapability != null)
						{
							dropCapability.setDroppedByHost(message.droppedByHost);
						}
					}
				}
			});
			
			return null;
		}
	}
}
