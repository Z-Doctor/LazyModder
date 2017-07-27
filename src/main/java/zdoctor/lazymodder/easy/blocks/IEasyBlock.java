package zdoctor.lazymodder.easy.blocks;

import net.minecraft.util.ResourceLocation;

public interface IEasyBlock {

	int getSubCount();

	ResourceLocation getRegistryName();

	String getNameFromMeta(int i);

}
