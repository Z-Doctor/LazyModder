package zdoctor.lazymodder.registery;

import java.util.ArrayList;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import zdoctor.lazymodder.ModMain;
import zdoctor.lazymodder.devtools.ModelCreator;
import zdoctor.lazymodder.easy.items.EasyItem;
import zdoctor.lazymodder.easy.items.IEasyItem;

public class ItemRegistry {
	private static ArrayList<Item> itemList = new ArrayList<>();
	private static ModelCreator mc;

	public static void register(Item item) {
		System.out.println("Item Added: " + item.getRegistryName());
		if (ModMain.DEV_ENV) {
			if (mc == null)
				mc = new ModelCreator();

			if (!mc.doesFileExist(mc.getItemPath(item))) {
				System.out.println("Model Error: " + item.getRegistryName());
				mc.createDefaultModel(item);
			}
		}

		itemList.add(item);
	}
	
	public static void register(ItemBlock item) {
		System.out.println("ItemBlock Added: " + item.getRegistryName());
		if (ModMain.DEV_ENV) {
			if (mc == null)
				mc = new ModelCreator();

			if (!mc.doesFileExist(mc.getItemPath(item))) {
				System.out.println("Model Error: " + item.getRegistryName());
				mc.createDefaultModel(item);
			}
		}

		itemList.add(item);
	}

	public static void registerItems(Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		itemList.forEach(item -> {
			registry.register(item);
		});
	}

	public static void registerItemModels() {
		IForgeRegistry<Item> itemReg = GameRegistry.findRegistry(Item.class);
		itemReg.getValues().forEach(item1 -> {
			if (item1 instanceof EasyItem) {
				EasyItem item = (EasyItem) item1;
				for (int i = 0; i < item.getSubCount(); i++) {
					ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(
							item.getRegistryName().getResourceDomain() + ":" + item.getNameFromMeta(i), "inventory"));
				}
			} else if (item1 instanceof IEasyItem) {
				IEasyItem item = (IEasyItem) item1;
				for (int i = 0; i < item.getSubCount(); i++) {
					ModelLoader.setCustomModelResourceLocation((Item) item, i, new ModelResourceLocation(
							item.getRegistryName().getResourceDomain() + ":" + item.getNameFromMeta(i), "inventory"));
				}
			}
		});

	}

}
