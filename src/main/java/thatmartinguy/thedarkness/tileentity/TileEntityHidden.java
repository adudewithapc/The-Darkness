package thatmartinguy.thedarkness.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public class TileEntityHidden extends TileEntity
{
    private IBlockState replacedBlock;
    private TileEntity replacedEntity;

    public TileEntityHidden()
    {
        replacedBlock = Blocks.AIR.getDefaultState();
    }

    public void setBlockState(IBlockState blockState)
    {
        replacedBlock = blockState;
        this.markDirty();
    }

    public IBlockState getBlockState()
    {
        return replacedBlock;
    }

    public void setTileEntity(@Nullable TileEntity tileEntity)
    {
        replacedEntity = tileEntity;
        this.markDirty();
    }

    @Nullable
    public TileEntity getTileEntity()
    {
        return replacedEntity;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        compound.setInteger("BlockMeta", getBlockState().getBlock().getMetaFromState(getBlockState()));

        if(this.getTileEntity() != null)
        {
            compound.setTag("TileEntity", getTileEntity().serializeNBT());
        }

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        replacedBlock = Block.getStateById(compound.getInteger("BlockMeta"));

        if(compound.getTag("TileEntity") != null)
        {
            replacedEntity.deserializeNBT((NBTTagCompound) compound.getTag("TileEntity"));
        }
    }
}
