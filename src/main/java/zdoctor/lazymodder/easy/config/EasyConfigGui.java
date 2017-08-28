package zdoctor.lazymodder.easy.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * If you extend the class, make sure to have an empty constructor
 * 
 */
public abstract class EasyConfigGui extends EasyConfig implements IModGuiFactory {
	public EasyConfigGui() {
		config = getEasyConfig().getConfig();
		modid = getEasyConfig().getModid();
	}

	public abstract EasyConfig getEasyConfig();

	public EasyConfigGui(FMLPreInitializationEvent e) {
		this(e, "Default");
	}

	public EasyConfigGui(FMLPreInitializationEvent e, String defaultCatergory) {
		super(e, defaultCatergory);
	}

	@Override
	public void initialize(Minecraft minecraftInstance) {
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public boolean hasConfigGui() {
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		List<IConfigElement> elements = new ArrayList<>();
		getConfig().getCategoryNames().forEach(cat -> {
			elements.addAll(new ConfigElement(getConfig().getCategory(cat)).getChildElements());
		});
		return new GuiConfig(parentScreen, elements, getModid(), false, false, getTitle());
	}

	public String getTitle() {
		return getModid();
	}
}
