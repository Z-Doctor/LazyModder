package zdoctor.lazymodder;

import java.io.File;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zdoctor.lazymodder.builtin.helpers.JavaHelper;
import zdoctor.lazymodder.proxy.CommonProxy;

/**
 * @author Z_Doctor
 * 
 */
@Mod(modid = ModMain.MODID)
public class ModMain {
	public static final String MODID = "lazymodder";
	/**
	 * Set this to true to enable JSon generator tool.
	 */
	public static final boolean DEV_ENV = true;
	@Instance
	public static ModMain mod = new ModMain();

	@SidedProxy(clientSide = "zdoctor.lazymodder.proxy.ClientProxy", serverSide = "zdoctor.lazymodder.proxy.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		File jarFile = JavaHelper.getLocationOfMod(MODID);
		System.out.println("Location: " + jarFile.getAbsolutePath());
		proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}

}