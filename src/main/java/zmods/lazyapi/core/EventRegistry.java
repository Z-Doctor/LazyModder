package zmods.lazyapi.core;

import org.apache.logging.log4j.Level;

import net.minecraft.util.NonNullList;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zmods.lazyapi.easy.EasyFunctions;
import zmods.lazyapi.events.RuleChangedEvent;

public class EventRegistry {
	private static NonNullList<Object> eventList = NonNullList.create();

	public static void init() {
		eventList.forEach(event -> MinecraftForge.EVENT_BUS.register(event));
		eventList.clear();
	}

	public static void register(Class eventClass) {
		try {
			register(eventClass.newInstance());
		} catch (IllegalAccessException | InstantiationException e) {
			FMLLog.log(Level.FATAL, "Unable to create a new instance of {} for event handling.", eventClass.getName());
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
			EasyFunctions.customGameRules.forEach((key, ruleValue) -> {
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

	}
}
