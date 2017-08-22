package zdoctor.lazymodder.easy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockWoodSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.interfaces.INoModel;
import zdoctor.lazymodder.easy.items.EasyItemSlab;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

public class EasyWoodSlab extends BlockWoodSlab implements IEasyRegister {
	protected ItemBlock itemBlock;
	protected EasyWoodenDoubleSlab blockDouble;

	public EasyWoodSlab(String name) {
		super();
		this.setRegistryName(name);
		this.setUnlocalizedName(name);

		EasyRegistry.register(this);
		blockDouble = new EasyWoodenDoubleSlab(name + "2", this);
		itemBlock = createItem();
	}

	private EasyWoodSlab(String name, boolean unused) {
		this.setRegistryName(name);
		this.setUnlocalizedName(name);

		EasyRegistry.register(this);
	}

	private ItemBlock createItem() {
		return new EasyItemSlab(this, blockDouble, BlockPlanks.EnumType.values().length);
	}

	public ItemBlock getItemBlock() {
		return itemBlock;
	}

	public EasyWoodenDoubleSlab getBlockDouble() {
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

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(this, 1, damageDropped(state));
	}

	private static class EasyWoodenDoubleSlab extends EasyWoodSlab implements INoModel {
		private Block singleSlab;

		public EasyWoodenDoubleSlab(String name, Block singSlab) {
			super(name, true);
			this.singleSlab = singSlab;
		}

		@Override
		public boolean isDouble() {
			return true;
		}
		
		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
				EntityPlayer player) {
			return new ItemStack(singleSlab, 1, singleSlab.damageDropped(state));
		}

	}

}
