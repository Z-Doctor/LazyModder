package zdoctor.lazymodder.interfaces;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

public interface IItemModel {
	public ModelResourceLocation getModelLocation(ItemStack stack);
}
