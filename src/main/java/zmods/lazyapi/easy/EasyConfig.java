package zmods.lazyapi.easy;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


/**
 * When using this to create your mod config, don't forget to add the
 * {@link GUIFACTORY} reference string to you mod instance
 */
public class EasyConfig extends Configuration {
	public static final String GUIFACTORY = "zmods.lazyapi.api.EasyConfig.Gui";
	
	private static final NonNullList<EasyConfig> CONFIGLST = NonNullList.create();
	private static Map<String, ConfigCategory> CategoryList  = new HashMap<>();

	private String modId;
	private String title = "";
	private String title2 = "";
	
	public EasyConfig(FMLPreInitializationEvent e) {
		this(e.getSuggestedConfigurationFile(), Loader.instance().activeModContainer().getModId());
	}
	
	public EasyConfig(File file, String modId) {
		super(file);
		this.modId = modId;
	}
	
	public List<IConfigElement> getElements() {
		return null;
	}
	
	public EasyConfig setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public EasyConfig setTitle2(String title2) {
		this.title2 = title2;
		return this;
	}

	public String getTitleLine2() {
		return this.title2.equals("") ? this.getConfigFile().getAbsolutePath() : this.title2;
	}

	public String getTitle() {
		return this.title.equals("") ? this.modId : this.title;
	}

	public boolean allRequireMCRestart() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean allRequireWorldRestart() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getModID() {
		return this.modId;
	}
	
	public EasyConfig addCatergory(@Nonnull String cat) {
		return this.addCatergory(cat, null);
	}
	
	public EasyConfig addCatergory(@Nonnull String cat, ConfigCategory parent) {
		this.CategoryList.put(cat, new ConfigCategory(cat, parent));
		return this;
	}
	
	@Override
	public void save() {
		
		if(hasChanged())
			super.save();
	}
	
	@Override
	public void load() {
		super.load();
	}
	
	public static void syncConfigs() {
		CONFIGLST.forEach(config -> config.save());
	}
	
	public static class GuiFactory implements IModGuiFactory {

		@Override
		public void initialize(Minecraft minecraftInstance) {
			
		}

		@Override
		public Class<? extends GuiScreen> mainConfigGuiClass() {
			return Gui.class;
		}

		@Override
		public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
			return null;
		}

		@Override
		public boolean hasConfigGui() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public GuiScreen createConfigGui(GuiScreen parentScreen) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public static class Gui extends GuiConfig {
		
		public Gui(GuiScreen parentScreen, EasyConfig config) {
			super(parentScreen, config.getElements(), config.getModID(), GuiConfig.getAbridgedConfigPath(config.toString()), 
					config.allRequireWorldRestart(), config.allRequireMCRestart(), config.getTitle(), config.getTitleLine2());
		}

		public Gui(GuiScreen parentScreen, List<IConfigElement> configElements, String modID, String configID,
				boolean allRequireWorldRestart, boolean allRequireMcRestart, String title, String titleLine2) {
			
			super(parentScreen, configElements, modID, configID, allRequireWorldRestart, allRequireMcRestart, title, titleLine2);
		}
		
	}

}
