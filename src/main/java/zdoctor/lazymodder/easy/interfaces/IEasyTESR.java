package zdoctor.lazymodder.easy.interfaces;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IEasyTESR extends IEasyTileEntity {
	@SideOnly(Side.CLIENT)
	public Class getTileEntityRenderer();
}
