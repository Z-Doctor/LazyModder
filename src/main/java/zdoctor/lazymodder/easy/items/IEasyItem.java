package zdoctor.lazymodder.easy.items;

import net.minecraft.util.ResourceLocation;

public interface IEasyItem {
	public String getNameFromMeta(int meta);

	public int getSubCount();

	public ResourceLocation getRegistryName();
}
