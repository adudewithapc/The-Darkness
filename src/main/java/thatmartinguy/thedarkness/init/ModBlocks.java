package thatmartinguy.thedarkness.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thatmartinguy.thedarkness.block.*;

@EventBusSubscriber
public class ModBlocks
{
    public static BlockHidden blockHidden;
    public static final Block[] BLOCKS = {
        blockHidden = new BlockHidden("blockHidden", Material.AIR)
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
}
