package zdoctor.lazymodder.easy.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

/**
 * 
 * @author Z_Doctor
 */
public class EasyItemSword extends ItemSword implements IEasyRegister {
	protected int subCount;

	public EasyItemSword(String name, Item.ToolMaterial material) {
		this(name, 1, material);
	}

	public EasyItemSword(String name, int subCount, Item.ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		EasyRegistry.register(this);
	}

	@Override
	public String getNameFromMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	@Override
	public String getRegistryNameForMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	@Override
	public int getSubCount() {
		return subCount;
	}

}
