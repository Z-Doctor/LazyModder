package zdoctor.lazymodder.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

//Cubik Studio 2.8.445 Beta JAVA exporter
//Designed by Z_Doctor in Cubik Studio
// As you can see I have no idea how to actually make a model :(
public class ModelMushromoid extends ModelBase {

	// fields
	ModelRenderer e1;
	ModelRenderer e2;
	ModelRenderer e3;
	ModelRenderer e4;
	ModelRenderer e5;
	ModelRenderer e6;
	ModelRenderer e7;
	ModelRenderer e8;

	public ModelMushromoid() {
		textureWidth = 16;
		textureHeight = 16;

		e1 = new ModelRenderer(this, 0, 15);
		e1.addBox(5F, 3F, 5F, 6, 9, 6);
		e1.setRotationPoint(5F, 3F, 5F);
		e1.setTextureSize(textureWidth, textureHeight);
		e1.mirror = false;
		setRotation(e1, 0F, 0F, 0F);
		e2 = new ModelRenderer(this, 0, 44);
		e2.addBox(0F, 12F, 0F, 16, 4, 16);
		e2.setRotationPoint(0F, 12F, 0F);
		e2.setTextureSize(textureWidth, textureHeight);
		e2.mirror = false;
		setRotation(e2, 0F, 0F, 0F);
		e3 = new ModelRenderer(this, 0, 7);
		e3.addBox(5F, 0F, 7F, 2, 3, 2);
		e3.setRotationPoint(5F, 0F, 7F);
		e3.setTextureSize(textureWidth, textureHeight);
		e3.mirror = false;
		setRotation(e3, 0F, 0F, 0F);
		e4 = new ModelRenderer(this, 0, 7);
		e4.addBox(9F, 0F, 7F, 2, 3, 2);
		e4.setRotationPoint(9F, 0F, 7F);
		e4.setTextureSize(textureWidth, textureHeight);
		e4.mirror = false;
		setRotation(e4, 0F, 0F, 0F);
		e5 = new ModelRenderer(this, 0, 30);
		e5.addBox(0F, 10F, 2F, 1, 2, 12);
		e5.setRotationPoint(0F, 10F, 2F);
		e5.setTextureSize(textureWidth, textureHeight);
		e5.mirror = false;
		setRotation(e5, 0F, 0F, 0F);
		e6 = new ModelRenderer(this, 0, 30);
		e6.addBox(15F, 10F, 2F, 1, 2, 12);
		e6.setRotationPoint(15F, 10F, 2F);
		e6.setTextureSize(textureWidth, textureHeight);
		e6.mirror = false;
		setRotation(e6, 0F, 0F, 0F);
		e7 = new ModelRenderer(this, 0, 12);
		e7.addBox(2F, 10F, 0F, 12, 2, 1);
		e7.setRotationPoint(2F, 10F, 0F);
		e7.setTextureSize(textureWidth, textureHeight);
		e7.mirror = false;
		setRotation(e7, 0F, 0F, 0F);
		e8 = new ModelRenderer(this, 0, 12);
		e8.addBox(2F, 10F, 15F, 12, 2, 1);
		e8.setRotationPoint(2F, 10F, 15F);
		e8.setTextureSize(textureWidth, textureHeight);
		e8.mirror = false;
		setRotation(e8, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		e1.render(f5);
		e2.render(f5);
		e3.render(f5);
		e4.render(f5);
		e5.render(f5);
		e6.render(f5);
		e7.render(f5);
		e8.render(f5);
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