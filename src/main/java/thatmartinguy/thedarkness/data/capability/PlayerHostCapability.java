package thatmartinguy.thedarkness.data.capability;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerHostCapability implements IPlayerHostCapability
{
    private boolean transforming;
    private boolean host;
    private boolean followed;

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

    @Override
    public void setFollowed(boolean followed)
    {
        this.followed = followed;
    }

    @Override
    public boolean isFollowed()
    {
        return followed;
    }
}
