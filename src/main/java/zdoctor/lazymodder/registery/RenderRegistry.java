package zdoctor.lazymodder.registery;

import java.lang.reflect.InvocationTargetException;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import zdoctor.lazymodder.easy.blocks.EasyBlock;
import zdoctor.lazymodder.easy.blocks.IEasyBlock;
import zdoctor.lazymodder.easy.items.EasyItem;
import zdoctor.lazymodder.easy.items.IEasyItem;

public class RenderRegistry {

	public static void registerItemModels() {
		IForgeRegistry<Item> itemReg = GameRegistry.findRegistry(Item.class);
		itemReg.getValues().forEach(item -> {
			if (item instanceof IEasyItem) {
				NonNullList<ItemStack> subItems = NonNullList.create();
				item.getSubItems(CreativeTabs.SEARCH, subItems);
				for (int i = 0; i < subItems.size(); i++) {
					System.out.println("Registered Model: " + subItems.get(i).getUnlocalizedName().substring(5));
					ModelLoader.setCustomModelResourceLocation(item, i,
							new ModelResourceLocation(item.getRegistryName().getResourceDomain() + ":"
									+ subItems.get(i).getUnlocalizedName().substring(5), "inventory"));
				}
			}
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

	public static void registerEntityRenderingHandler(Class<? extends EntityLiving> entityClass,
			Class<? extends RenderLiving> entityRender) {
		registerEntityRenderingHandler(entityClass, new IRenderFactory() {

			@Override
			public Render createRenderFor(RenderManager manager) {
				try {
					return entityRender.getConstructor(RenderManager.class).newInstance(manager);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		});
	}

	public static void registerEntityRenderingHandler(Class<? extends Entity> entityClass,
			IRenderFactory<Entity> renderFactory) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}
}
