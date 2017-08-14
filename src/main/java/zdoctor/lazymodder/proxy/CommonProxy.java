package zdoctor.lazymodder.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zdoctor.lazymodder.helpers.KeyBindingHelper;
import zdoctor.lazymodder.registery.EntityRegistry;
import zdoctor.lazymodder.registery.EventRegistry;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e) {
//		ConfigGuiFactory.load(e);
//		CoreEvents.load();
		
		EventRegistry.register(EventRegistry.builtinEvents.class);
		EventRegistry.register(KeyBindingHelper.class);
		EntityRegistry.registerEntities();
		
	}
	
	public void init(FMLInitializationEvent e) {
		EventRegistry.init();
	}
	
	public void postInit(FMLPostInitializationEvent e) {
		
	}
	
}