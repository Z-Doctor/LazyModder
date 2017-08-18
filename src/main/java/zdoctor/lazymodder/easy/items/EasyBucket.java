package zdoctor.lazymodder.easy.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

public class EasyBucket extends ItemBucket implements IEasyRegister {

	public EasyBucket(Block containedBlockIn) {
		super(containedBlockIn);
		setUnlocalizedName("bucket" + containedBlockIn.getUnlocalizedName().substring(5));
		setRegistryName("bucket" + containedBlockIn.getUnlocalizedName().substring(5));
		EasyRegistry.register(this);
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
