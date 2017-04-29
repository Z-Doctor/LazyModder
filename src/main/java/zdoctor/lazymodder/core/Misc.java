package zdoctor.lazymodder.core;

public class Misc {
	public static void preInit() {
		EventRegistry.register(EventRegistry.builtinEvents.class);
	}
}
