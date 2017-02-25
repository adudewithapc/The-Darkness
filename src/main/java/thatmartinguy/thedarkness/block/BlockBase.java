package thatmartinguy.thedarkness.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import thatmartinguy.thedarkness.TheDarkness;

public class BlockBase extends Block
{
	public BlockBase(String unlocalizedName, String registryName, Material material)
	{
		this(material, MapColor.BLACK);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.setCreativeTab(TheDarkness.tabDarkness);
		this.setSoundType(SoundType.STONE);
	}
	
	public BlockBase(Material materialIn, MapColor mapColor)
	{
		super(materialIn, mapColor);
	}
}
