package thatmartinguy.thedarkness;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import thatmartinguy.thedarkness.achievement.ModAchievements;
import thatmartinguy.thedarkness.block.ModBlocks;
import thatmartinguy.thedarkness.client.gui.ModGuiHandler;
import thatmartinguy.thedarkness.command.CommandCheckHost;
import thatmartinguy.thedarkness.command.CommandMakeHost;
import thatmartinguy.thedarkness.command.CommandRemoveHost;
import thatmartinguy.thedarkness.command.CommandResetReliquaryCraftedState;
import thatmartinguy.thedarkness.crafting.ModCrafting;
import thatmartinguy.thedarkness.crafting.ModShapedRecipe;
import thatmartinguy.thedarkness.data.ModWorldData;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.entity.ModEntities;
import thatmartinguy.thedarkness.event.AchievementEventHandler;
import thatmartinguy.thedarkness.event.CapabilityEventHandler;
import thatmartinguy.thedarkness.event.CommonEventHandler;
import thatmartinguy.thedarkness.item.ModItems;
import thatmartinguy.thedarkness.network.DroppedByHostMessage;
import thatmartinguy.thedarkness.network.PlayerHostMessage;
import thatmartinguy.thedarkness.network.ReliquaryMessage;
import thatmartinguy.thedarkness.potion.ModPotionEffects;
import thatmartinguy.thedarkness.proxy.IProxy;
import thatmartinguy.thedarkness.util.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)

public class TheDarkness
{
	public static CreativeTabs tabDarkness = new TabDarkness("tabDarkness");
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_LOCATION, serverSide = Reference.SERVER_PROXY_LOCATION)
	public static IProxy proxy;
	
	@Instance
	public static TheDarkness instance;
	
	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
	
	public static DamageSource equipShadowArmor;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		ModItems.init();
		ModPotionEffects.init();
		ModBlocks.init();
		ModEntities.registerEntities();
		
		equipShadowArmor = new DamageSource("equiparmor");
		
		proxy.preInit();
		
		int id = -1;
		NETWORK.registerMessage(ReliquaryMessage.Handler.class, ReliquaryMessage.class, id++, Side.CLIENT);
		NETWORK.registerMessage(PlayerHostMessage.Handler.class, PlayerHostMessage.class, id++, Side.CLIENT);
		NETWORK.registerMessage(DroppedByHostMessage.Handler.class, DroppedByHostMessage.class, id++, Side.CLIENT);
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		ModCrafting.init();
		ModShapedRecipe.init();
		ModEntities.addSpawns();
		ModAchievements.init();
		
		PlayerHostProvider.register();
		
		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
		MinecraftForge.EVENT_BUS.register(new CapabilityEventHandler());
		MinecraftForge.EVENT_BUS.register(new AchievementEventHandler());
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new ModGuiHandler());
		
		proxy.init();
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
	}
	
	@EventHandler
	public static void serverStartingEvent(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandResetReliquaryCraftedState());
		event.registerServerCommand(new CommandCheckHost());
		event.registerServerCommand(new CommandRemoveHost());
		event.registerServerCommand(new CommandMakeHost());
	}
	
	@EventHandler
	public static void serverStoppedEvent(FMLServerStoppedEvent event)
	{
		ModWorldData.clearInstance();
	}
}
