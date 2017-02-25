package thatmartinguy.thedarkness.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockVoidstone extends BlockBase
{
	public BlockVoidstone(String unlocalizedName, String registryName, Material material)
	{
		super(unlocalizedName, registryName, material);
		this.setLightLevel(-1);
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(50.0F);
		this.setResistance(2000.0F);
	}
	
	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return false;
	}
}
