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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import thatmartinguy.thedarkness.command.CommandDimension;
import thatmartinguy.thedarkness.command.CommandTransforming;
import thatmartinguy.thedarkness.data.capability.PlayerHostProvider;
import thatmartinguy.thedarkness.init.ModLootTables;
import thatmartinguy.thedarkness.network.ReliquaryCraftedMessage;
import thatmartinguy.thedarkness.proxy.IProxy;
import thatmartinguy.thedarkness.util.Reference;
import thatmartinguy.thedarkness.world.gen.WorldGenReliquaryChest;

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
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

        int networkID = -1;
        network.registerMessage(ReliquaryCraftedMessage.Handler.class, ReliquaryCraftedMessage.class, networkID++, Side.CLIENT);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        PlayerHostProvider.register();

        ModLootTables.register();
        GameRegistry.registerWorldGenerator(new WorldGenReliquaryChest(), 0);
    }

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_LOCATION, serverSide = Reference.SERVER_PROXY_LOCATION)
    public static IProxy proxy;

    @Instance
    public static TheDarkness instance;

    @EventHandler
    public void s(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandDimension());
        event.registerServerCommand(new CommandTransforming());
    }
}
