package zdoctor.lazymodder.easy.items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import zdoctor.lazymodder.registery.ItemRegistry;

public class EasyBucket extends ItemBucket implements IEasyItem {

	public EasyBucket(Block containedBlockIn) {
		super(containedBlockIn);
		setUnlocalizedName("bucket" + containedBlockIn.getUnlocalizedName().substring(5));
		setRegistryName("bucket" + containedBlockIn.getUnlocalizedName().substring(5));
		ItemRegistry.register(this);
	}

	@Override
	public String getNameFromMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	@Override
	public int getSubCount() {
		return 1;
	}

	@Override
	public String getRegistryNameForMeta(int meta) {
		return getNameFromMeta(meta);
	}

}
