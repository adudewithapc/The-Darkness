package thatmartinguy.thedarkness.client.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ModSoundEvent
{
	public static final SoundEvent ITEM_RELIQUARY_DRUNK = new SoundEvent(setResourceLocation("drunk_reliquary"));
	
	private static ResourceLocation setResourceLocation(String resourcePathIn)
	{
		return new ResourceLocation("thedarkness", resourcePathIn);
	}
}
