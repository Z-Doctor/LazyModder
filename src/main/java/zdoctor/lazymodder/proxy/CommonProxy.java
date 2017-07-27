package zdoctor.lazymodder.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zdoctor.lazymodder.core.EventRegistry;
import zdoctor.lazymodder.core.Misc;
import zdoctor.lazymodder.examples.Blocks;
import zdoctor.lazymodder.examples.Entity;
import zdoctor.lazymodder.examples.Food;
import zdoctor.lazymodder.examples.ZItems;
import zdoctor.lazymodder.examples.Recipes;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e) {
//		ConfigGuiFactory.load(e);
//		CoreEvents.load();
		ZItems.preInit();
		Blocks.preInit();
		Entity.preInit();
		Food.preInit();
		Recipes.preInit();
		//Do not comment out
		Misc.preInit();
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