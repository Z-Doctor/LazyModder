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
import zdoctor.lazymodder.interfaces.INoModel;

public class BlockRegistry {
	private static ArrayList<Block> blockList = new ArrayList<>();
	private static ModelCreator mc;

	public static void register(Block block) {
		System.out.println("Block Added: " + block.getRegistryName());
		if (ModMain.DEV_ENV && block instanceof IEasyBlock && !(block instanceof INoModel)) {
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

}
