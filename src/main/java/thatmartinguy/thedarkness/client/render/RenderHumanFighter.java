package thatmartinguy.thedarkness.client.render;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;

public class RenderHumanFighter extends RenderHuman
{
	public RenderHumanFighter(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
		this.addLayer(new LayerBipedArmor(this));
	}
}
