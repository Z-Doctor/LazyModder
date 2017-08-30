package zdoctor.lazymodder.easy.entity.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class EasyTileEntity extends TileEntity {
	
	/**
	 * So tile entities save there states correctly with custom tags
	 */
	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}
}
