package zdoctor.lazymodder.easy.interfaces;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Living Entities should implement this class to add their renders
 *
 */
public interface IEasyLivingRender {
	@SideOnly(Side.CLIENT)
	public Class<? extends RenderLiving> getEntityRenderer();
}
