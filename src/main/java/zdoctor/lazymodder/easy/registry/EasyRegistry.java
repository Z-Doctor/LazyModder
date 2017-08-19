package zdoctor.lazymodder.easy.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import zdoctor.lazymodder.ModMain;
import zdoctor.lazymodder.client.render.block.statemap.EmptyStateMap;
import zdoctor.lazymodder.client.render.itemrender.IItemRenderer;
import zdoctor.lazymodder.client.render.itemrender.IItemRendererAPI;
import zdoctor.lazymodder.easy.crafting.EasyRecipe;
import zdoctor.lazymodder.easy.entity.living.EasyLivingEntity;
import zdoctor.lazymodder.easy.interfaces.ICustomStateMap;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.interfaces.IEasyTileEntity;
import zdoctor.lazymodder.easy.interfaces.INoModel;

public class EasyRegistry {
	private static ArrayList<Block> blockList = new ArrayList<>();
	private static ArrayList<Item> itemList = new ArrayList<>();
	private static ArrayList<EasyRecipe> recipeList = new ArrayList<>();
	private static ArrayList<Object> eventList = new ArrayList<>();
	private static ArrayList<EasyLivingEntity> entityList = new ArrayList<>();
	private static Map<String, Integer> UID = new HashMap<>();

	public static void register(Block block) {
		blockList.add(block);
	}

	public static void register(Item item) {
		itemList.add(item);
	}

	public static void register(EasyRecipe recipe) {
		recipeList.add(recipe);
	}
	
	/**
	 * Used to register event classes
	 * 
	 * @param eventClass
	 *            - I.e ModEvent.class
	 */
	public static void register(Class eventClass) {
		try {
			MinecraftForge.EVENT_BUS.register(eventClass.newInstance());
		} catch (IllegalAccessException | InstantiationException e) {
			FMLLog.log.catching(Level.FATAL, e);
		}
	}

	public static void register(EasyLivingEntity entity) {
		System.out.println("Registered Living Entity: " + entity.getRegistryName());

		String mod = entity.getRegistryName().getResourceDomain();
		Integer temp = UID.putIfAbsent(mod, 0);
		int id = temp == null ? 0 : temp.intValue();
		UID.put(mod, ++id);

		if (entity.hasEgg())
			net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entity.getRegistryName(),
					entity.getEntityClass(), entity.getRegistryName().getResourcePath(), id, mod,
					entity.getTrackingRange(), entity.getUpdateFrequency(), entity.sendsVelocityUpdates(),
					entity.getPrimaryEggColor(), entity.getSecondaryEggColor());
		else
			net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entity.getRegistryName(),
					entity.getEntityClass(), entity.getRegistryName().getResourcePath(), id, mod,
					entity.getTrackingRange(), entity.getUpdateFrequency(), entity.sendsVelocityUpdates());

		if (FMLCommonHandler.instance().getSide() == Side.CLIENT && entity.getEntityRenderer() != null) {
			System.out.println("Client side");
			registerEntityRenderingHandler(entity.getEntityClass(), entity.getEntityRenderer());
		}
	}

	@SubscribeEvent
	public void registerBlocks(Register<Block> event) {
		System.out.println(FMLCommonHandler.instance().getSide().toString());
		System.out.println("Registring Blocks");

		IForgeRegistry<Block> registry = event.getRegistry();
		blockList.forEach(block -> {
			System.out.println("Registered Block: " + block.getRegistryName());
			registry.register(block);

			if (block instanceof IEasyTileEntity) {
				System.out.println("Registered TileEntity: " + block.getRegistryName());
				IEasyTileEntity block1 = (IEasyTileEntity) block;
				GameRegistry.registerTileEntity(block1.getTileEntity(), block1.getTileEntityRegistryName());
			}
		});
	}

	@SubscribeEvent
	public void registerItems(Register<Item> event) {
		System.out.println(FMLCommonHandler.instance().getSide().toString());
		System.out.println("Registering Items");

		IForgeRegistry<Item> registry = event.getRegistry();
		itemList.forEach(item -> {
			System.out.println("Registered Item: " + item.getRegistryName());
			registry.register(item);
		});
	}

	@SubscribeEvent
	public void registerRecipes(Register<IRecipe> event) {
		System.out.println("Registering Recipes");

		IForgeRegistry<IRecipe> registry = event.getRegistry();
		recipeList.forEach(recipe -> {
			System.out.println("Registered Recipe: " + recipe.getRegistryName());
			registry.register(recipe);
		});
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		System.out.println("Registering Models");
		registerItemModels();
		registerBlockModels();
	}

	@SideOnly(Side.CLIENT)
	private void registerBlockModels() {
		blockList.forEach(block -> {
			if (block instanceof IEasyRegister) {
				IEasyRegister block1 = (IEasyRegister) block;
				
				if(block instanceof ICustomStateMap) {
					ModelLoader.setCustomStateMapper(block, ((ICustomStateMap)block).getStateMap());
				}
				
				if (block instanceof IEasyTileEntity) {
					System.out.println("REG TILEENTITY: " + block.getRegistryName());
					IEasyTileEntity tile = (IEasyTileEntity) block1;
					bindTileEntitySpecialRenderer(block, tile.getTileEntity(), tile.getTileEntityRenderer());
				}
				
				if (!(block instanceof INoModel)) {
					for (int i = 0; i < block1.getSubCount(); i++) {
						System.out.println("REG BLOCK: " + block.getRegistryName().getResourceDomain() + ":"
								+ block1.getRegistryNameForMeta(i));
						ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i,
								new ModelResourceLocation(block.getRegistryName().getResourceDomain() + ":"
										+ block1.getRegistryNameForMeta(i), "inventory"));
					}
				}

				if (block instanceof INoModel) {
					ModelLoader.setCustomStateMapper(block, new EmptyStateMap());
				}
			}
		});
	}

	@SideOnly(Side.CLIENT)
	private void registerItemModels() {
		itemList.forEach(item -> {
			if (item instanceof IEasyRegister && !(item instanceof INoModel)) {
				IEasyRegister item1 = (IEasyRegister) item;
				for (int i = 0; i < item1.getSubCount(); i++) {
					System.out.println("REG ITEM: " + item.getRegistryName().getResourceDomain() + ":"
							+ item1.getRegistryNameForMeta(i));
					ModelLoader.setCustomModelResourceLocation(item, i,
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

	public static void addSpawn(Class<? extends EntityLiving> entityClass, int weightedProb, int min, int max,
			EnumCreatureType typeOfCreature, Biome[] biomes) {
		EntityRegistry.addSpawn(entityClass, weightedProb, min, max, typeOfCreature, biomes);
	}

	public static void bindTileEntitySpecialRenderer(Block block, Class<? extends TileEntity> tileEntityClass,
			Class<? extends TileEntitySpecialRenderer> specialRenderer) {
		try {
			TileEntitySpecialRenderer renderer = specialRenderer.newInstance();
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
	
	public static void addPotionEffect(EntityLivingBase entity, PotionEffect effect) {
		entity.addPotionEffect(effect);
	}

	public static void addPotionEffectWithChance(EntityLivingBase entity, World world, PotionEffect effect,
			Float chance) {
		if (world.rand.nextFloat() < chance) {
			addPotionEffect(entity, effect);
		}
	}
}
