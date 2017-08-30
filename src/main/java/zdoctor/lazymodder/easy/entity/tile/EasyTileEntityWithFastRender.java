package zdoctor.lazymodder.easy.entity.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class EasyTileEntityWithFastRender extends EasyTileEntity {
	@Override
	public boolean hasFastRenderer() {
		return true;
	}
}
