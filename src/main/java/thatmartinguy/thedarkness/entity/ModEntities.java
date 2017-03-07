package thatmartinguy.thedarkness.entity;

import java.util.Set;

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
import thatmartinguy.thedarkness.entity.dummy.EntityLivingShadowSlave;
import thatmartinguy.thedarkness.entity.dummy.EntityShadowCrafter;
import thatmartinguy.thedarkness.entity.mob.EntityHumanArcher;
import thatmartinguy.thedarkness.entity.mob.EntityHumanCivilian;
import thatmartinguy.thedarkness.entity.mob.EntityHumanFighter;
import thatmartinguy.thedarkness.entity.mob.EntityLivingShadow;
import thatmartinguy.thedarkness.entity.projectile.EntitySpectralKnife;
import thatmartinguy.thedarkness.util.Reference;

public class ModEntities
{
	private static int entityID = -1;
	public static void registerEntities()
	{
		registerEntity(EntityLivingShadow.class, "livingShadow", entityID++, 80, 3, false, 0x000000, 0x000000);
		registerEntity(EntityHumanCivilian.class, "humanciv", entityID++, 80, 3, false);
		registerEntity(EntityHumanFighter.class, "humanfighter", entityID++, 80, 3, false);
		registerEntity(EntityHumanArcher.class, "humanarcher", entityID++, 80, 3, false);
		
		registerEntity(EntityLivingShadowSlave.class, "livingshadowslave", entityID++, 80, 3, false);
		registerEntity(EntityShadowCrafter.class, "shadowcrafter", entityID++, 80, 3, false);
		
		registerEntity(EntitySpectralKnife.class, "spectralknife", entityID++, 80, 3, true);
	}
	
	public static void addSpawns()
	{
		addSpawn(EntityLivingShadow.class, "livingShadow", 100, 1, 3, EnumCreatureType.MONSTER, getAllBiomes());
	}
	
	private static Set<Biome> getBiomes(BiomeDictionary.Type type)
	{
		return BiomeDictionary.getBiomes(type);
	}
	
	private static Biome[] getAllBiomes()
	{
		Iterable<Biome> biomes = ForgeRegistries.BIOMES;
		return Iterables.toArray(biomes, Biome.class);
	}
	
	private static void registerEntity(Class<? extends Entity> entity, String name, int id, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
	{
		ResourceLocation registryName = new ResourceLocation(Reference.MOD_ID, name);
		EntityRegistry.registerModEntity(registryName, entity, registryName.toString(), id, TheDarkness.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
	}
	
	private static void registerEntity(Class<? extends Entity> entity, String name, int id, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary)
	{
		ResourceLocation registryName = new ResourceLocation(Reference.MOD_ID, name);
		EntityRegistry.registerModEntity(registryName, entity, registryName.toString(), id, TheDarkness.instance, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
	}
	
	private static void addSpawn(Class<? extends EntityLiving> entity, String name, int probability, int minSpawn, int maxSpawn, EnumCreatureType creatureType, Biome... biomes)
	{
		EntityRegistry.addSpawn(entity, probability, minSpawn, maxSpawn, creatureType, biomes);
	}
	
	private static void registerEgg(String name, int eggPrimary, int eggSecondary)
	{
		ResourceLocation registryName = new ResourceLocation(Reference.MOD_ID, name);
		EntityRegistry.registerEgg(registryName, eggPrimary, eggSecondary);
	}
}