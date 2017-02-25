package thatmartinguy.thedarkness.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import thatmartinguy.thedarkness.TheDarkness;
import thatmartinguy.thedarkness.network.ReliquaryMessage;
import thatmartinguy.thedarkness.reference.Reference;

public class ReliquaryWorldData extends WorldSavedData
{
	private boolean isReliquaryCrafted;
	private static final String IDENTIFIER = Reference.MOD_ID + "reliquary";
	private ReliquaryMessage message;
	
	public ReliquaryWorldData()
	{
		super(IDENTIFIER);
		message = new ReliquaryMessage();
	}
	
	public ReliquaryWorldData(String name)
	{
		super(name);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		isReliquaryCrafted = compound.getBoolean("isReliquaryCrafted");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setBoolean("isReliquaryCrafted", isReliquaryCrafted);
		return compound;
	}
	
	public void onReliquaryCrafted()
	{
		if(!isReliquaryCrafted)
		{
			this.isReliquaryCrafted = true;
			this.markDirty();
		}
	}
	
	public void setReliquaryCrafted(boolean isReliquaryCrafted)
	{
		this.isReliquaryCrafted = isReliquaryCrafted;
		TheDarkness.NETWORK.sendToServer(new ReliquaryMessage(this.isReliquaryCrafted));
		this.markDirty();
	}
	
	public boolean isReliquaryCrafted()
	{
		return isReliquaryCrafted;
	}
	
	public static ReliquaryWorldData get(World world, boolean isGlobal)
	{
		MapStorage storage = isGlobal ? world.getMapStorage() : world.getPerWorldStorage();
		ReliquaryWorldData instance = (ReliquaryWorldData)storage.getOrLoadData(ReliquaryWorldData.class, IDENTIFIER);
			
		if(instance == null)
		{
			instance = new ReliquaryWorldData();
			instance.setDirty(true);
			storage.setData(IDENTIFIER, instance);
		}
		return instance;
	}
}
