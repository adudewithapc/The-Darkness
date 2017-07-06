package thatmartinguy.thedarkness.client.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import thatmartinguy.thedarkness.entity.EntityLivingShadow;
import thatmartinguy.thedarkness.util.Reference;

import javax.annotation.Nullable;

public class RenderLivingShadow extends RenderBiped<EntityLivingShadow>
{
    private static final ResourceLocation TEXTURE_LOCATION = Reference.createResourceLocation("textures/entities/livingshadow.png");

    public RenderLivingShadow(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, new ModelBiped(1.0f), 0);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityLivingShadow entity)
    {
        return this.TEXTURE_LOCATION;
    }
}
