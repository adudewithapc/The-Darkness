package thatmartinguy.thedarkness.entity.mob;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import thatmartinguy.thedarkness.util.Reference;

public class RenderHumanCivilian extends RenderHuman
{
	
	public RenderHumanCivilian(RenderManager rendermanagerIn)
	{
		super(rendermanagerIn, true);
		this.setTextureLocation("textures/entity/humanfemale.png");
	}
}
