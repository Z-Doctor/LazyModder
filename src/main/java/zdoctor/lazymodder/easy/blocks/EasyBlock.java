package zdoctor.lazymodder.easy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;
import zdoctor.lazymodder.easy.items.IEasyItem;
import zdoctor.lazymodder.registery.BlockRegistry;
import zdoctor.lazymodder.registery.ItemRegistry;

public class EasyBlock extends Block {
	// public static final PropertyEnum TYPE = PropertyEnum.create("type",
	// test.class);
	//
	// public static enum test implements IStringSerializable {
	// ;
	//
	// @Override
	// public String getName() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// }

	private EasyItemBlock itemBlock;

	public EasyBlock(String name) {
		this(name, Material.ROCK);
	}

	// public EasyBlock(String name, boolean hasSubtypes) {
	// this(name, hasSubtypes, Material.ROCK);
	// }

	// public EasyBlock(String name, Material materialIn) {
	// this(name, false, materialIn);
	// }

	public EasyBlock(String name, Material materialIn) {
		super(materialIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabs.DECORATIONS);

		BlockRegistry.register(this);
		itemBlock = new EasyItemBlock(this);
	}

	public static class EasyItemBlock extends ItemBlock implements IEasyItem {
		// public EasyItemBlock(Block block, boolean hasSubtypes) {
		// this(block, hasSubtypes);
		// }

		public EasyItemBlock(Block block) {
			super(block);
			this.setUnlocalizedName(block.getRegistryName().toString());
			this.setRegistryName(block.getRegistryName().toString());
			// this.setHasSubtypes(hasSubtypes);

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
	}

	public int getSubCount() {
		return 1;
	}

	public String getNameFromMeta(int i) {
		return getRegistryName().getResourcePath();
	}
}
