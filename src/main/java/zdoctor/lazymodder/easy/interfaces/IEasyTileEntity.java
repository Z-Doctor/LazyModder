package zdoctor.lazymodder.easy.interfaces;

import net.minecraft.tileentity.TileEntity;

public interface IEasyTileEntity<T> {
	public Class<? extends TileEntity> getTileEntity();
	public String getTileEntityRegistryName();
	public T getItemBlock();
}
