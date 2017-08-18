package zdoctor.lazymodder.easy.interfaces;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;

public interface IEasyTileEntity {
	public Class<? extends TileEntity> getTileEntity();
	public Class<? extends TileEntitySpecialRenderer> getTileEntityRenderer();
	public String getTileEntityRegistryName();
	public ItemBlock getItemBlock();
}
