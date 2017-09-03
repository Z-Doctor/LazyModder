package zdoctor.lazymodder.easy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import zdoctor.lazymodder.easy.blocks.properties.PropertyVariant;

/**
 * Creates a block with 4 variants that also rotates.
 * Each class can only have 4 since forge only alows 16 meta values
 *
 */
public class EasyRotatingBlockWithVariants extends EasyRotatingBlock {
	public static final String[] VARIANTSLIST = { "variant1", "variant2", "variant3", "variant4"};
	public static final PropertyVariant VARIANTS = PropertyVariant.create("variant", VARIANTSLIST);
	
	public EasyRotatingBlockWithVariants(String name) {
		this(name, Material.ROCK);
	}
	
	public EasyRotatingBlockWithVariants(String name, Material material) {
		this(name, material, true);
	}
	
	public EasyRotatingBlockWithVariants(String name, boolean invert) {
		this(name, Material.ROCK, invert);
	}

	public EasyRotatingBlockWithVariants(String name, int subBlockCount, boolean invert) {
		this(name, subBlockCount, invert, Material.ROCK);
	}

	public EasyRotatingBlockWithVariants(String name, Material materialIn, boolean invert) {
		this(name, 1, invert, materialIn);
	}

	/**
	 * Creates a block that can be placed like a pumpkin (facing a direction).
	 * @param name - Name of block
	 * @param subCount - the amount of subtypes
	 * @param invert
	 * @param materialIn
	 */
	public EasyRotatingBlockWithVariants(String name, int subCount, boolean invert, Material materialIn) {
		super(name, subCount, invert, materialIn);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, VARIANTS});
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = getDefaultState();
		state = state.withProperty(FACING, EnumFacing.getHorizontal(meta & 3));
		state = state.withProperty(VARIANTS, VARIANTS.getNameFromValue(meta >> 2));
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return (VARIANTS.getValue(state.getValue(VARIANTS)) << 2) + state.getValue(FACING).getHorizontalIndex();
	}

}
