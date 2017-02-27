package thatmartinguy.thedarkness.data.capability;

public class PlayerHostCapability implements IPlayerHostCapability
{
	private boolean isHost;
	
	@Override
	public void setHost(boolean isHost)
	{
		this.isHost = isHost;
	}
	
	@Override
	public boolean isHost()
	{
		return this.isHost;
	}
}
