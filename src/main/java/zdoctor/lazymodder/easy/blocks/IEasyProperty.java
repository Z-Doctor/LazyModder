package zdoctor.lazymodder.easy.blocks;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IEasyProperty <T extends Comparable<T>> extends IProperty<T> {

	Comparable getValue(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack);

	default int getMetaFromState(IBlockState state){
		return -1;
	}

	Comparable getValueFromMeta(int meta);;
	
}
