package thatmartinguy.thedarkness.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import thatmartinguy.thedarkness.TheDarkness;

public class BlockBase extends Block
{
    public BlockBase(String name, Material materialIn)
    {
        super(materialIn);
        this.setRegistryName(name);
        this.setUnlocalizedName();
        this.setCreativeTab(TheDarkness.TAB_DARKNESS);
    }

    public Block setUnlocalizedName()
    {
        return super.setUnlocalizedName(getRegistryName().toString());
    }
}
