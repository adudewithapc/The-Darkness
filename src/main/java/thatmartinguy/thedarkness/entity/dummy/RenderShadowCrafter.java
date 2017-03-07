package thatmartinguy.thedarkness.entity.dummy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import thatmartinguy.thedarkness.util.Reference;

public class RenderShadowCrafter extends RenderLiving<EntityShadowCrafter>
{
	private final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(Reference.MOD_ID, "textures/entity/livingshadow.png");
	
	public RenderShadowCrafter(RenderManager rendermanagerIn)
	{
		super(rendermanagerIn, new ModelBiped(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityShadowCrafter entity)
	{
		return this.TEXTURE_LOCATION;
	}
}
