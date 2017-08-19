package zdoctor.lazymodder.easy.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import zdoctor.lazymodder.easy.blocks.EasyBlock;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

public class EasyItemDoorTileEntity extends ItemDoor implements IEasyRegister {
	private int subCount;
	protected Block block;

	public EasyItemDoorTileEntity(Block block) {
		this(block, 1);
	}

	public EasyItemDoorTileEntity(Block block, int subCount) {
		super(block);
		this.setUnlocalizedName(block.getRegistryName().toString());
		this.setRegistryName(block.getRegistryName());
		this.subCount = subCount;
		this.setHasSubtypes(subCount > 1);
		this.block = block;
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
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getRegistryNameForMeta(int meta) {
		return getNameFromMeta(meta);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		if (this.block instanceof EasyBlock)
			return "tile." + ((EasyBlock) this.block).getNameFromMeta(itemStack.getMetadata());
		return "tile." + this.getNameFromMeta(itemStack.getMetadata());
	}
}