package zmods.lazyapi.events;

import javax.annotation.Nonnull;

import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.ValueType;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * These only register changes made using this mods methods
 * Note that rule changes that other mods make independently will not be registered.
 * See {@link GameRules} for all default game rules.
 */
@Cancelable
public class RuleChangedEvent extends Event {
	private final String key;
	private final String ruleValue;
	private final String currentRule;
	private final ValueType valueType;

	public RuleChangedEvent(@Nonnull String key, @Nonnull String ruleValue, ValueType valueType, @Nonnull String currentRule) {
		this.key = key;
		this.ruleValue = ruleValue;
		this.valueType = valueType;
		this.currentRule = currentRule;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public String getRuleValue() {
		return this.ruleValue;
	}
	
	public ValueType getValueType() {
		return this.valueType;
	}
	
	public String getCurrentRule() {
		return this.currentRule;
	}
}
