package zdoctor.lazymodder.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMushromoid extends ModelBase {
	// fields
	ModelRenderer Right_Leg;
	ModelRenderer Left_Leg;
	ModelRenderer Torso;
	ModelRenderer Head;
	ModelRenderer Top;

	public ModelMushromoid()
		  {
		    textureWidth = 64;
		    textureHeight = 32;
		    
		      Right_Leg = new ModelRenderer(this, 8, 0);
		      Right_Leg.addBox(-1F, -1F, -1F, 2, 3, 2);
		      Right_Leg.setRotationPoint(-2F, 22F, 0F);
		      Right_Leg.setTextureSize(64, 32);
		      Right_Leg.mirror = true;
		      setRotation(Right_Leg, 0F, 0F, 0F);
		      Left_Leg = new ModelRenderer(this, 8, 0);
		      Left_Leg.addBox(-1F, -1F, -1F, 2, 3, 2);
		      Left_Leg.setRotationPoint(2F, 22F, 0F);
		      Left_Leg.setTextureSize(64, 32);
		      Left_Leg.mirror = true;
		      setRotation(Left_Leg, 0F, 0F, 0F);
		      Torso = new ModelRenderer(this, 0, 0);
		      Torso.addBox(-1F, 0F, -1F, 2, 5, 2);
		      Torso.setRotationPoint(0F, 17F, 0F);
		      Torso.setTextureSize(64, 32);
		      Torso.mirror = true;
		      setRotation(Torso, 0F, 0F, 0F);
		      Head = new ModelRenderer(this, 0, 0);
		      Head.addBox(-1F, -3F, -1F, 2, 3, 2);
		      Head.setRotationPoint(0F, 17F, 0F);
		      Head.setTextureSize(64, 32);
		      Head.mirror = true;
		      setRotation(Head, 0F, 0F, 0F);
		      Top = new ModelRenderer(this, 0, 7);
		      Top.addBox(-4.5F, -2F, -4.5F, 9, 2, 9);
		      Top.setRotationPoint(0F, 14F, 0F);
		      Top.setTextureSize(64, 32);
		      Top.mirror = true;
		      setRotation(Top, 0F, 0F, 0F);
		  }

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Right_Leg.render(f5);
		Left_Leg.render(f5);
		Torso.render(f5);
		Head.render(f5);
		Top.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}