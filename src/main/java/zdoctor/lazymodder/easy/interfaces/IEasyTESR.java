package zdoctor.lazymodder.easy.interfaces;

import zdoctor.lazymodder.common.client.EasyTileEntitySpecialRenderer;

public interface IEasyTESR extends IEasyTileEntity {
	public Class<? extends EasyTileEntitySpecialRenderer> getTileEntityRenderer();
}
