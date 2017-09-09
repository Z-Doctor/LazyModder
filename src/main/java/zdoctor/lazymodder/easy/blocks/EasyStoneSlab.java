package zdoctor.lazymodder.easy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zdoctor.lazymodder.easy.common.EasyStateMap.Builder;
import zdoctor.lazymodder.easy.interfaces.ICustomStateMap;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.interfaces.INoModel;
import zdoctor.lazymodder.easy.items.EasyItemSlab;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

public class EasyStoneSlab extends BlockStoneSlab implements IEasyRegister {
	protected ItemBlock itemBlock;
	protected EasyStoneDoubleSlab blockDouble;

	public EasyStoneSlab(String name) {
		super();
		this.setRegistryName(name);
		this.setUnlocalizedName(name);

		EasyRegistry.register(this);
		blockDouble = new EasyStoneDoubleSlab(name + "2", this);
		itemBlock = createItem();
	}

	private EasyStoneSlab(String name, boolean unused) {
		this.setRegistryName(name);
		this.setUnlocalizedName(name);

		EasyRegistry.register(this);
	}

	private ItemBlock createItem() {
		return new EasyItemSlab(this, blockDouble, BlockStoneSlab.EnumType.values().length);
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

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(this, 1, damageDropped(state));
	}

	private static class EasyStoneDoubleSlab extends EasyStoneSlab implements INoModel, ICustomStateMap {
		private Block singleSlab;

		public EasyStoneDoubleSlab(String name, Block singSlab) {
			super(name, true);
			this.singleSlab = singSlab;
		}

		@Override
		public boolean isDouble() {
			return true;
		}

		@Override
		public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_,
				EnumFacing p_193383_4_) {
			return BlockFaceShape.SOLID;
		}

		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
				EntityPlayer player) {
			return new ItemStack(singleSlab, 1, singleSlab.damageDropped(state));
		}

		@Override
		public IStateMapper getStateMap() {
			return new StateMap.Builder().ignore(EasyStoneSlab.SEAMLESS).build();
		}

	}

}
