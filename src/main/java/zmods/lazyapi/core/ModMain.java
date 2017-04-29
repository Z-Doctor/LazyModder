package zmods.lazyapi.core;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zmods.lazyapi.proxy.CommonProxy;

@Mod(modid = "lazyapi", version = "0.1")
public class ModMain {
	public static final boolean DEV_ENV = true;
	@Instance
	public static ModMain mod = new ModMain();
	
	@SidedProxy(clientSide = "zmods.lazyapi.proxy.ClientProxy", serverSide = "zmods.lazyapi.proxy.ServerProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		if(DEV_ENV)
			DevTools.preInit(e);
		proxy.preInit(e);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		if(DEV_ENV)
			DevTools.init(e);
		proxy.init(e);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		if(DEV_ENV)
			DevTools.postInit(e);
		proxy.postInit(e);
	}

}