package thatmartinguy.thedarkness.entity.dummy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import thatmartinguy.thedarkness.util.Reference;

public class RenderLivingShadowSlave extends RenderLiving<EntityLivingShadowSlave>
{
	private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(Reference.MOD_ID, "textures/entity/livingshadow.png");
	
	public RenderLivingShadowSlave(RenderManager rendermanagerIn)
	{
		super(rendermanagerIn, new ModelBiped(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLivingShadowSlave entity)
	{
		return TEXTURE_LOCATION;
	}
}
