package thatmartinguy.thedarkness.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thatmartinguy.thedarkness.tileentity.TileEntityHidden;

import javax.annotation.Nullable;

public class BlockHidden extends BlockBase
{
    private final AxisAlignedBB EMPTY_AABB = new AxisAlignedBB(0, 0, 0, 0, 0, 0);
    public BlockHidden(String name, Material materialIn)
    {
        super(name, materialIn);
        this.setCreativeTab(null);
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return EMPTY_AABB;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return EMPTY_AABB;
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos)
    {
        return EMPTY_AABB;
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityHidden();
    }

    public IBlockState createHiddenBlock(World world, BlockPos pos, IBlockState blockState, @Nullable TileEntity tileEntity)
    {
        if (world.getTileEntity(pos) instanceof TileEntityHidden)
        {
            TileEntityHidden entityHidden = (TileEntityHidden) world.getTileEntity(pos);
            entityHidden.setBlockState(blockState);
            entityHidden.setTileEntity(tileEntity);
        }
        return this.getDefaultState();
    }

    public void resetHiddenBlock(World world, BlockPos pos)
    {
        if (world.getTileEntity(pos) instanceof TileEntityHidden)
        {
            TileEntityHidden entityHidden = (TileEntityHidden) world.getTileEntity(pos);
            IBlockState state = entityHidden.getBlockState();
            TileEntity tileEntity = entityHidden.getTileEntity();
            world.removeTileEntity(pos);
            if(tileEntity != null)
                world.setTileEntity(pos, tileEntity);
            world.setBlockState(pos, state);
        }
    }
}
