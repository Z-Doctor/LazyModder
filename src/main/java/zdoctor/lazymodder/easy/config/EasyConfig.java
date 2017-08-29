package zdoctor.lazymodder.easy.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Call in your preInit
 */
public class EasyConfig {
	private static final Map<String, EasyConfig> CONFIG_REGISTRY = new HashMap<>();
	protected final Map<String, Property> properties = new HashMap<>();
	protected final List<Entry> entries = new ArrayList<>();
	protected String modid;
	protected Configuration config;
	protected String defaultCatergory;

	public EasyConfig() {
	}

	public EasyConfig(FMLPreInitializationEvent e) {
		this(e, "Default");
	}

	public EasyConfig(FMLPreInitializationEvent e, String defaultCatergory) {
		this.defaultCatergory = defaultCatergory;
		modid = Loader.instance().activeModContainer().getModId();
		config = new Configuration(e.getSuggestedConfigurationFile());
		open();
		CONFIG_REGISTRY.put(modid, this);
	}

	public EasyConfig registerInt(String name, int defaultValue, String... optional) {
		return this.registerInt(defaultCatergory, name, defaultValue, Integer.MIN_VALUE, Integer.MAX_VALUE, optional);
	}

	public EasyConfig registerInt(String name, int defaultValue, int minValue, int maxValue, String... optional) {
		return this.registerInt(defaultCatergory, name, defaultValue, minValue, maxValue, optional);
	}

	/**
	 * 
	 * @param category
	 * @param name
	 * @param defaultValue
	 * @param minValue
	 * @param maxValue
	 * @param optional
	 *            - set the comment and langkey (for formating) lank key is
	 *            optional, but you need to add a comment to add a lang key
	 * @return
	 */
	public EasyConfig registerInt(String category, String name, int defaultValue, int minValue, int maxValue,
			String... optional) {
		Entry intEntry = new Entry(category, name, defaultValue, minValue, maxValue, optional);
		int value = config.getInt(name, category, defaultValue, minValue, maxValue,
				optional.length > 0 ? optional[0] : "", optional.length > 1 ? optional[1] : name);
		Property prop = new Property(category, name, value);
		properties.put(name, prop);
		entries.add(intEntry);
		return this;
	}

	public int getInt(String name) {
		return Integer.valueOf(properties.get(name).getInt());
	}

	public EasyConfig registerBoolean(String name, boolean defaultValue, String... optional) {
		return this.registerBoolean(defaultCatergory, name, defaultValue, optional);
	}

	public EasyConfig registerBoolean(String category, String name, boolean defaultValue, String... optional) {
		Entry booleanEntry = new Entry(category, name, defaultValue, optional);
		boolean value = config.getBoolean(name, category, defaultValue, optional.length > 0 ? optional[0] : "",
				optional.length > 1 ? optional[1] : name);
		Property prop = new Property(category, name, value);
		properties.put(name, prop);
		entries.add(booleanEntry);
		return this;
	}

	public boolean getBoolean(String name) {
		return Boolean.valueOf(properties.get(name).getBoolean());
	}

	public Configuration getConfig() {
		return config;
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		System.out.println(event.getModID() + " config changed");
		if (CONFIG_REGISTRY.get(event.getModID()) != null) {
			System.out.println(event.getModID() + " config saved");
			CONFIG_REGISTRY.get(event.getModID()).sync();
		}
	}

	public void open() {
		config.load();
	}

	public void close() {
		config.save();
	}

	public void sync() {
		config.save();
		System.out.println("Saving");
		entries.forEach(entry -> {
			System.out.println(entry.getName());
			if(entry.getType() == Entry.Type.BOOLEAN) {
				boolean value = config.getBoolean(entry.getName(), entry.getCategory(), entry.getDefaultBoolValue(), entry.getOptional().length > 0 ? entry.getOptional()[0] : "",
						entry.getOptional().length > 1 ? entry.getOptional()[1] : entry.getName());
				Property prop = new Property(entry.getCategory(), entry.getName(), value);
				properties.put(entry.getName(), prop);
				System.out.println("New: " + entry.getName() + " - " + value);
			} else if(entry.getType() == Entry.Type.INT) {
				int value = config.getInt(entry.getName(), entry.getCategory(), entry.getDefaultIntValue(), entry.getMinValue(), entry.getMaxValue(), entry.getOptional().length > 0 ? entry.getOptional()[0] : "",
						entry.getOptional().length > 1 ? entry.getOptional()[1] : entry.getName());
				Property prop = new Property(entry.getCategory(), entry.getName(), value);
				properties.put(entry.getName(), prop);
			}
		});
	}

	public String getModid() {
		return modid;
	}

	public static class Property {

		private String category;
		private String name;
		private Object value;

		public Property(String category, String name, Object value) {
			this.category = category;
			this.name = name;
			this.value = value;
		}

		public boolean getBoolean() {
			return (boolean) value;
		}

		public int getInt() {
			return (int) value;
		}

		public String getName() {
			return name;
		}

		public String getCategory() {
			return category;
		}

	}

	public static class Entry {

		private String category;
		private String name;
		private boolean defaultBoolValue;
		private String[] optional;
		private Type type;
		private int defaultIntValue;
		private int minValue;
		private int maxValue;

		public Entry(String category, String name, boolean defaultValue, String... optional) {
			this.category = category;
			this.name = name;
			this.defaultBoolValue = defaultValue;
			this.optional = optional;
			this.type = Type.BOOLEAN;
		}

		public Entry(String category, String name, int defaultValue, int minValue, int maxValue, String... optional) {
			this.category = category;
			this.name = name;
			this.defaultIntValue = defaultValue;
			this.minValue = minValue;
			this.maxValue = maxValue;
			this.optional = optional;
			this.type = Type.INT;
		}
		
		public Type getType() {
			return type;
		}
		
		public String getCategory() {
			return category;
		}

		public String getName() {
			return name;
		}

		public boolean getDefaultBoolValue() {
			return defaultBoolValue;
		}

		public String[] getOptional() {
			return optional;
		}

		public int getDefaultIntValue() {
			return defaultIntValue;
		}

		public int getMinValue() {
			return minValue;
		}

		public int getMaxValue() {
			return maxValue;
		}



		public static enum Type {
			INT, BOOLEAN;
		}
	}
}