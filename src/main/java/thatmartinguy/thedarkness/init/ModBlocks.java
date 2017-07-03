package thatmartinguy.thedarkness.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thatmartinguy.thedarkness.block.*;
import thatmartinguy.thedarkness.tileentity.TileEntityHidden;

@EventBusSubscriber
public class ModBlocks
{
    public static BlockHidden blockHidden;
    public static BlockBase blockSeeThrough;

    public static final Block[] BLOCKS = {
        blockHidden = new BlockHidden("blockHidden", Material.AIR),
        blockSeeThrough = new BlockSeeThrough("blockSeeThrough", Material.STRUCTURE_VOID)
    };

    public static final Class<? extends TileEntity>[] TILE_ENTITIES = new Class[]{
            TileEntityHidden.class
    };

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BLOCKS);
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event)
    {
        for(Block block : BLOCKS)
        {
            event.getRegistry().register(new ItemBlock(block).setUnlocalizedName(block.getUnlocalizedName()).setRegistryName(block.getRegistryName()));
        }
    }

    public static void registerTileEntities()
    {
        for(Class<? extends TileEntity> tileEntity : TILE_ENTITIES)
        {
            GameRegistry.registerTileEntity(tileEntity, "tileEntityHidden");
        }
    }
}
