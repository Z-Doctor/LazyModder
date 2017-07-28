package zdoctor.lazymodder.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zdoctor.lazymodder.core.Misc;
import zdoctor.lazymodder.examples.Entity;
import zdoctor.lazymodder.registery.EntityRegistry;
import zdoctor.lazymodder.registery.EventRegistry;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e) {
//		ConfigGuiFactory.load(e);
//		CoreEvents.load();
		
		Entity.preInit();
		
		Misc.preInit();
		//Do not comment out
		EventRegistry.register(EventRegistry.builtinEvents.class);
		
		EntityRegistry.registerEntities();
	}
	
	public void init(FMLInitializationEvent e) {
//		NetworkRegistry.INSTANCE.registerGuiHandler(ModMain.mod, new GUIHandler());
		//Do not comment out
		EventRegistry.init();
//		GUIs.init(e);
	}
	
	public void postInit(FMLPostInitializationEvent e) {
		
	}
	
}