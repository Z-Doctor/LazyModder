package zdoctor.lazymodder.common.client;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import zdoctor.lazymodder.builtin.helpers.RenderHelper;

/**
 * This class creates a stand in for {@link TileEntitySpecialRenderer}
 * so the server can see this class. Both render functions will be called by the client
 * so just code as normal
 *
 */

public abstract class EasyTileEntitySpecialRenderer<T extends TileEntity> {
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
	};

	public void renderTileEntityFast(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage,
			float partial, BufferBuilder buffer) {
	};

	public void bindTexture(ResourceLocation texLocation) {
		RenderHelper.bindTexture(texLocation);
	}
}