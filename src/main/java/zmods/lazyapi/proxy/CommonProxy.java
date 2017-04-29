package zmods.lazyapi.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import zmods.lazyapi.core.EventRegistry;
import zmods.lazyapi.core.Misc;
import zmods.lazyapi.core.ModMain;
import zmods.lazyapi.easy.EasyGUI.GUIHandler;
import zmods.lazyapi.examples.Blocks;
import zmods.lazyapi.examples.Entity;
import zmods.lazyapi.examples.Food;
import zmods.lazyapi.examples.GUIs;
import zmods.lazyapi.examples.Items;

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