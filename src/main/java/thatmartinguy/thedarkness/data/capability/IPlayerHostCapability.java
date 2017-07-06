package thatmartinguy.thedarkness.data.capability;

public interface IPlayerHostCapability
{
    public void setTransforming(boolean isTransforming);
    public boolean isTransforming();

    public void setHost(boolean isHost);
    public boolean isHost();

    public void setFollowed(boolean followed);
    public boolean isFollowed();
}
