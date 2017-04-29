package zdoctor.lazymodder.easy;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.ValueType;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import zdoctor.lazymodder.events.RuleChangedEvent;

public class EasyFunctions {
	public static final Map<String, Entry<String, GameRules.ValueType>> customGameRules = new HashMap<>();

	public static void addRecipe(ItemStack itemStack, Object... components) {
		GameRegistry.addRecipe(itemStack, components);
	}

	public static void addShapedRecipe(ItemStack itemStack, Object... components) {
		GameRegistry.addShapedRecipe(itemStack, components);
	}

	public static void addShapelessRecipe(ItemStack itemStack, Object... components) {
		GameRegistry.addShapelessRecipe(itemStack, components);
	}

	public static <K extends IForgeRegistryEntry<?>> K register(K object) {
		return GameRegistry.register(object);
	}

	public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
		GameRegistry.registerTileEntity(tileEntityClass, id);
	}

	public static void addPotionEffect(EntityLivingBase entity, PotionEffect effect) {
		entity.addPotionEffect(effect);
	}

	public static void addPotionEffectWithChance(EntityLivingBase entity, World world, PotionEffect effect,
			Float chance) {
		if (world.rand.nextFloat() < chance) {
			addPotionEffect(entity, effect);
		}
	}

	/**
	 * This class handles changing existing game rules and adding new ones. See
	 * {@link RuleChangedEvent} to prevent some changes in the rules
	 * 
	 */
	public static void modifyGameRule(@Nonnull String key, @Nonnull String ruleValue,
			@Nonnull GameRules.ValueType type) {
		customGameRules.put(key, new SimpleEntry(ruleValue, type));
	}

	public static void modifyGameRule(VanillaRules vanillaRule, boolean bool) {
		if (vanillaRule.getRuleType() == ValueType.BOOLEAN_VALUE)
			customGameRules.put(vanillaRule.getRuleName(),
					new SimpleEntry((bool ? "true" : "false"), ValueType.BOOLEAN_VALUE));
	}

	public static void modifyGameRule(VanillaRules vanillaRule, int i) {
		if (vanillaRule.getRuleType() == ValueType.NUMERICAL_VALUE)
			customGameRules.put(vanillaRule.getRuleName(),
					new SimpleEntry(Integer.toString(i), ValueType.NUMERICAL_VALUE));
	}

	public static enum VanillaRules {
		FIRETICK("doFireTick", GameRules.ValueType.BOOLEAN_VALUE),
		MOBGRIEF("mobGriefing", GameRules.ValueType.BOOLEAN_VALUE),
		KEEPINVENTORY("keepInventory", GameRules.ValueType.BOOLEAN_VALUE),
		MOBSPAWNING("doMobSpawning", GameRules.ValueType.BOOLEAN_VALUE),
		MOBLOOT("doMobLoot", GameRules.ValueType.BOOLEAN_VALUE),
		TILEDROPS("doTileDrops", GameRules.ValueType.BOOLEAN_VALUE),
		ENTITYDROPS("doEntityDrops", GameRules.ValueType.BOOLEAN_VALUE),
		COMMANDBLOCKOUTPUT("commandBlockOutput", GameRules.ValueType.BOOLEAN_VALUE),
		NATURALREGEN("naturalRegeneration", GameRules.ValueType.BOOLEAN_VALUE),
		DAYCYCLE("doDaylightCycle", GameRules.ValueType.BOOLEAN_VALUE),
		LOGADMINCOMMANDS("logAdminCommands", GameRules.ValueType.BOOLEAN_VALUE),
		SHOWDEATHMESSAGES("showDeathMessages", GameRules.ValueType.BOOLEAN_VALUE),
		RANDOMTICKSPEED("randomTickSpeed", GameRules.ValueType.NUMERICAL_VALUE),
		COMMANDFEEDBACK("sendCommandFeedback", GameRules.ValueType.BOOLEAN_VALUE),
		MINDEBUGINFO("reducedDebugInfo", GameRules.ValueType.BOOLEAN_VALUE),
		SPECTATORSGENERATECHUNKS("spectatorsGenerateChunks", GameRules.ValueType.BOOLEAN_VALUE),
		SPAWNRADIUS("spawnRadius", GameRules.ValueType.NUMERICAL_VALUE),
		DISABLEELYTRAMOVEMENTCHECK("disableElytraMovementCheck", GameRules.ValueType.NUMERICAL_VALUE),
		MAXENTITYCRAMMING("maxEntityCramming", GameRules.ValueType.NUMERICAL_VALUE),
		WEATHERCYCLE("doWeatherCycle", GameRules.ValueType.BOOLEAN_VALUE);

		private final String ruleName;
		private final ValueType ruleType;

		private VanillaRules(String ruleName, ValueType ruleType) {
			this.ruleName = ruleName;
			this.ruleType = ruleType;
		}

		public String getRuleName() {
			return this.ruleName;
		}

		public ValueType getRuleType() {
			return this.ruleType;
		}
	}

}
