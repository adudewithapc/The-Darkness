package thatmartinguy.thedarkness.world.gen;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import thatmartinguy.thedarkness.init.ModLootTables;

import java.util.Random;

public class WorldGenReliquaryChest implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if (world instanceof WorldServer)
        {
            WorldServer worldServer = (WorldServer) world;

            if(worldServer.provider.getDimensionType() == DimensionType.THE_END && chunkX == worldServer.getSpawnCoordinate().getX() >> 4 && chunkZ == worldServer.getSpawnCoordinate().getZ() >> 4)
            {
                BlockPos chestPosition = worldServer.getSpawnCoordinate().offset(EnumFacing.NORTH);
                world.setBlockState(chestPosition, Blocks.BLACK_SHULKER_BOX.getDefaultState(), 3);
                if (world.getTileEntity(chestPosition) instanceof TileEntityShulkerBox)
                {
                    TileEntityShulkerBox entityBox = (TileEntityShulkerBox) world.getTileEntity(chestPosition);
                    entityBox.setLootTable(ModLootTables.lootReliquary, 1);
                }
            }
        }
    }
}
