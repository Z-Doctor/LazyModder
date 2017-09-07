package zdoctor.lazymodder.easy.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

/**
 * When you extend this class, make sure to add an empty constructor
 * 
 */
public class EasyConfigGui extends EasyConfig implements IModGuiFactory {
	public EasyConfig getEasyConfig() {
		return this;
	};

	public EasyConfigGui(FMLPreInitializationEvent e) {
		this(e, "Default");
	}

	public EasyConfigGui(FMLPreInitializationEvent e, String defaultCatergory) {
		super(e, defaultCatergory);
		EasyRegistry.register(this);
	}

	protected void modifyGuiFactory(ModContainer container) {
		Field[] fields = container.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			// System.out.println("Type: " + fields[i].getType().toString());
			if (fields[i].getType().toString().equals("interface java.util.Map")) {
				System.out.println("Field: " + fields[i].getName());
				fields[i].setAccessible(true);
				try {
					Map<String, Object> map = (Map<String, Object>) fields[i].get(container);
					Builder<String, Object> map2 = ImmutableMap.builder();
					map.forEach((key, value) -> {
						System.out.println(key + "=" + value);
						if (!key.equals("guiFactory"))
							map2.put(key, value);
					});
					map2.put("guiFactory", "zdoctor.lazymodder.easy.config.EasyConfigGui");
					fields[i].set(container, map2.build());
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}
			}
			// System.out.println("Field: " + fields[i].get());
		}
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
