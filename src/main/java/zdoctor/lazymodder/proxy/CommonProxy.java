package zdoctor.lazymodder.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e) {
//		ConfigGuiFactory.load(e);
//		CoreEvents.load();
//		Items.preInit();
//		Blocks.preInit();
//		Entity.preInit();
//		Food.preInit();
//		Misc.preInit();
	}
	
	public void init(FMLInitializationEvent e) {
//		NetworkRegistry.INSTANCE.registerGuiHandler(ModMain.mod, new GUIHandler());
//		EventRegistry.init();
//		GUIs.init(e);
	}
	
	public void postInit(FMLPostInitializationEvent e) {
		
	}
	
}