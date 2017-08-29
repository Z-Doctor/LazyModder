package zdoctor.lazymodder.easy.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPurpurSlab;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.interfaces.INoModel;
import zdoctor.lazymodder.easy.items.EasyItemSlab;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

public class EasySlab extends BlockSlab implements IEasyRegister {
	public static final PropertyEnum<BlockPlanks.EnumType> WOOD_VARIANT = PropertyEnum.<BlockPlanks.EnumType>create(
			"variant", BlockPlanks.EnumType.class);
	public static final PropertyEnum<BlockStoneSlab.EnumType> STONE_VARIANT = PropertyEnum.<BlockStoneSlab.EnumType>create(
			"variant", BlockStoneSlab.EnumType.class);
	public static final PropertyEnum<BlockPurpurSlab.Variant> PURPUR_VARIANT = PropertyEnum.<BlockPurpurSlab.Variant>create(
			"variant", BlockPurpurSlab.Variant.class);
	public static final PropertyEnum<EasySlab.DefaultVariant> DEFAULT_VARIANT = PropertyEnum.<EasySlab.DefaultVariant>create(
			"variant", EasySlab.DefaultVariant.class);

	protected ItemBlock itemBlock;
	protected EasyStoneDoubleSlab blockDouble;

	public EasySlab(String name) {
		this(name, Material.WOOD);
	}

	public EasySlab(String name, Material material) {
		super(material);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

		IBlockState iblockstate = this.blockState.getBaseState();

		if (!this.isDouble()) {
			iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
			
			blockDouble = new EasyStoneDoubleSlab(name + "2", this);
			itemBlock = createItem();
			itemBlock.setCreativeTab(getCreativeTabToDisplayOn());
		}

		this.setDefaultState(iblockstate.withProperty(DEFAULT_VARIANT, DefaultVariant.DEFAULT));

		EasyRegistry.register(this);
	}

	private ItemBlock createItem() {
		return new EasyItemSlab(this, blockDouble, DefaultVariant.values().length);
	}

	public ItemBlock getItemBlock() {
		return itemBlock;
	}

	public EasyStoneDoubleSlab getBlockDouble() {
		return blockDouble;
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

	// Slab Overrides
	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState().withProperty(DEFAULT_VARIANT, DefaultVariant.DEFAULT);

		if (!this.isDouble()) {
			iblockstate = iblockstate.withProperty(HALF,
					(meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
		}

		return iblockstate;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;

		if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
			i |= 8;
		}

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return this.isDouble() ? new BlockStateContainer(this, new IProperty[] { DEFAULT_VARIANT })
				: new BlockStateContainer(this, new IProperty[] { HALF, DEFAULT_VARIANT });
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return itemBlock;
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(itemBlock);
	}

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public String getUnlocalizedName(int meta) {
		return getNameFromMeta(meta);
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return DEFAULT_VARIANT;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return DefaultVariant.DEFAULT;
	}

	private static class EasyStoneDoubleSlab extends EasySlab implements INoModel {
		private Block singleSlab;

		public EasyStoneDoubleSlab(String name, Block singSlab) {
			super(name, singSlab.getDefaultState().getMaterial());
			this.singleSlab = singSlab;
		}

		@Override
		public boolean isDouble() {
			return true;
		}

		// @Override
		// public ItemStack getPickBlock(IBlockState state, RayTraceResult
		// target, World world, BlockPos pos,
		// EntityPlayer player) {
		// return new ItemStack(singleSlab, 1, singleSlab.damageDropped(state));
		// }

	}

	public static enum DefaultVariant implements IStringSerializable {
		DEFAULT;

		public String getName() {
			return "default";
		}
	}

}
