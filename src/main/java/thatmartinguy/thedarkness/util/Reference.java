package thatmartinguy.thedarkness.util;

import net.minecraft.util.ResourceLocation;

public class Reference
{
    public static final String MOD_ID = "thedarkness";
    public static final String MOD_NAME = "The Darkness";
    public static final String MOD_VERSION = "0.0.1";

    public static final String PROXY_LOCATION = "thatmartinguy.thedarkness.proxy";
    public static final String CLIENT_PROXY_LOCATION = PROXY_LOCATION + ".ClientProxy";
    public static final String SERVER_PROXY_LOCATION = PROXY_LOCATION + ".ServerProxy";

    public static ResourceLocation createResourceLocation(String name)
    {
        return new ResourceLocation(MOD_ID, name);
    }
}
