package zdoctor.lazymodder.easy.entity.tile;

public abstract class EasyTileEntityWithFastRender extends EasyTileEntityWithRender {
	@Override
	public boolean hasFastRenderer() {
		return true;
	}
}
