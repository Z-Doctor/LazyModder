package zdoctor.lazymodder;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import zdoctor.lazymodder.client.render.itemrender.IItemRendererRenderItem;
import zdoctor.lazymodder.easy.registry.EasyRegistry;
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