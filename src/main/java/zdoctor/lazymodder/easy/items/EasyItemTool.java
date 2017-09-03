package zdoctor.lazymodder.easy.items;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.NonNullList;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

/**
 * 
 * @author Z_Doctor
 */
public class EasyItemTool extends ItemTool implements IEasyRegister {
	protected int subCount;

	public EasyItemTool(String toolName, int subCount, float attackDamageIn, float attackSpeedIn, Item.ToolMaterial materialIn,
			Set<Block> effectiveBlocksIn) {
		super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
		setUnlocalizedName(toolName);
		setRegistryName(toolName);
		
		this.subCount = subCount;
		EasyRegistry.register(this);
	}

	public EasyItemTool(String toolName, Item.ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
		this(toolName, 1, 0.0F, 0.0F, materialIn, effectiveBlocksIn);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (isInCreativeTab(tab))
			for (int i = 0; i < this.getSubCount(); i++)
				subItems.add(new ItemStack(this, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return "item." + this.getNameFromMeta(itemStack.getMetadata()).toLowerCase();
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
