package zdoctor.lazymodder.registery;

import java.lang.reflect.InvocationTargetException;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
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
	
	public static void registerEntityRenderingHandlers() {
		System.out.println("registering Handlers");
		EntityRegistry.entityList.forEach(entity -> {
			if(entity.getEntityClass() != null && entity.getEntityRenderer() != null)
				registerEntityRenderingHandler(entity.getEntityClass(), entity.getEntityRenderer());
		});
	}
	
	public static void registerEntityRenderingHandler(Class<? extends EntityLiving> entityClass, Class<? extends RenderLiving> entityRender) {
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
	
	public static void registerEntityRenderingHandler(Class<? extends Entity> entityClass, IRenderFactory<Entity> renderFactory) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}
}
