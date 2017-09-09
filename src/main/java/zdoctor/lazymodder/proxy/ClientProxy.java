package zdoctor.lazymodder.proxy;

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
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import zdoctor.lazymodder.builtin.helpers.KeyBindingHelper;
import zdoctor.lazymodder.client.render.itemrender.IItemRendererRenderItem;
import zdoctor.lazymodder.easy.registry.EasyRegistry;
import zdoctor.lazymodder.easy.registry.LMClientRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		EasyRegistry.register(KeyBindingHelper.class);
		EasyRegistry.register(LMClientRegistry.class);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		// Adds support for IItemRender
		IReloadableResourceManager mcResourceManager = (IReloadableResourceManager) Minecraft.getMinecraft()
				.getResourceManager();

		if (mcResourceManager instanceof SimpleReloadableResourceManager) {
			List<IResourceManagerReloadListener> reloadListeners = ReflectionHelper.getPrivateValue(
					SimpleReloadableResourceManager.class, (SimpleReloadableResourceManager) mcResourceManager,
					"reloadListeners", "field_110546_b");
			reloadListeners.remove(Minecraft.getMinecraft().getItemRenderer());
			reloadListeners.remove(Minecraft.getMinecraft().entityRenderer);
			reloadListeners.remove(Minecraft.getMinecraft().renderGlobal);
		}

		RenderItem renderItem = new IItemRendererRenderItem(Minecraft.getMinecraft().getRenderItem(),
				Minecraft.getMinecraft().renderEngine, Minecraft.getMinecraft().getItemColors());
		ReflectionHelper.setPrivateValue(Minecraft.class, Minecraft.getMinecraft(), renderItem, "renderItem",
				"field_175621_X");

		RenderManager renderManager = new RenderManager(Minecraft.getMinecraft().renderEngine, renderItem);
		ReflectionHelper.setPrivateValue(Minecraft.class, Minecraft.getMinecraft(), renderManager, "renderManager",
				"field_175616_W");

		ItemRenderer itemRenderer = new ItemRenderer(Minecraft.getMinecraft());
		mcResourceManager.registerReloadListener(renderItem);
		ReflectionHelper.setPrivateValue(Minecraft.class, Minecraft.getMinecraft(), itemRenderer, "itemRenderer",
				"field_175620_Y");

		EntityRenderer entityRenderer = new EntityRenderer(Minecraft.getMinecraft(), mcResourceManager);
		mcResourceManager.registerReloadListener(entityRenderer);
		ReflectionHelper.setPrivateValue(Minecraft.class, Minecraft.getMinecraft(), entityRenderer, "entityRenderer",
				"field_71460_t");

		RenderGlobal renderGlobal = new RenderGlobal(Minecraft.getMinecraft());
		mcResourceManager.registerReloadListener(renderGlobal);
		ReflectionHelper.setPrivateValue(Minecraft.class, Minecraft.getMinecraft(), renderGlobal, "renderGlobal",
				"field_71438_f");
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
}