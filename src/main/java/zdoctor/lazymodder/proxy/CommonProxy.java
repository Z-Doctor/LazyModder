package zdoctor.lazymodder.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zdoctor.lazymodder.easy.config.EasyConfig;
import zdoctor.lazymodder.easy.registry.EasyRegistry;
import zdoctor.lazymodder.events.AdditonalEvents;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e) {
//		EasyRegistry.register(EasyRegistry.builtinEvents.class);
		EasyRegistry.register(EasyRegistry.class);
		EasyRegistry.register(AdditonalEvents.class);
		EasyRegistry.register(EasyConfig.class);
	}
	
	public void init(FMLInitializationEvent e) {
		EasyRegistry.init();
	}
	
	public void postInit(FMLPostInitializationEvent e) {
		
	}
	
}