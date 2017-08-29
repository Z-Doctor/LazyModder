package zdoctor.lazymodder.easy.builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zdoctor.lazymodder.easy.blocks.EasyMultiBlock;

public class MultiBlockBuilder {
	private int height = 0;
	private int width = 0;
	private int length = 0;
	private List<Layer> blueprint = new ArrayList<>();
	private Map<Character, String> variants = new HashMap<>();
	private StringBuilder sequence = new StringBuilder("");

	public MultiBlockBuilder map(String... bpArray) {
		height++;
		Layer layer = new Layer();
		for (String string : bpArray) {
			width = string.length() > width ? string.length() : width;
			layer.add(string);
		}
		length = bpArray.length > length ? bpArray.length : length;
		blueprint.add(layer);
		return this;
	}

	public MultiBlockBuilder map(int repeat, String... bpArray) {
		for (int i = 0; i < repeat; i++)
			this.map(bpArray);
		return this;
	}

	public MultiBlockBuilder where(char key, String variant) {
		sequence.append(key);
		variants.put(key, variant);
		return this;
	}

	public MultiBlock build() {
		return new MultiBlock(width, height, length, blueprint, variants, sequence.toString());
	}

	public static class Layer {
		private List<String> layers = new ArrayList<>();

		public void add(String string) {
			layers.add(string);
		}

		public void build(MultiBlock multiBlock, EasyMultiBlock block, World world, BlockPos pos, EnumFacing facing, boolean replaceByDefault,
				Map<BlockPos, IBlockState> blockMap) {
			BlockPos startingPos = pos;
			for (int z = 0; z < multiBlock.length; z++) {
				String s = layers.get(z);
				pos = startingPos.offset(facing, z);
				for (int x = 0; x < multiBlock.width; x++) {
					char c = x < s.length() ? s.charAt(x) : ' ';
					if (c == ' ') {
						if (replaceByDefault)
							world.setBlockToAir(pos);
					} else {
						IBlockState state = block.getDefaultState().withProperty(block.VARIANTS, multiBlock.variants.get(c));
						world.setBlockState(pos, state);
						blockMap.put(pos, state);
					}
					pos = pos.offset(facing.rotateY());
				}
			}
		}

	}

	public static class MultiBlock {
		private int height = 0;
		private int width = 0;
		private int length = 0;

		private List<Layer> blueprint;
		private Map<Character, String> variants;
		private String sequence;

		public MultiBlock(int width, int height, int length, List<Layer> blueprint, Map<Character, String> variants, String sequence) {
			this.width = width;
			this.height = height;
			this.length = length;
			this.blueprint = blueprint;
			this.variants = variants;
			this.sequence = sequence;
		}

		public List<String> getVariants() {
			List<String> variantList = new ArrayList<>();
			for(int i = 0; i < sequence.length(); i++) {
				char c = sequence.charAt(i);
				variantList.add(variants.get(c));
			}
			return variantList;
		}

		public boolean hasRoom(EasyMultiBlock block, World world, BlockPos pos, EnumFacing facing) {
			BlockPos origin = pos;
			for (int y = 0; y < height; y++) {
				pos = origin.add(0, y, 0);
				for (int z = 0; z < length; z++) {
					for (int x = 0; x < width; x++) {
						IBlockState state = world.getBlockState(pos);
						if (state.getBlock() != block && !state.getBlock().isReplaceable(world, pos))
							return false;
						pos.offset(facing.rotateY());
					}
					pos.offset(facing);
				}
			}

			return true;
		}

		public Map<BlockPos, IBlockState> build(EasyMultiBlock block, World world, BlockPos pos, EnumFacing facing) {
			return this.build(block, world, pos, facing, false);
		}

		public Map<BlockPos, IBlockState> build(EasyMultiBlock block, World world, BlockPos pos, EnumFacing facing,
				boolean replaceByDefault) {
			if (hasRoom(block, world, pos, facing)) {
				Map<BlockPos, IBlockState> blockMap = new HashMap<>();
				for (Layer layer : blueprint) {
					layer.build(this, block, world, pos, facing, replaceByDefault, blockMap);
					pos = pos.up();
				}
				return blockMap;
			}

			return null;
		}

	}

}
