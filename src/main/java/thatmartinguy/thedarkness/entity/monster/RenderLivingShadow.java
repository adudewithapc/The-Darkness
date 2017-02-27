package thatmartinguy.thedarkness.entity.monster;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import thatmartinguy.thedarkness.util.Reference;

public class RenderLivingShadow extends RenderBiped<EntityLivingShadow>
{
	private static final ResourceLocation LIVING_SHADOW_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/entity/livingshadow.png");
	
	public RenderLivingShadow(RenderManager rendermanagerIn)
	{
		super(rendermanagerIn, new ModelBiped(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLivingShadow entity)
	{
		return LIVING_SHADOW_TEXTURE;
	}
}
