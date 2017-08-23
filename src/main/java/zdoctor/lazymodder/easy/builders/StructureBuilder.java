package zdoctor.lazymodder.easy.builders;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import scala.collection.parallel.ParIterableLike.Count;

public class StructureBuilder {
	private int height = 0;
	private int width = 0;
	private int length = 0;
	private List<Layer> blueprint = new ArrayList<>();
	private Map<Character, ItemStack> materials = new HashMap();

	public StructureBuilder() {
	}

	public StructureBuilder map(String... bpArray) {
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
	
	public StructureBuilder map(int repeat, String... bpArray) {
		for(int i = 0; i < repeat; i ++) {
			this.map(bpArray);
		}
		return this;
	}

	public StructureBuilder where(char key, ItemStack value) {
		materials.put(key, value);
		return this;
	}

	public StructureBuilder where(char key, Block value) {
		materials.put(key, new ItemStack(value));
		return this;
	}

	public Structure build() {
		return new Structure(width, height, length, blueprint, materials);
	}

	public static class Layer {
		private List<String> layers = new ArrayList<>();

		public void add(String string) {
			layers.add(string);
		}

		public void build(Structure structure, World world, BlockPos pos, EnumFacing facing, boolean replaceByDefault, Map<BlockPos, IBlockState> blockMap) {
			BlockPos startingPos = pos;
			for (int z = 0; z < structure.length; z++) {
				String s = layers.get(z);
				System.out.println("Building: " + s);
				pos = startingPos.offset(facing, z);
				for (int x = 0; x < structure.width; x++) {
					char c = x < s.length() ? s.charAt(x) : ' ';
					if (c == ' ') {
						if(replaceByDefault)
							world.setBlockToAir(pos);
					} else {
						ItemStack stack = structure.materials.get(c);
						Block b = Block.getBlockFromItem(stack.getItem());
						IBlockState state = b.getStateFromMeta(stack.getMetadata());
						
						// For blocks that have differnet states based on meta
						world.setBlockState(pos, state);
						blockMap.put(pos, state);
					}
					pos = pos.offset(facing.rotateY());
				}
			}
		}

	}

	public static class Structure {
		private int height = 0;
		private int width = 0;
		private int length = 0;

		private List<Layer> blueprint;
		private Map<Character, ItemStack> materials;
		
		public Structure(NBTTagCompound compound) {
			this.blueprint = new ArrayList<>();
			this.materials = new HashMap<>();
			readFromNBT(compound);
		}

		public Structure(int width, int height, int length, List<Layer> blueprint,
				Map<Character, ItemStack> materials) {
			this.width = width;
			this.height = height;
			this.length = length;

			this.blueprint = blueprint;
			this.materials = materials;
		}

		public Map<BlockPos, IBlockState> build(World world, BlockPos pos) {
			return this.build(world, pos, EnumFacing.NORTH);
		}

		public Map<BlockPos, IBlockState> build(World world, BlockPos pos, EnumFacing facing) {
			return this.build(world, pos, facing, false);
		}
		
		public Map<BlockPos, IBlockState> build(World world, BlockPos pos, EnumFacing facing, boolean replaceByDefault) {
			Map<BlockPos, IBlockState> blockMap = new HashMap<>();
			for (Layer layer : blueprint) {
				layer.build(this, world, pos, facing, replaceByDefault, blockMap);
				pos = pos.up();
			}
			return blockMap;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public int getLength() {
			return length;
		}

		public void writeToNBT(NBTTagCompound compound) {
			compound.setIntArray("Dimensions", new int[] {width, height, length});
			
			NBTTagCompound bpTag = new NBTTagCompound();
			for (int length = 0; length < this.length; length++) {
				Layer layer = blueprint.get(length);
				List<byte[]> byteList = new ArrayList<>();
				for (String s : layer.layers) {
					byteList.add(s.getBytes(StandardCharsets.UTF_16));
				}
				NBTTagCompound tag = new NBTTagCompound();
				for(int width = 0; width < this.width; width++) {
					tag.setByteArray("Layer[" + width + "]", byteList.get(width));
				}
				bpTag.setTag("Layer[" + length + "]", tag);
			}
			
			compound.setTag("Blueprint", bpTag);
			NBTTagCompound materialTag = new NBTTagCompound();
			materials.forEach((c, stack) -> {
				NBTTagCompound itemStackTag = new NBTTagCompound();
				stack.writeToNBT(itemStackTag);
				
				materialTag.setTag(Character.toString(c), itemStackTag);
			});
			
			StringBuilder materialList = new StringBuilder("");
			for(char c : this.materials.keySet()) {
				materialList.append(c);
			}
			materialTag.setString("MaterialList", materialList.toString());
			
			compound.setTag("Materials", materialTag);
		}
		
		public void readFromNBT(NBTTagCompound compound) {
			int[] dimensions = compound.getIntArray("Dimensions");
			this.width = dimensions[0];
			this.height = dimensions[1];
			this.length = dimensions[2];
			System.out.println("X: " + this.width + ";Y: " + this.height + ";Z: " + this.length);
			
			String materialList = compound.getString("MaterialList");
			NBTTagCompound materialTag = compound.getCompoundTag("Materials");
			for(int i = 0; i < materialList.length(); i++) {
				char c = materialList.charAt(i);
				ItemStack stack = new ItemStack(materialTag.getCompoundTag(Character.toString(c)));
				this.materials.put(c, stack);
				System.out.println(c + ": " + stack.getDisplayName());
			}
			
			NBTTagCompound bpTag = compound.getCompoundTag("Blueprint");
			for (int length = 0; length < this.length; length++) {
				NBTTagCompound layerTag = bpTag.getCompoundTag("Layer[" + length + "]");
				Layer layer = new Layer();
				for(int width = 0; width < this.width; width++) {
					byte[] byteArray = layerTag.getByteArray("Layer[" + width + "]");
					String s = new String(byteArray, StandardCharsets.UTF_16);
					System.out.println("Width: " + this.width + ";Length: " + this.length +";" + s);
					layer.add(s);
				}
				blueprint.add(layer);
			}
		}

	}

}
