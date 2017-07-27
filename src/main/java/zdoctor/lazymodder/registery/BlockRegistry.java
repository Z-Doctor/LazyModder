package zdoctor.lazymodder.registery;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import zdoctor.lazymodder.ModMain;
import zdoctor.lazymodder.devtools.ModelCreator;
import zdoctor.lazymodder.easy.blocks.EasyBlock;
import zdoctor.lazymodder.easy.blocks.IEasyBlock;

public class BlockRegistry {
	private static ArrayList<Block> blockList = new ArrayList<>();
	private static ModelCreator mc;
	
	public static void register(Block block) {
		System.out.println("Block Added: " + block.getRegistryName());
		if (ModMain.DEV_ENV) {
			if (mc == null)
				mc = new ModelCreator();
			
			if (!mc.doesFileExist(mc.getBlockPath(block))) {
				mc.createDefaultModel(block);
			}
			
			if (!mc.doesFileExist(mc.getBlockStatePath(block))) 
				mc.createDefaultBlockstate(block);
		}

		blockList.add(block);
	}


	public static void registerBlocks(Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		blockList.forEach(block -> {
			registry.register(block);
		});
	}

	public static void registerBlockModels() {
		IForgeRegistry<Block> blockReg = GameRegistry.findRegistry(Block.class);
		blockReg.getValues().forEach(block1 -> {
			if (block1 instanceof EasyBlock) { // res.getResourceDomain().equalsIgnoreCase(modId))
												// {
				EasyBlock block = (EasyBlock) block1;
				for (int i = 0; i < block.getSubCount(); i++) {
					ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i,
							new ModelResourceLocation(
									block.getRegistryName().getResourceDomain() + ":" + block.getNameFromMeta(i),
									"inventory"));
				}
			} else if (block1 instanceof IEasyBlock) {
				IEasyBlock block = (IEasyBlock) block1;
				for (int i = 0; i < block.getSubCount(); i++) {
					ModelLoader.setCustomModelResourceLocation((Item) block, i, new ModelResourceLocation(
							block.getRegistryName().getResourceDomain() + ":" + block.getNameFromMeta(i), "inventory"));
				}
			}
		});

	}
}
