package zmods.lazyapi.core;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.IResource;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import zmods.lazyapi.easy.EasyItems;

public class DevTools {
	protected class ItemModels {

	}

	protected static void preInit(FMLPreInitializationEvent e) {

	}

	public static void init(FMLInitializationEvent e) {
		// ModelLoader.setCustomModelResourceLocation(Config.itemRing, 0, new
		// ModelResourceLocation("baubles:Ring", "inventory"));
	}

	protected static void postInit(FMLPostInitializationEvent e) {
		// System.out.println("Finding loaded mods:");
		// for (ModContainer mod : Loader.instance().getActiveModList()) {
		// System.out.println("Mod Found: " + mod.getModId());
		// }
	}

	public static void registerItemModels(String modId) {
		IForgeRegistry<Item> itemReg = GameRegistry.findRegistry(Item.class);
		itemReg.getKeys().forEach(res -> {
			if (res.getResourceDomain().equalsIgnoreCase(modId)) {
//				System.out.println(res.getResourceDomain() + ":" + res.getResourcePath());
				EasyItems item = (EasyItems) itemReg.getValue(res);
//				System.out.println("Has SubItems: " + item.getHasSubtypes());
//				System.out.println("SubCount: " + item.getSubCount());
				for (int i = 0; i < item.getSubCount(); i++) {
					System.out.println("Registered: " + res.getResourceDomain() + ":" + item.getNameFromDamage(i));
					ModelLoader.setCustomModelResourceLocation(item, i,
							new ModelResourceLocation(res.getResourceDomain() + ":" + item.getNameFromDamage(i), "inventory"));
				}
			}
		});

	}
}
