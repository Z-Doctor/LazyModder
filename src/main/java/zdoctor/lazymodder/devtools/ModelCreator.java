package zdoctor.lazymodder.devtools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Used to detect if a model is missing for blocks and items and create default
 * models for them. Due to certain limitations users must direct where to save
 * the models.
 * 
 * Users will be prompted if they want to create the model file.
 * 
 * EasyItems and items/blocks that implement IEasyItem/IEasyBlock will
 * automatically check for model existence and prompt. Other classes will have
 * to handle them self.
 * 
 * @author Z_Doctor
 *
 */
//TODO Update using IModelHelper
public class ModelCreator {
	private static ResourceLocation itemRes = new ResourceLocation("lazymodder:models/item/standard_item.json");
	private static ResourceLocation blockRes = new ResourceLocation("lazymodder:models/block/standard_block.json");
	private static ResourceLocation itemBlockRes = new ResourceLocation(
			"lazymodder:models/item/standard_itemblock.json");
	private static ResourceLocation blockStateRes = new ResourceLocation("lazymodder:blockstates/standard_block.json");

	private JFileChooser fc;

	public ModelCreator() {
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
	}

	public void createDefaultModel(ItemStack stack) {
		try {
			int reply = JOptionPane.showConfirmDialog(null, "Create default model?",
					"Item Model Missing - " + stack.getUnlocalizedName().substring(5), JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = new File(fc.getSelectedFile(), stack.getUnlocalizedName().substring(5) + ".json");
					fc.setCurrentDirectory(fc.getSelectedFile());

					if (file.createNewFile()) {
						System.out.println("Succesfully created model");
						BufferedWriter bw = new BufferedWriter(new FileWriter(file));

						InputStream in = Minecraft.getMinecraft().getResourceManager().getResource(itemRes)
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
			}

		} catch (Exception e1) {
			System.out.println("Failed to load file");
			// e1.printStackTrace();
		}
	}

	public void createDefaultModel(Item item) {
		try {
			int reply = JOptionPane.showConfirmDialog(null, "Create default model?",
					"Item Model Missing - " + item.getRegistryName().getResourcePath(), JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = new File(fc.getSelectedFile(), item.getRegistryName().getResourcePath() + ".json");
					fc.setCurrentDirectory(fc.getSelectedFile());

					if (file.createNewFile()) {
						// System.out.println("Succesfully created model");
						BufferedWriter bw = new BufferedWriter(new FileWriter(file));

						InputStream in = Minecraft.getMinecraft().getResourceManager().getResource(itemRes)
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
			}

		} catch (Exception e1) {
			System.out.println("Failed to load file");
			// e1.printStackTrace();
		}

	}

	public void createDefaultModel(Block block) {
		try {
			int reply = JOptionPane.showConfirmDialog(null, "Create default model?",
					"Block Model Missing - " + block.getRegistryName().getResourcePath(), JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = new File(fc.getSelectedFile(), block.getRegistryName().getResourcePath() + ".json");
					fc.setCurrentDirectory(fc.getSelectedFile());

					if (file.createNewFile()) {
						System.out.println("Succesfully created file");
						BufferedWriter bw = new BufferedWriter(new FileWriter(file));

						InputStream in = Minecraft.getMinecraft().getResourceManager().getResource(blockRes)
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
			}
		} catch (Exception e1) {
			System.out.println("Failed to load file");
			e1.printStackTrace();
		}

	}

	public void createDefaultBlockstate(Block block) {
		try {
			int reply = JOptionPane.showConfirmDialog(null, "Create default blockstate?",
					"Blockstate Missing - " + block.getRegistryName().getResourcePath(), JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = new File(fc.getSelectedFile(), block.getRegistryName().getResourcePath() + ".json");
					fc.setCurrentDirectory(fc.getSelectedFile());

					if (file.createNewFile()) {
						System.out.println("Succesfully created file");
						BufferedWriter bw = new BufferedWriter(new FileWriter(file));

						InputStream in = Minecraft.getMinecraft().getResourceManager().getResource(blockStateRes)
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
			}
		} catch (Exception e1) {
			System.out.println("Failed to load file");
			e1.printStackTrace();
		}
	}

	public void createDefaultModel(ItemBlock item) {
		try {
			int reply = JOptionPane.showConfirmDialog(null, "Create default model?",
					"Model Missing - " + item.getRegistryName().getResourcePath(), JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = new File(fc.getSelectedFile(), item.getRegistryName().getResourcePath() + ".json");
					fc.setCurrentDirectory(fc.getSelectedFile());

					if (file.createNewFile()) {
						// System.out.println("Succesfully created model");
						BufferedWriter bw = new BufferedWriter(new FileWriter(file));

						InputStream in = Minecraft.getMinecraft().getResourceManager().getResource(itemBlockRes)
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
			}

		} catch (Exception e1) {
			System.out.println("Failed to load file");
			// e1.printStackTrace();
		}
	}

	public static boolean doesFileExist(String res) {
		return doesFileExist(new ResourceLocation(res));
	}

	public static boolean doesFileExist(ResourceLocation res) {
		boolean exist = true;

		try {
			Minecraft.getMinecraft().getResourceManager().getResource(res).getInputStream();
		} catch (Exception e) {
			exist = false;
		}

		return exist;
	}

	public static String getItemPath(Item item, int meta) {
		return item.getRegistryName().getResourceDomain() + ":models/item/"
				+ item.getUnlocalizedName(new ItemStack(item, meta)).substring(5) + ".json";
	}

	public static String getItemPath(ItemStack stack) {
		return stack.getItem().getRegistryName().getResourceDomain() + ":models/item/"
				+ stack.getUnlocalizedName().substring(5) + ".json";
	}

	public static String getItemPath(Item item) {
		return item.getRegistryName().getResourceDomain() + ":models/item/" + item.getRegistryName().getResourcePath()
				+ ".json";
	}

	public String getBlockPath(Block block) {
		return block.getRegistryName().getResourceDomain() + ":models/block/"
				+ block.getRegistryName().getResourcePath() + ".json";
	}

	public String getBlockStatePath(Block block) {
		return block.getRegistryName().getResourceDomain() + ":blockstates/" + block.getRegistryName().getResourcePath()
				+ ".json";
	}

}
