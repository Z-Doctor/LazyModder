package zdoctor.lazymodder.common.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;

/**
 *
 */

public abstract class EasyRenderLiving<T extends EntityLiving> {

	private RenderManager renderManagerIn;
	private ModelBase mainModel;
	private float shadowSize;

	public EasyRenderLiving(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
		this.renderManagerIn = renderManagerIn;
		this.mainModel = modelBaseIn;
		this.shadowSize = shadowSizeIn;
	}
}