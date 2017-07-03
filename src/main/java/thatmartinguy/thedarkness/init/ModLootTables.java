package thatmartinguy.thedarkness.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import thatmartinguy.thedarkness.util.Reference;

public class ModLootTables
{
    public static ResourceLocation lootReliquary;

    public static final ResourceLocation[] LOOT_TABLES = {
            lootReliquary = Reference.createResourceLocation("reliquary_loot")
    };

    public static void register()
    {
        for(ResourceLocation lootTable : LOOT_TABLES)
        {
            LootTableList.register(lootTable);
        }
    }
}
