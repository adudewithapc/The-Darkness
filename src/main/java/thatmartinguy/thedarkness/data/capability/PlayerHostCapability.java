package thatmartinguy.thedarkness.data.capability;

public class PlayerHostCapability implements IPlayerHostCapability
{
    private boolean transforming;
    private boolean host;

    @Override
    public void setTransforming(boolean isTransforming)
    {
        transforming = isTransforming;
    }

    @Override
    public boolean isTransforming()
    {
        return transforming;
    }

    @Override
    public void setHost(boolean isHost)
    {
        host = isHost;
        if(host)
            transforming = false;
    }

    @Override
    public boolean isHost()
    {
        return host;
    }
}
