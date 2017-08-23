package zdoctor.lazymodder.easy.interfaces;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public interface IEasyRegister {
	public String getNameFromMeta(int meta);
	public String getRegistryNameForMeta(int meta);
	
	public int getSubCount();
}
