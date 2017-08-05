package zdoctor.lazymodder.easy.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public interface IEasyItem {
	public String getNameFromMeta(int meta);

	public int getSubCount();

	public ResourceLocation getRegistryName();

	public boolean getHasSubtypes();

	public void getSubItems(CreativeTabs search, NonNullList<ItemStack> subList);
}
