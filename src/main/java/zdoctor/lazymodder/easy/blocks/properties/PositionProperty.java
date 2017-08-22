package zdoctor.lazymodder.easy.blocks.properties;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

public class PositionProperty extends PropertyInteger implements IEasyProperty<BlockPos> {
	protected BlockPos origin;

	protected PositionProperty(String name, BlockPos origin, int min, int max) {
		super(name, min, max);
		this.origin = origin;
		EasyRegistry
	}
	
	public static PositionProperty create(String name, BlockPos origin, int min, int max) {
		return new PositionProperty(name, origin, min, max);
	}
	
	@Override
	public Comparable getValue(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		return pos.subtract(origin);
	}
	@Override
	public Comparable getValueFromMeta(int meta) {
		return meta;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(this);
	}
}
