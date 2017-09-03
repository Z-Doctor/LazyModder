package zdoctor.lazymodder.easy.interfaces;

import net.minecraft.item.Item;

public interface IEasyRegister {
	public String getNameFromMeta(int meta);
	public String getRegistryNameForMeta(int meta);
	
	public default int getSubCount() {
		return 1;
	};
	
	public default int getRegistryMeta(Item item, int meta) {
		return meta;
	}
	
}
