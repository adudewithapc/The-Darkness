package thatmartinguy.thedarkness.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import thatmartinguy.thedarkness.entity.mob.EntityHuman;
import thatmartinguy.thedarkness.entity.model.ModelHuman;
import thatmartinguy.thedarkness.util.Reference;

public class RenderHuman extends RenderLiving<EntityHuman>
{
	protected ResourceLocation textureLocation = new ResourceLocation(Reference.MOD_ID, "textures/entity/humanmale.png");
	
	public RenderHuman(RenderManager renderManagerIn)
	{
		this(renderManagerIn, false);
	}
	
	public RenderHuman(RenderManager renderManagerIn, boolean useSmallArms)
	{
		super(renderManagerIn, new ModelHuman(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHuman entity)
	{
		return this.textureLocation;
	}
	
	protected void setTextureLocation(String location)
	{
		this.textureLocation = new ResourceLocation(Reference.MOD_ID, location);
	}
}
