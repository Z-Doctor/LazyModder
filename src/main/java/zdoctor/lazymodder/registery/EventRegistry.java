package zdoctor.lazymodder.registery;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zdoctor.lazymodder.events.RuleChangedEvent;

public class EventRegistry {
	private static NonNullList<Object> eventList = NonNullList.create();

	public static void init() {
		eventList.forEach(event -> MinecraftForge.EVENT_BUS.register(event));
		eventList.clear();
	}

	public static void register(Class eventClass) {
		try {
			MinecraftForge.EVENT_BUS.register(eventClass.newInstance());
		} catch (IllegalAccessException | InstantiationException e) {
			FMLLog.log.catching(Level.FATAL, e);
		}
	}

	public static void register(Object modEvents) {
		eventList.add(modEvents);
	}

	public static class builtinEvents {
		@SubscribeEvent
		public void loadGameRules(WorldEvent.Unload e) {
			World world = e.getWorld();
			GameRules gRules = world.getGameRules();
			zdoctor.lazymodder.client.GameRules.customGameRules.forEach((key, ruleValue) -> {
				String currentRule = gRules.getString(key);
				RuleChangedEvent ruleEvent = new RuleChangedEvent(key, ruleValue.getKey(), ruleValue.getValue(),
						currentRule);
				System.out.println("Changing Rules on unload");
				MinecraftForge.EVENT_BUS.post(ruleEvent);
				if (!ruleEvent.isCanceled()) {
					if (!gRules.getString(key).equals(""))
						gRules.setOrCreateGameRule(key, ruleValue.getKey());
					else
						gRules.addGameRule(key, ruleValue.getKey(), ruleValue.getValue());
				}
			});
		}
		
		@SubscribeEvent
		public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
			System.out.println("Registering Recipes");
			RecipeRegistry.registerRecipes(event);
		}
		
		@SubscribeEvent
		public void registerItems(RegistryEvent.Register<Item> event) {
			System.out.println("Registering Items");
			
			ItemRegistry.registerItems(event);
		}
		
		@SubscribeEvent
		public void registerBlocks(RegistryEvent.Register<Block> event) {
			System.out.println("Registering Blocks");
			
			BlockRegistry.registerBlocks(event);
		}
		
		@SideOnly(Side.CLIENT)
		@SubscribeEvent
		public void registerModels(ModelRegistryEvent event) {
			System.out.println("Registering Models");
			ItemRegistry.registerItemModels();
			BlockRegistry.registerBlockModels();
		}
		
	}
}
