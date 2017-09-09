package zdoctor.lazymodder.easy.interfaces;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IRenderTESR {
	@SideOnly(Side.CLIENT)
	public Class<? extends TileEntitySpecialRenderer> getRenderer();
}
