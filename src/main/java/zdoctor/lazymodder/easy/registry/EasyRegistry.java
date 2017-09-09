package zdoctor.lazymodder.easy.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistry;
import zdoctor.lazymodder.easy.config.EasyConfigGui;
import zdoctor.lazymodder.easy.entity.living.EasyEntityLiving;
import zdoctor.lazymodder.easy.interfaces.IEasyTileEntity;
import zdoctor.lazymodder.easy.interfaces.IEasyWorldGenerator;
import zdoctor.lazymodder.easy.interfaces.IHaveRecipe;

public class EasyRegistry {
	private static ArrayList<Block> BLOCKLIST = new ArrayList<>();
	private static ArrayList<Item> ITEMLIST = new ArrayList<>();
	private static ArrayList<IRecipe> RECIPELIST = new ArrayList<>();

	private static ArrayList<EasyEntityLiving> ENTITYLIST = new ArrayList<>();
	private static Map<String, Integer> MOD_ENTITY_UID = new HashMap<>();

	private static ArrayList<IEasyWorldGenerator> WORLDGENLIST = new ArrayList<>();
	// private static ArrayList<RenderLiving> entityRendererList = new
	// ArrayList<>();
	// private static ArrayList<IEasyGuiHandler> guiHandlerList = new
	// ArrayList<>();
	private static final Map<ModContainer, EasyConfigGui> GUIMAP = new HashMap<>();

	private static ArrayList<Object> eventList = new ArrayList<>();

	/**
	 * Blocks should be registered during preInit
	 * 
	 * @param block
	 */
	public static void register(Block block) {
		BLOCKLIST.add(block);
	}

	/**
	 * Items should be registered during preInit
	 * 
	 * @param item
	 */
	public static void register(Item item) {
		ITEMLIST.add(item);
	}

	/**
	 * Recipes should be registered after items have been initialized and during
	 * preInit
	 * 
	 * @param recipe
	 */
	public static void register(IRecipe recipe) {
		RECIPELIST.add(recipe);
	}

	public static void register(IEasyWorldGenerator worldGen) {
		WORLDGENLIST.add(worldGen);
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

	public static <T extends EasyEntityLiving> void register(T entity) {
		System.out.println("Registered Living Entity: " + entity.getRegistryName());
		String mod = entity.getRegistryName().getResourceDomain();
		Integer temp = MOD_ENTITY_UID.putIfAbsent(mod, 0);
		int id = temp == null ? 0 : temp.intValue();
		MOD_ENTITY_UID.put(mod, ++id);

		if (entity.hasEgg())
			net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entity.getRegistryName(),
					entity.getEntityClass(), entity.getRegistryName().getResourcePath(), id, mod,
					entity.getTrackingRange(), entity.getUpdateFrequency(), entity.sendsVelocityUpdates(),
					entity.getPrimaryEggColor(), entity.getSecondaryEggColor());
		else
			net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entity.getRegistryName(),
					entity.getEntityClass(), entity.getRegistryName().getResourcePath(), id, mod,
					entity.getTrackingRange(), entity.getUpdateFrequency(), entity.sendsVelocityUpdates());

		ENTITYLIST.add(entity);
	}

	public static void register(EasyConfigGui easyConfigGui) {
		GUIMAP.put(getActiveMod(), easyConfigGui);
	}

	// /**
	// * Used to register IEasyGuiHandler
	// *
	// * @param guiHandler
	// */
	// public static void register(IEasyGuiHandler guiHandler) {
	// guiHandlerList.add(guiHandler);
	// }

	@SubscribeEvent
	public void registerBlocks(Register<Block> event) {
		System.out.println(FMLCommonHandler.instance().getSide().toString());
		System.out.println("Registring Blocks");

		IForgeRegistry<Block> registry = event.getRegistry();
		BLOCKLIST.forEach(block -> {
			if (block instanceof IHaveRecipe) {
				NonNullList<IRecipe> recipeList = NonNullList.create();
				((IHaveRecipe) block).addRecipeToList(recipeList);
				for (IRecipe iRecipe : recipeList) {
					register(iRecipe);
				}
			}

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
		ITEMLIST.forEach(item -> {
			if (item instanceof IHaveRecipe) {
				NonNullList<IRecipe> recipeList = NonNullList.create();
				((IHaveRecipe) item).addRecipeToList(recipeList);
				for (IRecipe iRecipe : recipeList) {
					register(iRecipe);
				}
			}
			System.out.println("Registered Item: " + item.getRegistryName());
			registry.register(item);
		});
	}

	@SubscribeEvent
	public void registerRecipes(Register<IRecipe> event) {
		System.out.println("Registering Recipes");

		IForgeRegistry<IRecipe> registry = event.getRegistry();
		RECIPELIST.forEach(recipe -> {
			System.out.println("Registered Recipe: " + recipe.getRegistryName());
			registry.register(recipe);
		});
	}

	public static void registerWorldGen() {
		WORLDGENLIST.forEach(gen -> {
			GameRegistry.registerWorldGenerator(gen, gen.getGenWeight());
		});
	}

	/**
	 * Add a spawn entry for the supplied entity in the supplied {@link Biome}
	 * list
	 * 
	 * @param weightedProb
	 *            Probability
	 * @param min
	 *            Min spawn count
	 * @param max
	 *            Max spawn count
	 * @param typeOfCreature
	 *            Type of spawn
	 * @param biomes
	 *            List of biomes
	 */
	public static void addSpawn(Class<? extends EntityLiving> entityClass, int weightedProb, int min, int max,
			EnumCreatureType typeOfCreature, Biome[] biomes) {
		EntityRegistry.addSpawn(entityClass, weightedProb, min, max, typeOfCreature, biomes);
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

	public static Side getSide() {
		return FMLCommonHandler.instance().getSide();
	}

	public static ModContainer getActiveMod() {
		return Loader.instance().activeModContainer();
	}

	public static ImmutableList<Block> getBlockList() {
		return new ImmutableList.Builder().addAll(BLOCKLIST).build();
	}

	public static ImmutableList<Item> getItemList() {
		return new ImmutableList.Builder().addAll(ITEMLIST).build();
	}

	public static ImmutableList<EasyEntityLiving> getEntityList() {
		return new ImmutableList.Builder().addAll(ENTITYLIST).build();
	}

	public static ImmutableSet<Map.Entry<ModContainer, EasyConfigGui>> getGuiMap() {
		return new ImmutableSet.Builder().addAll(GUIMAP.entrySet()).build();
	}

	public static void init() {
		registerWorldGen();
	}
}
