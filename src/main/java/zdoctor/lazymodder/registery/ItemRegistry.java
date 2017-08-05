package zdoctor.lazymodder.registery;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;
import zdoctor.lazymodder.ModMain;
import zdoctor.lazymodder.devtools.ModelCreator;
import zdoctor.lazymodder.easy.blocks.EasyBlock.EasyItemBlock;
import zdoctor.lazymodder.easy.items.IEasyItem;

public class ItemRegistry {
	private static ArrayList<Item> itemList = new ArrayList<>();
	private static ModelCreator mc;

	public static void register(Item item) {
		System.out.println("Item Added: " + item.getRegistryName());
		if (ModMain.DEV_ENV) {
			if (mc == null)
				mc = new ModelCreator();
			if (item.getHasSubtypes()) {
				NonNullList<ItemStack> subList = NonNullList.create();
				item.getSubItems(CreativeTabs.SEARCH, subList);
				System.out.println("Found SubTypes: " + subList.size());
				for (ItemStack itemStack : subList) {
					if (!mc.doesFileExist(mc.getItemPath(itemStack))) {
						System.out.println("Model Error: " + itemStack.getUnlocalizedName().substring(5));
						mc.createDefaultModel(itemStack);
					}
				}
			} else if (!mc.doesFileExist(mc.getItemPath(item))) {
				System.out.println("Model Error: " + item.getRegistryName());
				mc.createDefaultModel((Item) item);
			}
		}

		itemList.add(item);
	}

	public static void register(EasyItemBlock item) {
		System.out.println("ItemBlock Added: " + item.getRegistryName());
		if (ModMain.DEV_ENV) {
			if (item instanceof IEasyItem) {
				if (mc == null)
					mc = new ModelCreator();

				if (!mc.doesFileExist(mc.getItemPath(item))) {
					System.out.println("Model Error: " + item.getRegistryName());
					mc.createDefaultModel(item);
				}
			}
		}

		itemList.add(item);
	}

	public static void registerItems(Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		System.out.println("Registering ITems");
		itemList.forEach(item -> {
			registry.register(item);
		});
	}
}
