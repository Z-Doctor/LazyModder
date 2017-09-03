package zdoctor.lazymodder.easy.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import zdoctor.lazymodder.easy.interfaces.IEasyWorldGenerator;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

public class EasyOreGen extends WorldGenMinable implements IEasyWorldGenerator {
	public static final Predicate<IBlockState> ALL = Predicates.alwaysTrue();
	public static final Predicate<IBlockState> DIRT = BlockMatcher.forBlock(Blocks.DIRT);
	public static final Predicate<IBlockState> STONE = BlockMatcher.forBlock(Blocks.STONE);
	public static final Predicate<IBlockState> GRAVEL = BlockMatcher.forBlock(Blocks.GRAVEL);
	public static final Predicate<IBlockState> SAND = BlockMatcher.forBlock(Blocks.SAND);
	public static final Predicate<IBlockState> SANDSTONE = BlockMatcher.forBlock(Blocks.SANDSTONE);
	public static final Predicate<IBlockState> NETHERRACK = BlockMatcher.forBlock(Blocks.NETHERRACK);
	public static final Predicate<IBlockState> SOULSAND = BlockMatcher.forBlock(Blocks.SOUL_SAND);

	protected final List<DimensionType> genInWorlds = new ArrayList<>();
	protected int numberOfBlocks;
	protected IBlockState oreState;
	protected Vec2f xOffset = new Vec2f(-3, 3);
	protected Vec2f zOffset = new Vec2f(-3, 3);
	protected Vec2f yRange = new Vec2f(-3, 3);
	protected Predicate<IBlockState> predicate;
	protected IWorldGenerator customWorldGen;
	private int yMin = 1; //The same as iron
	private int yMax = 63;
	private int genWieght = -1;

	public EasyOreGen(Block block, int blockCount, IWorldGenerator generator) {
		this(block.getDefaultState(), blockCount, generator);
	}

	public EasyOreGen(IBlockState state, int blockCount, IWorldGenerator generator) {
		super(state, blockCount, Predicates.alwaysFalse());
		this.customWorldGen = generator;
		EasyRegistry.register(this);
	}
	
	public EasyOreGen(Block block, int blockCount, Predicate<IBlockState>... genPredicate) {
		this(block.getDefaultState(), blockCount, genPredicate);
	}

	public EasyOreGen(IBlockState state, int blockCount, Predicate<IBlockState>... genPredicate) {
		this(state, blockCount,
				genPredicate.length == 0 ? STONE : genPredicate.length > 1 ? combine(genPredicate) : genPredicate[0]);
	}
	
	public EasyOreGen(IBlockState state, int blockCount, Predicate<IBlockState> genPredicate) {
		super(state, blockCount, genPredicate);
		this.numberOfBlocks = blockCount;
		this.oreState = state;
		this.predicate = genPredicate;
		EasyRegistry.register(this);
	}
	
	public int getNumberOfBlocks() {
		return numberOfBlocks;
	}
	
	public EasyOreGen genInWorld(DimensionType... dimensions) {
		genInWorlds.addAll(Arrays.asList(dimensions));
		return this;
	}
	

	public int getRandomY(Random rand) {
		return rand.nextInt(getMaxY()-getMinY()) + getMinY();
	}
	
	public EasyOreGen setYValues(int min, int max) {
		yMin = Math.min(min, max);
		yMin = yMin > 0 ? yMin : 1;
		yMax = Math.max(min, max);
		yMax = yMax < 256 ? yMax : 255;
		return this;
	}
	
	public int getMinY() {
		return yMin;
	}

	public int getMaxY() {
		return yMax;
	}
	
	public void setGenWieght(int genWieght) {
		this.genWieght = genWieght;
	}

	protected IBlockState getOreState() {
		return oreState;
	}
	
	public EasyOreGen setCustomWorldGen(IWorldGenerator customWorldGen) {
		this.customWorldGen = customWorldGen;
		return this;
	}

	public IWorldGenerator getCustomWorldGenerator() {
		return customWorldGen;
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (getCustomWorldGenerator() != null) {
			getCustomWorldGenerator().generate(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		} else {
			if (genInWorlds.size() > 0) {
				if(genInWorlds.contains(world.provider.getDimensionType())) {
					BlockPos position = new BlockPos(chunkX * 16  + random.nextInt(16), getRandomY(world.rand), chunkZ * 16  + random.nextInt(16));
					System.out.println("Gen: " + position);
					generate(world, random, position);
				}
			} else {
				if (world.provider.getDimensionType() == DimensionType.OVERWORLD) {
					BlockPos position = new BlockPos(chunkX * 16  + random.nextInt(16), getRandomY(world.rand), chunkZ * 16 + random.nextInt(16));
					System.out.println("Gen: " + position);
					generate(world, random, position);
				}
			}
		}
	}
	
	@Override
	public int getGenWeight() {
		return genWieght == -1 ? IEasyWorldGenerator.super.getGenWeight() : genWieght;
	}
	
	public static Predicate<IBlockState> createPredicate(Block block) {
		return BlockMatcher.forBlock(block);
	}
	
	public static Predicate<IBlockState> createPredicate(IBlockState state) {
		return new Predicate<IBlockState>() {

			@Override
			public boolean apply(IBlockState input) {
				return input == state;
			}
		};
	}

	public static Predicate<IBlockState> combine(Predicate<IBlockState>[] genPredicate) {
		return new Predicate<IBlockState>() {

			@Override
			public boolean apply(IBlockState input) {
				for (int i = 0; i < genPredicate.length; i++) {
					if (genPredicate[i].apply(input))
						return true;
				}
				return false;
			}
		};
	}

}
