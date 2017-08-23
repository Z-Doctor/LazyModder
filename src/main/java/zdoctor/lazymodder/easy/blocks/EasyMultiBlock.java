package zdoctor.lazymodder.easy.blocks;

import java.util.Collection;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zdoctor.lazymodder.easy.blocks.properties.PropertyVariant;
import zdoctor.lazymodder.easy.builders.MultiBlockBuilder.MultiBlock;
import zdoctor.lazymodder.easy.items.EasyItemBlock;

public class EasyMultiBlock extends EasyBlock {
	public final PropertyVariant VARIANTS;
	protected BlockStateContainer blockState;
	protected MultiBlock multiBlock;
	protected List<String> blockVarirants;

	public EasyMultiBlock(String name, MultiBlock multiBlock) {
		this(name, multiBlock, Material.ROCK);
	}

	public EasyMultiBlock(String name, MultiBlock multiBlock, Material material) {
		super(name, material);
		this.multiBlock = multiBlock;
		
		List<String> blockVarirants = multiBlock.getVariants();
		if(!blockVarirants.contains("default"))
			blockVarirants.add("default");
		this.blockVarirants = blockVarirants;
		VARIANTS = PropertyVariant.create("variant", blockVarirants);
		
		this.blockState = createTrueBlockState();
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANTS, "default"));
	}
	
	@Override
	public BlockStateContainer getBlockState() {
		return this.blockState;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		String varirant = state.getValue(VARIANTS);
		return blockVarirants.indexOf(varirant);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANTS, blockVarirants.get(meta));
	}

	public ItemBlock createItem() {
		return new EasyItemBlock(this);
	}

	public ItemBlock getItemBlock() {
		return itemBlock;
	}

	protected BlockStateContainer createTrueBlockState() {
		return new BlockStateContainer.Builder(this).add(VARIANTS).build();
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
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		System.out.println("Block Placed");
		System.out.println("has space: " + multiBlock.hasRoom(this, worldIn, pos, placer.getHorizontalFacing()));
		multiBlock.build(this, worldIn, pos, placer.getHorizontalFacing());
	}
		
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
//		if(state.getBlock() == this) {
//			worldIn.setBlockToAir(pos);
//		}
	}
	
//	@Override
//	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
//		// TODO Auto-generated method stub
//		super.onBlockHarvested(worldIn, pos, state, player);
//	}

}
