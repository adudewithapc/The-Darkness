package thatmartinguy.thedarkness.loot;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.LootTableManager;
import thatmartinguy.thedarkness.util.Reference;

public class ModLootTableList
{
	public static final ResourceLocation LIVING_SHADOW_LOOT = register("entities/living_shadow_loot");
	
	private static ResourceLocation register(String id)
	{
		return LootTableList.register(new ResourceLocation(Reference.MOD_ID, id));
	}
}
