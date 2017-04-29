package zmods.lazyapi.core;

public class Misc {
	public static void preInit() {
		EventRegistry.register(EventRegistry.builtinEvents.class);
	}
}
