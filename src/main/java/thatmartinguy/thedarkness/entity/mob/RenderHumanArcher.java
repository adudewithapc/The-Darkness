package thatmartinguy.thedarkness.entity.mob;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;

public class RenderHumanArcher extends RenderHuman
{
	public RenderHumanArcher(RenderManager renderManagerIn)
	{
		super(renderManagerIn, true);
		this.setTextureLocation("textures/entity/humanfemale.png");
		this.addLayer(new LayerBipedArmor(this));
	}
}
