package zdoctor.lazymodder.easy.blocks;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.items.EasyItemBlock;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

public class EasyRotatingBlock extends BlockHorizontal implements IEasyRegister {
	protected ItemBlock itemBlock;
	protected int subCount;
	protected boolean invert;

	public EasyRotatingBlock(String name) {
		this(name, Material.ROCK);
	}
	
	public EasyRotatingBlock(String name, Material material) {
		this(name, material, true);
	}
	
	public EasyRotatingBlock(String name, boolean invert) {
		this(name, Material.ROCK, invert);
	}

	public EasyRotatingBlock(String name, int subBlockCount, boolean invert) {
		this(name, subBlockCount, invert, Material.ROCK);
	}

	public EasyRotatingBlock(String name, Material materialIn, boolean invert) {
		this(name, 1, invert, materialIn);
	}

	/**
	 * Creates a block that can be placed like a pumpkin (facing a direction).
	 * @param name - Name of block
	 * @param subCount - the amount of subtypes
	 * @param invert
	 * @param materialIn
	 */
	public EasyRotatingBlock(String name, int subCount, boolean invert, Material materialIn) {
		super(materialIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.subCount = subCount;
		this.invert = invert;

		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

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
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		if(!invert)
			return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
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

	@Override
	public void getSubBlocks(CreativeTabs tabIn, NonNullList<ItemStack> items) {
		if (tabIn == CreativeTabs.SEARCH || tabIn == getCreativeTabToDisplayOn())
			for (int i = 0; i < this.getSubCount(); i++)
				items.add(new ItemStack(this, 1, i));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(state));
	}

}
