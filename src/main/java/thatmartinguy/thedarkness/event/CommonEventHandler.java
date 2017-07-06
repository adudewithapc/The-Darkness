package thatmartinguy.thedarkness.event;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import thatmartinguy.thedarkness.init.ModLootTables;

import java.util.Random;

@EventBusSubscriber
public class CommonEventHandler
{
    public static boolean shouldShrineExist = true;
    @SubscribeEvent
    public static void createFirstShrine(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        World world = event.player.world;

        if(shouldShrineExist && event.toDim == 1)
        {
            Random random = new Random();

            EnumFacing randomDirection = EnumFacing.getHorizontal(random.nextInt(4));
            BlockPos boxPos = ((WorldServer)world).getSpawnCoordinate().offset(EnumFacing.DOWN).offset(randomDirection, 2);
            world.setBlockState(boxPos, Blocks.PURPLE_SHULKER_BOX.getDefaultState());
            if(world.getTileEntity(boxPos) instanceof TileEntityShulkerBox)
            {
                TileEntityShulkerBox entityBox = (TileEntityShulkerBox) world.getTileEntity(boxPos);
                entityBox.setLootTable(ModLootTables.lootReliquary, 1);
            }
            shouldShrineExist = false;
        }
    }
}
