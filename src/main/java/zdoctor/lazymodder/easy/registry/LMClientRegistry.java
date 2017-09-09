package zdoctor.lazymodder.easy.registry;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.google.common.collect.BiMap;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zdoctor.lazymodder.ModMain;
import zdoctor.lazymodder.client.render.itemrender.IItemRenderer;
import zdoctor.lazymodder.client.render.itemrender.IItemRendererAPI;
import zdoctor.lazymodder.easy.entity.living.EasyEntityLiving;
import zdoctor.lazymodder.easy.interfaces.ICustomMeshDefinition;
import zdoctor.lazymodder.easy.interfaces.ICustomStateMap;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.interfaces.IEasyTileEntity;
import zdoctor.lazymodder.easy.interfaces.INoModel;
import zdoctor.lazymodder.easy.interfaces.IRenderLiving;
import zdoctor.lazymodder.easy.interfaces.IRenderTESR;

@SideOnly(Side.CLIENT)
public class LMClientRegistry {
	public static void registerLivingEntityRenderer(EasyEntityLiving entity, Class<? extends RenderLiving> renderer) {
		System.out.println("Registered Entity Renderer: " + entity.getRegistryName());
		registerEntityRenderingHandler(entity.getEntityClass(), renderer);
	}

	public static void bindTileEntitySpecialRenderer(Block block, Class<? extends TileEntity> tileEntityClass,
			TileEntitySpecialRenderer renderer) {
		try {
			ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, renderer);

			if (renderer instanceof IItemRenderer) {
				System.out.println("Registered Item Render");
				IItemRendererAPI.registerIItemRenderer(Item.getItemFromBlock(block), (IItemRenderer) renderer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed");
		}
	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		System.out.println("Registering Models");
		registerItemModels();
		registerBlockModels();
		registerLivingEntityRenderers();
	}

	public static void registerEntityRenderingHandler(Class<? extends Entity> entityClass,
			IRenderFactory<Entity> renderFactory) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}

	/**
	 * Called internally in {@link EasyEntityLivingWithRender}, but is here for
	 * others convience
	 * 
	 * @param entityClass
	 * @param entityRender
	 */
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

	private void registerItemModels() {
		EasyRegistry.getItemList().forEach(item -> {
			if (item instanceof IEasyRegister && !(item instanceof INoModel)) {
				IEasyRegister item1 = (IEasyRegister) item;
				for (int i = 0; i < item1.getSubCount(); i++) {
					System.out.println("REG ITEM: " + item.getRegistryName().getResourceDomain() + ":"
							+ item1.getRegistryNameForMeta(i));
					ModelLoader.setCustomModelResourceLocation(item, item1.getRegistryMeta(item, i),
							new ModelResourceLocation(
									item.getRegistryName().getResourceDomain() + ":" + item1.getRegistryNameForMeta(i),
									"inventory"));
				}
			} else if (item instanceof INoModel) {
				IEasyRegister item1 = (IEasyRegister) item;
				for (int i = 0; i < item1.getSubCount(); i++) {
					System.out.println("REG ITEM: " + item.getRegistryName().getResourceDomain() + ":"
							+ item1.getRegistryNameForMeta(i));
					ModelLoader.setCustomModelResourceLocation(item, i,
							new ModelResourceLocation(ModMain.MODID + ":" + "empty_item", "inventory"));
				}
			}
		});
	}

	private void registerBlockModels() {
		EasyRegistry.getBlockList().forEach(block -> {
			if (block instanceof IEasyRegister) {
				IEasyRegister block1 = (IEasyRegister) block;

				if (block instanceof ICustomMeshDefinition) {
					ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(block),
							((ICustomMeshDefinition) block).getMeshDefinition());
				}
				if (block instanceof ICustomStateMap) {
					ModelLoader.setCustomStateMapper(block, ((ICustomStateMap) block).getStateMap());
				}

				if (block instanceof IEasyTileEntity) {
					Class<? extends TileEntity> tileEntityClass = ((IEasyTileEntity) block).getTileEntity();
					if (IRenderTESR.class.isAssignableFrom(tileEntityClass)) {
						System.out.println("REG TileEntity Render: " + block.getRegistryName());
						try {
							Class<? extends TileEntitySpecialRenderer> renderClass = ((IRenderTESR) tileEntityClass
									.newInstance()).getRenderer();
							TileEntitySpecialRenderer renderer = renderClass.newInstance();
							bindTileEntitySpecialRenderer(block, tileEntityClass, renderer);
						} catch (InstantiationException | IllegalAccessException e) {
							e.printStackTrace();
							System.out.println("REG for TileEntity Render failed: " + block.getRegistryName());
						}
					}
				}

				if (!(block instanceof INoModel)) {
					for (int i = 0; i < block1.getSubCount(); i++) {
						System.out.println("REG BLOCK: " + block.getRegistryName().getResourceDomain() + ":"
								+ block1.getRegistryNameForMeta(i));
						ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
								block1.getRegistryMeta(Item.getItemFromBlock(block), i),
								new ModelResourceLocation(block.getRegistryName().getResourceDomain() + ":"
										+ block1.getRegistryNameForMeta(i), "inventory"));
					}
				}

			}
		});
	}

	private void registerLivingEntityRenderers() {
		EasyRegistry.getEntityList().forEach(entity -> {
			if (IRenderLiving.class.isAssignableFrom(entity.getEntityClass()))
				try {
					LMClientRegistry.registerLivingEntityRenderer(entity, ((IRenderLiving) entity.getEntityClass()
							.getConstructor(World.class).newInstance((World) null)).getLivingRenderer());
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
					FMLLog.log.debug("Unable to register entity '{}' render", entity);
				}
		});
	}

	public static void fmlPostInit() {
		Field guiFactories;
		try {
			guiFactories = FMLClientHandler.class.getDeclaredField("guiFactories");
			guiFactories.setAccessible(true);
			BiMap<ModContainer, IModGuiFactory> guiMap = (BiMap<ModContainer, IModGuiFactory>) guiFactories
					.get(FMLClientHandler.instance());
			EasyRegistry.getGuiMap().forEach(set -> {
				guiMap.forcePut(set.getKey(), set.getValue());
			});
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
