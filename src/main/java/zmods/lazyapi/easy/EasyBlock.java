package zmods.lazyapi.easy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class EasyBlock extends Block {
	private EasyItemBlock itemBlock;
	
	public EasyBlock(String name) {
		this(name, Material.ROCK);
		
	}
	
	public EasyBlock(String name, Material materialIn) {
		super(materialIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		EasyFunctions.register(this);
		itemBlock = new EasyItemBlock(this);
	}

	public static class EasyItemBlock extends ItemBlock {
		public EasyItemBlock(Block block) {
			super(block);
			this.setUnlocalizedName(block.getRegistryName().toString());
			this.setRegistryName(block.getRegistryName().toString());
			EasyFunctions.register(this);
		}
	}
}
