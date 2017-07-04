package thatmartinguy.thedarkness;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import thatmartinguy.thedarkness.command.CommandDimension;
import thatmartinguy.thedarkness.command.CommandHost;
import thatmartinguy.thedarkness.command.CommandTransforming;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.init.ModBlocks;
import thatmartinguy.thedarkness.init.ModLootTables;
import thatmartinguy.thedarkness.network.PlayerTransformMessage;
import thatmartinguy.thedarkness.proxy.IProxy;
import thatmartinguy.thedarkness.util.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class TheDarkness
{
    public static final CreativeTabs TAB_DARKNESS = new CreativeTabs("thedarkness")
    {
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(Blocks.BEDROCK);
        }
    };

    public static SimpleNetworkWrapper network;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModBlocks.registerTileEntities();

        network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

        int networkID = -1;
        network.registerMessage(PlayerTransformMessage.Handler.class, PlayerTransformMessage.class, networkID++, Side.CLIENT);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        PlayerHostProvider.register();

        ModLootTables.register();
    }

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_LOCATION, serverSide = Reference.SERVER_PROXY_LOCATION)
    public static IProxy proxy;

    @Instance
    public static TheDarkness instance;

    @EventHandler
    public void registerDebugCommands(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandDimension());
        event.registerServerCommand(new CommandTransforming());
        event.registerServerCommand(new CommandHost());
    }
}
