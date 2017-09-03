package zdoctor.lazymodder.easy.world.gen;

import java.util.Random;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.IWorldGenerator;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

public class EasySmallVeinOreGen extends EasyOreGen {
	public EasySmallVeinOreGen(Block block, int blockCount, IWorldGenerator generator) {
		this(block.getDefaultState(), blockCount, generator);
	}

	public EasySmallVeinOreGen(IBlockState state, int blockCount, IWorldGenerator generator) {
		super(state, blockCount, Predicates.alwaysFalse());
		this.customWorldGen = generator;
		EasyRegistry.register(this);
	}

	public EasySmallVeinOreGen(Block block, int blockCount, Predicate<IBlockState>... genPredicate) {
		this(block.getDefaultState(), blockCount, genPredicate);
	}

	public EasySmallVeinOreGen(IBlockState state, int blockCount, Predicate<IBlockState>... genPredicate) {
		super(state, blockCount,
				genPredicate.length == 0 ? STONE : genPredicate.length > 1 ? combine(genPredicate) : genPredicate[0]);
	}
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		return super.generate(worldIn, rand, position);
	}
}
