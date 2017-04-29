package zdoctor.lazymodder.core;

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
import zdoctor.lazymodder.easy.EasyItems;

public class DevTools {
	public static void registerItemModels(String modId) {
		IForgeRegistry<Item> itemReg = GameRegistry.findRegistry(Item.class);
		itemReg.getKeys().forEach(res -> {
			if (res.getResourceDomain().equalsIgnoreCase(modId)) {
				// System.out.println(res.getResourceDomain() + ":" +
				// res.getResourcePath());
				EasyItems item = (EasyItems) itemReg.getValue(res);
				// System.out.println("Has SubItems: " + item.getHasSubtypes());
				// System.out.println("SubCount: " + item.getSubCount());
				for (int i = 0; i < item.getSubCount(); i++) {
					System.out.println("Registered: " + res.getResourceDomain() + ":" + item.getNameFromMeta(i));
					ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(
							res.getResourceDomain() + ":" + item.getNameFromMeta(i), "inventory"));
				}
			}
		});

	}
}
