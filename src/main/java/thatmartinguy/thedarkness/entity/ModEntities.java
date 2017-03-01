package thatmartinguy.thedarkness.entity;

import com.google.common.collect.Iterables;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.entity.monster.EntityLivingShadow;
import thatmartinguy.thedarkness.util.Reference;

public class ModEntities
{
	public static void registerEntities()
	{
		registerEntity(EntityLivingShadow.class, "livingShadow", 80, 3, false, 0x000000, 0x000000);
	}
	
	public static void addSpawns()
	{
		addSpawn(EntityLivingShadow.class, "livingShadow", 100, 1, 3, EnumCreatureType.MONSTER, getAllBiomes());
	}
	
	private static Biome[] getBiomes(BiomeDictionary.Type type)
	{
		return BiomeDictionary.getBiomesForType(type);
	}
	
	private static Biome[] getAllBiomes()
	{
		Iterable<Biome> biomes = ForgeRegistries.BIOMES;
		return Iterables.toArray(biomes, Biome.class);
	}
	
	private static int entityID = -1;
	
	private static void registerEntity(Class<? extends Entity> entity, String name, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
	{
		ResourceLocation registryName = new ResourceLocation(Reference.MOD_ID, name);
		EntityRegistry.registerModEntity(entity, registryName.toString(), entityID++, TheDarkness.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
	}
	
	private static void registerEntity(Class<? extends Entity> entity, String name, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary)
	{
		ResourceLocation registryName = new ResourceLocation(Reference.MOD_ID, name);
		EntityRegistry.registerModEntity(entity, registryName.toString(), entityID++, TheDarkness.instance, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
	}
	
	private static void addSpawn(Class<? extends EntityLiving> entity, String name, int probability, int minSpawn, int maxSpawn, EnumCreatureType creatureType, Biome... biomes)
	{
		EntityRegistry.addSpawn(entity, probability, minSpawn, maxSpawn, creatureType, biomes);
	}
}