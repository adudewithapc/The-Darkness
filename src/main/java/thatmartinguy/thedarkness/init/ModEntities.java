package thatmartinguy.thedarkness.init;

import com.google.common.collect.Iterables;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.entity.EntityLivingShadow;
import thatmartinguy.thedarkness.util.Reference;

@EventBusSubscriber
public class ModEntities
{
    private static int entityID = -1;

    public static void registerEntities()
    {
        registerEntity("livingShadow", EntityLivingShadow.class, 80, 3, false);
    }

    public static void addSpawns()
    {
    }

    private static Biome[] getAllBiomes()
    {
        Iterable<Biome> biomes = ForgeRegistries.BIOMES;
        return Iterables.toArray(biomes, Biome.class);
    }

    private static void registerEntity(String name, Class<? extends Entity> entityClass, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
    {
        ResourceLocation registryName = Reference.createResourceLocation(name);
        EntityRegistry.registerModEntity(registryName, entityClass, name, entityID++, TheDarkness.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    private static void addSpawn(Class<? extends EntityLiving> entityClass, int weightedProb, int min, int max, EnumCreatureType creatureType, Biome... biomes)
    {
        EntityRegistry.addSpawn(entityClass, weightedProb, min, max, creatureType, biomes);
    }
}
