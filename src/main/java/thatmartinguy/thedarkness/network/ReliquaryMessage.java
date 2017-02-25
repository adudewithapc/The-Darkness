package thatmartinguy.thedarkness.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import thatmartinguy.thedarkness.data.ReliquaryWorldData;

public class ReliquaryMessage implements IMessage
{
	private boolean reliquaryCrafted;
	
	public ReliquaryMessage() {}
	
	public ReliquaryMessage(boolean reliquaryCrafted)
	{
		this.reliquaryCrafted = reliquaryCrafted;
	}
	
	public void setReliquaryCrafted(boolean isCrafted)
	{
		this.reliquaryCrafted = isCrafted;
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

}
