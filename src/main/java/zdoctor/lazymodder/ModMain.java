package zdoctor.lazymodder;

import java.lang.reflect.Field;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.SplashProgress;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import zdoctor.lazymodder.easy.registry.LMClientRegistry;
import zdoctor.lazymodder.proxy.CommonProxy;

/**
 * @author Z_Doctor
 * 
 */
@Mod(modid = ModMain.MODID)
public class ModMain {
	public static final String MODID = "lazymodder";
	// /**
	// * Set this to true to enable JSon generator tool.
	// */
	// public static final boolean DEV_ENV = true;
	// TODO add back json generator
	@Instance
	public static ModMain mod = new ModMain();

	static {
		FluidRegistry.enableUniversalBucket();
	}

	@SidedProxy(clientSide = "zdoctor.lazymodder.proxy.ClientProxy", serverSide = "zdoctor.lazymodder.proxy.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		// File jarFile = JavaHelper.getLocationOfMod(MODID);
		// System.out.println("Location: " + jarFile.getAbsolutePath());
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

	@EventHandler
	public void fmlFinished(FMLLoadCompleteEvent e) {
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			Thread t = new Thread() {
				Field done;

				@Override
				public synchronized void start() {
					try {
						done = SplashProgress.class.getDeclaredField("done");
						done.setAccessible(true);
					} catch (NoSuchFieldException | SecurityException e) {
						e.printStackTrace();
					}
					super.start();
				}

				@Override
				public void run() {
					try {
						while (!done.getBoolean(SplashProgress.class)) {

						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					LMClientRegistry.fmlPostInit();
				}
			};
			t.start();
		}
	}

}