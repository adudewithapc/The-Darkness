package thatmartinguy.thedarkness.data.capability;

public class PlayerHostCapability implements IPlayerHostCapability
{
	private boolean isHost;
	
	public void setHost(boolean isHost)
	{
		this.isHost = isHost;
	}
	
	public boolean isHost()
	{
		return this.isHost;
	}
}
