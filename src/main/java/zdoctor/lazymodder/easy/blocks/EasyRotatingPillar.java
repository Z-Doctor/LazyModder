package zdoctor.lazymodder.easy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EasyRotatingPillar extends EasyRotatingBlock {
	public static final PropertyEnum<EnumPillarType> TYPE = PropertyEnum.<EnumPillarType>create("type",
			EnumPillarType.class);

	public EasyRotatingPillar(String name, int subCount, boolean invert, Material material) {
		super(name, subCount, invert, material);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, TYPE });
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = getDefaultState();
		state = state.withProperty(FACING, EnumFacing.getHorizontal(meta & 3));
		state = state.withProperty(TYPE, EnumPillarType.valueOf(meta >> 2));
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(TYPE).getValue() << 2) + state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if(worldIn.getBlockState(pos.up()).getBlock() != this && worldIn.getBlockState(pos.down()).getBlock() != this)
			state = state.withProperty(TYPE, EnumPillarType.CENTER);
		else if(worldIn.getBlockState(pos.up()).getBlock() != this)
			state = state.withProperty(TYPE, EnumPillarType.TOP);
		else if(worldIn.getBlockState(pos.down()).getBlock() != this)
			state = state.withProperty(TYPE, EnumPillarType.BOTTOM);
		else {
			BlockPos tempPos = pos;
			BlockPos tempTopPos = null;
			do {
				tempPos = tempPos.up();
				IBlockState tempState = worldIn.getBlockState(tempPos);
				if(tempState.getBlock() == this) {
					if(worldIn.getBlockState(tempPos.up()).getBlock() != this) {
						tempTopPos = tempPos.down();
					}
				}
			} while(tempTopPos == null);
			tempPos = pos;
			BlockPos tempBottomPos= null;
			do {
				tempPos = tempPos.down();
				IBlockState tempState = worldIn.getBlockState(tempPos);
				if(tempState.getBlock() == this) {
					if(worldIn.getBlockState(tempPos.down()).getBlock() != this) {
						tempBottomPos = tempPos;
					}
				}
			} while(tempBottomPos == null);
			
			BlockPos centerPos = new BlockPos(pos.getX(), Math.ceil((tempTopPos.getY() + tempBottomPos.getY()) / 2f), pos.getZ());
			if(centerPos.equals(pos))
				state = state.withProperty(TYPE, EnumPillarType.CENTER);
			else
				state = state.withProperty(TYPE, EnumPillarType.MID);
		}
		return state;
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		state = getActualState(state, worldIn, pos);
		worldIn.setBlockState(pos, state, 3);
		if(!pos.up().equals(fromPos) && worldIn.getBlockState(pos.up()).getBlock() == this) {
			worldIn.getBlockState(pos.up()).getBlock().neighborChanged(state, worldIn, pos.up(), this, pos);
		}
		if(!pos.down().equals(fromPos) && worldIn.getBlockState(pos.down()).getBlock() == this) {
			worldIn.getBlockState(pos.down()).getBlock().neighborChanged(state, worldIn, pos.down(), this, pos);
			((World)worldIn).updateObservingBlocksAt(pos.down(), this);
		}
	}
	
	public static enum EnumPillarType implements IStringSerializable {
		TOP("top", 0), MID("mid", 1), BOTTOM("bottom", 2), CENTER("center", 3);

		private final String name;
		private int value;

		private EnumPillarType(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public static EnumPillarType valueOf(int value) {
			for (int i = 0; i < values().length; i++) {
				if (values()[i].getValue() == value)
					return values()[i];
			}
			return null;
		}

		public String toString() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}

		public int getValue() {
			return value;
		}
	}

}
