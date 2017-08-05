package zdoctor.lazymodder.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class EasyCreativeTab extends CreativeTabs {
	private ItemStack icon;

	public EasyCreativeTab(String label, ItemStack icon) {
		super(label);
		this.icon = icon;
	}

	public EasyCreativeTab(int index, String label, ItemStack icon) {
		super(index, label);
		this.icon = icon;
	}

	@Override
	public ItemStack getTabIconItem() {
		return icon;
	}

}
