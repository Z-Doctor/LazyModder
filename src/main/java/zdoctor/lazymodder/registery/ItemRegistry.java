package zdoctor.lazymodder.registery;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import zdoctor.lazymodder.ModMain;
import zdoctor.lazymodder.easy.items.EasyItem;
import zdoctor.lazymodder.easy.items.IEasyItem;

public class ItemRegistry {
	private static ResourceLocation res = new ResourceLocation("lazymodder:models/item/standard_item.json");
	private static ArrayList<Item> itemList = new ArrayList<>();
	private static JFileChooser fc;

	public static void register(Item item) {
		System.out.println("added item: " + item.getRegistryName());
		if (ModMain.DEV_ENV) {
			if(fc == null)
				fc = new JFileChooser();
			boolean flag = false;
			try {
//				ModelLoaderRegistry.getModel(item.getRegistryName());
				ResourceLocation temp = new ResourceLocation(item.getRegistryName().getResourceDomain() + ":models/item/" + item.getRegistryName().getResourcePath() + ".json");
				Minecraft.getMinecraft().getResourceManager().getResource(temp).getInputStream();
			} catch (Exception e) {
				e.printStackTrace();
				flag = true;
			}

			if (flag) {
				System.out.println("Model Error: " + item.getRegistryName());
				createDefaultModel(item);
			}
		}

		itemList.add(item);
	}

	public static void createDefaultModel(Item item) {
		try {
			int reply = JOptionPane.showConfirmDialog(null, "Create default model?",
					"Model Missing - " + item.getRegistryName().getResourcePath(), JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = new File(fc.getSelectedFile(), item.getRegistryName().getResourcePath() + ".json");
					fc.setCurrentDirectory(fc.getSelectedFile());
					System.out.println(file.getAbsolutePath());
					if (file.createNewFile()) {
						System.out.println("Succesfully created file");
						BufferedWriter bw = new BufferedWriter(new FileWriter(file));

						InputStream in = Minecraft.getMinecraft().getResourceManager().getResource(res)
								.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(in));

						String s = null;
						while ((s = reader.readLine()) != null) {
							bw.write(s);
							bw.newLine();
						}
						bw.close();
					} else
						JOptionPane.showMessageDialog(null, "File already exists");
				}
			} else {
				// JOptionPane.showMessageDialog(null, "GOODBYE");
			}

			//
			// InputStream in =
			// Minecraft.getMinecraft().getResourceManager().getResource(res).getInputStream();
			//
			// BufferedReader reader = new BufferedReader(new
			// InputStreamReader(in));
			//
			// String s = null;
			// while((s = reader.readLine()) != null) {
			// System.out.println("Line: " + s);
			// }

		} catch (Exception e1) {
			System.out.println("Failed to load file");
			e1.printStackTrace();
		}

	}

	public static void registerItems(Register<Item> event) {
		System.out.println("registring items");
		IForgeRegistry<Item> registry = event.getRegistry();
		itemList.forEach(item -> {
			registry.register(item);
		});
	}
	
	public static void registerItemModels() {
		System.out.println("Registering Models");
//		Loader.instance().getActiveModList().forEach(container -> {
//			
//		});
		IForgeRegistry<Item> itemReg = GameRegistry.findRegistry(Item.class);
//		itemReg.getKeys().forEach(res -> {
		itemReg.getValues().forEach(item1 -> {
			if (item1 instanceof EasyItem) { //res.getResourceDomain().equalsIgnoreCase(modId)) {
				EasyItem item = (EasyItem) item1;
				// System.out.println("Has SubItems: " + item.getHasSubtypes());
				// System.out.println("SubCount: " + item.getSubCount());
				for (int i = 0; i < item.getSubCount(); i++) {
//					System.out.println("Registered: " + res.getResourceDomain() + ":" + item.getNameFromMeta(i));
					ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(
							item.getRegistryName().getResourceDomain() + ":" + item.getNameFromMeta(i), "inventory"));
				}
			} else if (item1 instanceof IEasyItem) { //res.getResourceDomain().equalsIgnoreCase(modId)) {
				IEasyItem item = (IEasyItem) item1;
				// System.out.println("Has SubItems: " + item.getHasSubtypes());
				// System.out.println("SubCount: " + item.getSubCount());
				for (int i = 0; i < item.getSubCount(); i++) {
//					System.out.println("Registered: " + res.getResourceDomain() + ":" + item.getNameFromMeta(i));
					ModelLoader.setCustomModelResourceLocation((Item) item, i, new ModelResourceLocation(
							item.getRegistryName().getResourceDomain() + ":" + item.getNameFromMeta(i), "inventory"));
				}
			}
		});

	}
}
