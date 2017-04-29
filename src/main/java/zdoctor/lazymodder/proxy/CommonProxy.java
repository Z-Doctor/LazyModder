package zdoctor.lazymodder.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import zdoctor.lazymodder.ModMain;
import zdoctor.lazymodder.core.EventRegistry;
import zdoctor.lazymodder.core.Misc;
import zdoctor.lazymodder.easy.EasyGUI.GUIHandler;
import zdoctor.lazymodder.examples.Blocks;
import zdoctor.lazymodder.examples.Entity;
import zdoctor.lazymodder.examples.Food;
import zdoctor.lazymodder.examples.GUIs;
import zdoctor.lazymodder.examples.Items;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e) {
//		ConfigGuiFactory.load(e);
//		CoreEvents.load();
		Items.preInit();
		Blocks.preInit();
		Entity.preInit();
		Food.preInit();
		Misc.preInit();
	}
	
	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(ModMain.mod, new GUIHandler());
		EventRegistry.init();
		GUIs.init(e);
	}
	
	public void postInit(FMLPostInitializationEvent e) {
		
	}
	
}