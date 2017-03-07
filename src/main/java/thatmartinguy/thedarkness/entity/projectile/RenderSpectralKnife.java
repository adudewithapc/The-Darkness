package thatmartinguy.thedarkness.entity.projectile;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import thatmartinguy.thedarkness.util.Reference;

public class RenderSpectralKnife extends Render<EntitySpectralKnife>
{
	private final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(Reference.MOD_ID, "textures/entity/humanmale.png");
	
	protected RenderSpectralKnife(RenderManager renderManager)
	{
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySpectralKnife entity)
	{
		return TEXTURE_LOCATION;
	}
}
