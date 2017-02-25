package thatmartinguy.thedarkness.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ReliquaryMessageHandler implements IMessageHandler<ReliquaryMessage, IMessage>
{
	public ReliquaryMessageHandler()
	{
		
	}
	@Override
	public IMessage onMessage(ReliquaryMessage message, MessageContext ctx)
	{
		System.out.print("Item crafted");
		return null;
	}
}
