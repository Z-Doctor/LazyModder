package zdoctor.lazymodder.easy.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.property.IUnlistedProperty;
import zdoctor.lazymodder.easy.items.EasyItem;
import zdoctor.lazymodder.easy.items.IEasyItem;
import zdoctor.lazymodder.registery.BlockRegistry;
import zdoctor.lazymodder.registery.ItemRegistry;

public class EasyBlock extends Block implements IEasyBlock {
	public List<IEasyProperty> propertiesList;
	
	private EasyItemBlock itemBlock;
	private int subBlockCount;

	public EasyBlock(String name) {
		this(name, Material.ROCK);
	}

	public EasyBlock(String name, int subBlockCount) {
		this(name, subBlockCount, Material.ROCK);
	}

	public EasyBlock(String name, Material materialIn) {
		this(name, 1, materialIn);
	}

	/**
	 * if the block has multiple states, set the default state in constructor
	 * 
	 * @param name
	 * @param subBlockCount
	 * @param materialIn
	 */
	public EasyBlock(String name, int subBlockCount, Material materialIn) {
		super(materialIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		// this.setCreativeTab(CreativeTabs.DECORATIONS);

		System.out.println("Block Name: " + name);
		System.out.println("Block reg: " + getRegistryName().toString());

		this.subBlockCount = subBlockCount;
//		if(subBlockCount > 1) {
//			META.setMax(subBlockCount);
//			setDefaultState(getBlockState().getBaseState().withProperty(META, 0));
//		}
		
		System.out.println("prop");

		BlockRegistry.register(this);
		itemBlock = new EasyItemBlock(this);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		propertiesList = new ArrayList<>();
		registerProperties(propertiesList);
		return new BlockStateContainer(this, this.propertiesList.toArray(new IProperty[0]));
	} 

	/**
	 * Add custom properties to this to be added to the block.
	 * For a list check {@linkplain net.minecraft.block.properties}
	 * @param properties
	 */
	public void registerProperties(List<IEasyProperty> properties) {
		
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = -1;
		for (IEasyProperty prop : propertiesList) {
			meta = prop.getMetaFromState(state);
			if(meta > -1)
				break;
		}
		if(meta > -1)
			return meta;
		return super.getMetaFromState(state);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = getDefaultState();
		for (IEasyProperty prop : propertiesList) {
			Comparable value = prop.getValueFromMeta(meta);
			if(value != null)
				state = state.withProperty(prop, value);
		}
		return state;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		IBlockState newState = getDefaultState();
		for (IEasyProperty prop : propertiesList) {
			newState = newState.withProperty(prop, prop.getValue(worldIn, pos, newState, placer, stack));
		}
		worldIn.setBlockState(pos, newState);
	}

	@Override
	public String getNameFromMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	@Override
	public int getSubCount() {
		return this.subBlockCount;
	}

	@Override
	public boolean getHasSubtypes() {
		return this.subBlockCount > 1;
	}

	@Override
	public void getSubItems(CreativeTabs search, NonNullList<ItemStack> subList) {
		this.getSubBlocks(search, subList);
	}

	@Override
	public void getSubBlocks(CreativeTabs tabIn, NonNullList<ItemStack> items) {
		if (tabIn == CreativeTabs.SEARCH || tabIn == getCreativeTabToDisplayOn())
			for (int i = 0; i < this.getSubCount(); i++)
				items.add(new ItemStack(this, 1, i));
	}

	@Override
	public String getRegistryNameForMeta(int meta) {
		return getRegistryName().getResourcePath();
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

	public String getUnlocalizedName(ItemStack stack) {
		return this.getUnlocalizedName();
	}
	
	public static class EasyItemBlock extends ItemBlock implements IEasyItem {
		public EasyItemBlock(EasyBlock block) {
			super(block);
			this.setUnlocalizedName(block.getRegistryName().getResourcePath());
			this.setRegistryName(block.getRegistryName().getResourcePath());

			System.out.println("Item Name:" + block.getRegistryName().getResourcePath());
			System.out.println("Item reg: " + getRegistryName().toString());

			this.setHasSubtypes(block.getSubCount() > 1);

			setCreativeTab(CreativeTabs.DECORATIONS);

			this.addPropertyOverride(new ResourceLocation("meta"), EasyItem.META_GETTER);

			ItemRegistry.register(this);
		}

		@Override
		public String getNameFromMeta(int meta) {
			return getRegistryName().getResourcePath();
		}

		@Override
		public int getSubCount() {
			return 1;
		}

		@Override
		public String getRegistryNameForMeta(int meta) {
			return getNameFromMeta(meta);
		}
		
		@Override
		public String getUnlocalizedName(ItemStack stack) {
			if(Block.getBlockFromItem(stack.getItem()) instanceof EasyBlock)
				return ((EasyBlock)Block.getBlockFromItem(stack.getItem())).getUnlocalizedName(stack);
			return super.getUnlocalizedName(stack);
		}
	}

}
