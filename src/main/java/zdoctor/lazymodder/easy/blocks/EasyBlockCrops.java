package zdoctor.lazymodder.easy.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import scala.reflect.internal.Flags;
import zdoctor.lazymodder.builtin.helpers.FlagHelper;
import zdoctor.lazymodder.easy.items.EasyItemSeeds;

public class EasyBlockCrops extends EasyBlockBush implements IGrowable {
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
	public static final AxisAlignedBB[] CROPS_AABB = new AxisAlignedBB[] {
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };

	protected ItemSeeds cropSeed;
	protected Block farmLand;
	protected FlagHelper flag = new FlagHelper(ConditionFlags.MIN_LIGHT_LEVEL_8 | ConditionFlags.SEE_SKY_FLAG | ConditionFlags.REQUIRE_LIGHT_9_TO_GROW);

	public EasyBlockCrops(String name, Item crop) {
		this(name, crop, Blocks.FARMLAND, Material.PLANTS);
	}

	public EasyBlockCrops(String name, Item crop, Material material) {
		this(name, crop, Blocks.FARMLAND, material);
	}
	
	public EasyBlockCrops(String name, Item crop, Block farmLand) {
		this(name, crop, farmLand, Material.PLANTS);
	}

	public EasyBlockCrops(String name, Item crop, Block farmLand, Material material) {
		super(name, crop, material);
		this.farmLand = farmLand;
		this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
		this.setTickRandomly(true);
		cropSeed = createSeed();
	}
	
	public EasyBlockCrops setVanilaGrowthConditionsFlags(int...flags) {
		flag.reset();
		for (int i : flags) {
			flag.or(i);
		}
		return this;
	}

	protected ItemSeeds createSeed() {
		return new EasyItemSeeds(this, getFarmLand());
	}

	public Block getFarmLand() {
		return farmLand;
	}

	public int getMaxAge() {
		assert (AGE.getAllowedValues().size() - 1 == 7);
		return AGE.getAllowedValues().size() - 1;
	}

	protected int getAge(IBlockState state) {
		return ((Integer) state.getValue(this.getAgeProperty())).intValue();
	}

	protected IProperty getAgeProperty() {
		return AGE;
	}

	public boolean isMaxAge(IBlockState state) {
		return ((Integer) state.getValue(AGE)).intValue() >= this.getMaxAge();
	}

	public IBlockState withAge(int age) {
		return this.getDefaultState().withProperty(this.getAgeProperty(), Integer.valueOf(age));
	}

	public int getBonemealAgeIncrease(World worldIn) {
		return MathHelper.getInt(worldIn.rand, 2, 5);
	}

	public Item getSeed() {
		return cropSeed;
	}
	
	public boolean isFullGrown(IBlockState state) {
		return getMaxAge() == getAge(state);
	}

	protected float getGrowthChance(Block blockIn, World worldIn, BlockPos pos) {
		float f = 1.0F;
		BlockPos blockpos = pos.down();

		for (int i = -1; i <= 1; ++i) {
			for (int j = -1; j <= 1; ++j) {
				float f1 = 0.0F;
				IBlockState iblockstate = worldIn.getBlockState(blockpos.add(i, 0, j));

				if (iblockstate.getBlock().canSustainPlant(iblockstate, worldIn, blockpos.add(i, 0, j),
						net.minecraft.util.EnumFacing.UP, (net.minecraftforge.common.IPlantable) blockIn)) {
					f1 = 1.0F;

					if (iblockstate.getBlock().isFertile(worldIn, blockpos.add(i, 0, j))) {
						f1 = 3.0F;
					}
				}

				if (i != 0 || j != 0) {
					f1 /= 4.0F;
				}

				f += f1;
			}
		}

		BlockPos blockpos1 = pos.north();
		BlockPos blockpos2 = pos.south();
		BlockPos blockpos3 = pos.west();
		BlockPos blockpos4 = pos.east();
		boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock()
				|| blockIn == worldIn.getBlockState(blockpos4).getBlock();
		boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock()
				|| blockIn == worldIn.getBlockState(blockpos2).getBlock();

		if (flag && flag1) {
			f /= 2.0F;
		} else {
			boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock()
					|| blockIn == worldIn.getBlockState(blockpos4.north()).getBlock()
					|| blockIn == worldIn.getBlockState(blockpos4.south()).getBlock()
					|| blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();

			if (flag2) {
				f /= 2.0F;
			}
		}

		return f;
	}
	
	public AxisAlignedBB getCollisiongForAge(int age) {
		return CROPS_AABB[age];
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { AGE });
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.withAge(meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return this.getAge(state);
	}

	@Override
	public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, net.minecraft.world.IBlockAccess world,
			BlockPos pos, IBlockState state, int fortune) {
		super.getDrops(drops, world, pos, state, 0);
		int age = getAge(state);
		Random rand = world instanceof World ? ((World) world).rand : new Random();

		if (age >= getMaxAge()) {
			int k = 3 + fortune;

			for (int i = 0; i < 3 + fortune; ++i) {
				if (rand.nextInt(2 * getMaxAge()) <= age) {
					drops.add(new ItemStack(this.getSeed(), 1, 0));
				}
			}
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return this.isMaxAge(state) ? this.getCrop() : this.getSeed();
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this.getSeed());
	}

	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		IBlockState soil = worldIn.getBlockState(pos.down());
		return (isFullGrown(state) || canGrow(worldIn, pos, state, worldIn.isRemote)) && (flag.and(ConditionFlags.MIN_LIGHT_LEVEL_8) &&  worldIn.getLight(pos) >= 8) || (flag.and(ConditionFlags.SEE_SKY_FLAG) && worldIn.canSeeSky(pos)) && farmLand == soil.getBlock();
	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock() == getFarmLand();
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return !this.isMaxAge(state);
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int age = ((Integer) state.getValue(this.getAgeProperty())).intValue();
		return getCollisiongForAge(age);
	}
	
	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
		int j = this.getMaxAge();

		if (i > j) {
			i = j;
		}

		worldIn.setBlockState(pos, this.withAge(i), 2);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);

		if (worldIn.getLightFromNeighbors(pos.up()) >= 9 || !flag.and(ConditionFlags.REQUIRE_LIGHT_9_TO_GROW)) {
			int i = this.getAge(state);

			if (i < this.getMaxAge()) {
				float f = getGrowthChance(this, worldIn, pos);

				if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state,
						rand.nextInt((int) (25.0F / f) + 1) == 0)) {
					worldIn.setBlockState(pos, this.withAge(i + 1), 2);
					net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state,
							worldIn.getBlockState(pos));
				}
			}
		}
	}

	@Override
	public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos) {
		return net.minecraftforge.common.EnumPlantType.Crop;
	}
	
	public static class ConditionFlags {
		public static int SEE_SKY_FLAG = Integer.parseInt("1", 2);
		public static int MIN_LIGHT_LEVEL_8 = Integer.parseInt("10", 2);
		public static int REQUIRE_LIGHT_9_TO_GROW = Integer.parseInt("100", 2);
		
		public static final int ALL = SEE_SKY_FLAG | MIN_LIGHT_LEVEL_8 | REQUIRE_LIGHT_9_TO_GROW;
	}

}
