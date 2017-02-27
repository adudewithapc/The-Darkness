package thatmartinguy.thedarkness.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelLivingShadow extends ModelBase {
    public ModelRenderer LeftArm;
    public ModelRenderer LeftLeg;
    public ModelRenderer Head;
    public ModelRenderer Torso;
    public ModelRenderer RightArm;
    public ModelRenderer RightLeg;
    public ModelRenderer Head2;

    public ModelLivingShadow() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.LeftArm = new ModelRenderer(this, 56, 0);
        this.LeftArm.setRotationPoint(-5.0F, -12.0F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2);
        this.setRotationAngles(this.LeftArm, 0.0F, 0.0F, 0.10000000116728046F);
        this.LeftLeg = new ModelRenderer(this, 56, 0);
        this.LeftLeg.setRotationPoint(-2.0F, -5.0F, 0.0F);
        this.LeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -13.0F, -0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        this.Torso = new ModelRenderer(this, 32, 16);
        this.Torso.setRotationPoint(0.0F, -14.0F, -0.0F);
        this.Torso.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
        this.RightArm = new ModelRenderer(this, 56, 0);
        this.RightArm.setRotationPoint(5.0F, -12.0F, 0.0F);
        this.RightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2);
        this.RightArm.mirror = true;
        this.setRotationAngles(this.RightArm, 0.0F, 0.0F, -0.10000000116728046F);
        this.RightLeg = new ModelRenderer(this, 56, 0);
        this.RightLeg.setRotationPoint(2.0F, -5.0F, 0.0F);
        this.RightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2);
        this.RightLeg.mirror = true;
        this.Head2 = new ModelRenderer(this, 0, 16);
        this.Head2.setRotationPoint(0.0F, -13.0F, -0.0F);
        this.Head2.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.LeftArm.offsetX, this.LeftArm.offsetY, this.LeftArm.offsetZ);
        GlStateManager.translate(this.LeftArm.rotationPointX * scale, this.LeftArm.rotationPointY * scale, this.LeftArm.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.LeftArm.offsetX, -this.LeftArm.offsetY, -this.LeftArm.offsetZ);
        GlStateManager.translate(-this.LeftArm.rotationPointX * scale, -this.LeftArm.rotationPointY * scale, -this.LeftArm.rotationPointZ * scale);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.LeftArm.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.LeftLeg.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.Head.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.Torso.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.RightArm.offsetX, this.RightArm.offsetY, this.RightArm.offsetZ);
        GlStateManager.translate(this.RightArm.rotationPointX * scale, this.RightArm.rotationPointY * scale, this.RightArm.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.RightArm.offsetX, -this.RightArm.offsetY, -this.RightArm.offsetZ);
        GlStateManager.translate(-this.RightArm.rotationPointX * scale, -this.RightArm.rotationPointY * scale, -this.RightArm.rotationPointZ * scale);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.RightArm.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.RightLeg.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.Head2.render(scale);
        GlStateManager.disableBlend();
    }

    public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
