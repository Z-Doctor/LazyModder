package zdoctor.lazymodder.easy.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import zdoctor.lazymodder.easy.blocks.EasyBlock;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

public class EasyItemSlab extends ItemSlab implements IEasyRegister {
	private int subCount;

	public EasyItemSlab(BlockSlab singleSlab, BlockSlab doubleSlab, int subCount) {
		super(singleSlab, singleSlab, doubleSlab);
		this.setUnlocalizedName(singleSlab.getRegistryName().toString());
		this.setRegistryName(singleSlab.getRegistryName());
		this.subCount = subCount;
		this.addPropertyOverride(new ResourceLocation("meta"), ItemProperties.META_GETTER);
		
		EasyRegistry.register(this);
	}
	
	@Override
	public String getNameFromMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	@Override
	public int getSubCount() {
		return subCount;
	}

	@Override
	public String getRegistryNameForMeta(int meta) {
		return getNameFromMeta(meta);
	}

}
