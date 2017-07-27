package zdoctor.lazymodder.client;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import net.minecraft.world.GameRules.ValueType;
import zdoctor.lazymodder.events.RuleChangedEvent;

public class GameRules {
	public static final Map<String, Entry<String, net.minecraft.world.GameRules.ValueType>> customGameRules = new HashMap<>();

	/**
	 * This class handles changing existing game rules and adding new ones. See
	 * {@link RuleChangedEvent} to prevent some changes in the rules
	 * 
	 */
	public static void modifyGameRule(@Nonnull String key, @Nonnull String ruleValue,
			@Nonnull net.minecraft.world.GameRules.ValueType type) {
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
		FIRETICK("doFireTick", net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), MOBGRIEF("mobGriefing",
				net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), KEEPINVENTORY("keepInventory",
						net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), MOBSPAWNING("doMobSpawning",
								net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), MOBLOOT("doMobLoot",
										net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), TILEDROPS("doTileDrops",
												net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), ENTITYDROPS(
														"doEntityDrops",
														net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), COMMANDBLOCKOUTPUT(
																"commandBlockOutput",
																net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), NATURALREGEN(
																		"naturalRegeneration",
																		net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), DAYCYCLE(
																				"doDaylightCycle",
																				net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), LOGADMINCOMMANDS(
																						"logAdminCommands",
																						net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), SHOWDEATHMESSAGES(
																								"showDeathMessages",
																								net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), RANDOMTICKSPEED(
																										"randomTickSpeed",
																										net.minecraft.world.GameRules.ValueType.NUMERICAL_VALUE), COMMANDFEEDBACK(
																												"sendCommandFeedback",
																												net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), MINDEBUGINFO(
																														"reducedDebugInfo",
																														net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), SPECTATORSGENERATECHUNKS(
																																"spectatorsGenerateChunks",
																																net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE), SPAWNRADIUS(
																																		"spawnRadius",
																																		net.minecraft.world.GameRules.ValueType.NUMERICAL_VALUE), DISABLEELYTRAMOVEMENTCHECK(
																																				"disableElytraMovementCheck",
																																				net.minecraft.world.GameRules.ValueType.NUMERICAL_VALUE), MAXENTITYCRAMMING(
																																						"maxEntityCramming",
																																						net.minecraft.world.GameRules.ValueType.NUMERICAL_VALUE), WEATHERCYCLE(
																																								"doWeatherCycle",
																																								net.minecraft.world.GameRules.ValueType.BOOLEAN_VALUE);

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
