package thatmartinguy.thedarkness.data.capability;

public class ItemDropHostCapability implements IItemDropHostCapability
{
	private boolean droppedByHost;
	
	@Override
	public void setDroppedByHost(boolean hostDropped)
	{
		this.droppedByHost = hostDropped;
	}

	@Override
	public boolean isDroppedByHost()
	{
		return this.droppedByHost;
	}
}
