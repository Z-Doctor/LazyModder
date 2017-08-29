package zdoctor.lazymodder.easy.blocks;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockRedSandstone;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.items.EasyItemBlock;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

/**
 * @author Z_Doctor
 * Vanilla Block Veriants
 * {@link BlockPlanks}
 * {@link BlockSand}
 * {@link BlockSandStone}
 * {@link BlockRedSandstone}
 * {@link BlockQuartz}
 * {@link BlockQuartz}
 *
 */
public class EasyStairs extends BlockStairs implements IEasyRegister {
	protected ItemBlock itemBlock;

	public EasyStairs(String name, IBlockState state) {
		super(state);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		setCreativeTab(CreativeTabs.DECORATIONS);

		EasyRegistry.register(this);
		itemBlock = createItem();
	}

	public ItemBlock createItem() {
		return new EasyItemBlock(this, getSubCount());
	}

	public ItemBlock getItemBlock() {
		return itemBlock;
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
		return 1;
	}

}
