package zdoctor.lazymodder.easy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zdoctor.lazymodder.easy.builders.StructureBuilder.Structure;
import zdoctor.lazymodder.easy.items.EasyItemMultiBlock;

public class EasyMultiBlock extends EasyBlock {
	protected BlockStateContainer blockState;

	public EasyMultiBlock(String name, Structure structure) {
		this(name, structure, Material.ROCK);
	}

	public EasyMultiBlock(String name, Structure structure, Material material) {
		super(name, material);
		this.blockState = createTrueBlockState();
		setDefaultState(this.blockState.getBaseState().withProperty(meta, 0));
	}

	public ItemBlock createItem() {
		return new EasyItemMultiBlock(this);
	}
	
	public ItemBlock getItemBlock() {
		return itemBlock;
	}
	
	protected BlockStateContainer createTrueBlockState() {
		return new BlockStateContainer.Builder(this).add(meta).build();
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
		// TODO Auto-generated method stub
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

}
