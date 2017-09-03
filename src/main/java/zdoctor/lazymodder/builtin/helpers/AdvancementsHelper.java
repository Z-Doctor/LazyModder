package zdoctor.lazymodder.builtin.helpers;

import zdoctor.lazymodder.easy.registry.EasyRegistry;

/**
 * http://www.minecraftforum.net/forums/minecraft-discussion/redstone-discussion-and/commands-command-blocks-and/2809368-1-12-custom-advancements-aka-achievements
 *
 */
public class AdvancementsHelper {
	public static final String FOLDERS = "data/advancements/";

	public static class Builder {
		private String namespace;
		private String advancementName;

		public Builder(String advancementName) {
			this(EasyRegistry.getActiveMod().getModId(), advancementName);
		}

		public Builder(String namespace, String advancementName) {
			this.namespace = namespace;
			this.advancementName = advancementName;
		}
	}

	public void open(StringBuilder sb) {
		sb.append("{");
	}

	public void close(StringBuilder sb) {
		sb.append("}");
	}

	public void tab(StringBuilder sb) {
		sb.append("\t");
	}

	public void newLine(StringBuilder sb) {
		sb.append("\n");
	}

	public static enum AdvancementsKey {
		CRITERIA("criteria"), REQUIREMENTS("requirements"), PARENT("parent"), DISPLAY("display"), REWARDS("rewards");

		private String key;

		private AdvancementsKey(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

	}

	// public static void Triggers {
	// Minecraft.getMinecraft().fontRenderer
	// }

	// {
	// "criteria": {
	// "custom_trigger_name": {
	// "trigger": "namespace:trigger_name",
	// "conditions": {
	// "durability": 1,
	// "durability": {"min":1,"max":1},
	// "delta": 1,
	// "delta": {"min":1,"max":1},
	// "slots": {
	// "occupied": 1,
	// "occupied": {"min":1,"max":1},
	// "full": 1,
	// "full": {"min":1,"max":1},
	// "empty": 1,
	// "empty": {"min":1,"max":1}
	// },
	// "items": [
	// {
	// "item": "minecraft:stone",
	// "count": 1,
	// "count": {"min":1,"max":1},
	// "data": 1,
	// "data": {"min":1,"max":1},
	// "durability": 1,
	// "durability": {"min":1,"max":1},
	// "potion": "minecraft:invisibility",
	// "enchantments": [
	// {
	// "enchantment": "minecraft:sharpness",
	// "levels": 1,
	// "levels": {"min":1,"max":1}
	// }
	// ]
	// }
	// ],
	// "item": {
	// "item": "minecraft:stone",
	// "count": 1,
	// "count": {"min":1,"max":1},
	// "data": 1,
	// "data": {"min":1,"max":1},
	// "durability": 1,
	// "durability": {"min":1,"max":1},
	// "potion": "minecraft:invisibility",
	// "enchantments": [
	// {
	// "enchantment": "minecraft:sharpness",
	// "levels": 1,
	// "levels": {"min":1,"max":1}
	// }
	// ]
	// },
	// "levels": 1,
	// "levels": {"min":1,"max":1},
	// "recipe": "minecraft:chest",
	// "position": {
	// "x": 1,
	// "x": {"min":1,"max":1},
	// "y": 1,
	// "y": {"min":1,"max":1},
	// "z": 1,
	// "z": {"min":1,"max":1}
	// },
	// "biome": "minecraft:void",
	// "feature": "EndCity",
	// "dimension": "overworld",
	// "from": "overworld",
	// "to": "overworld",
	// "block": "minecraft:stone",
	// "state": {
	// "state_name": "state_value"
	// },
	// "entity": {
	// "type": "minecraft:creeper",
	// "distance": 1,
	// "distance": {"min":1,"max":1}
	// },
	// "killing_blow": {
	// "bypasses_armor": true,
	// "bypasses_invulnerability": true,
	// "bypasses_magic": true,
	// "is_explosion": true,
	// "is_fire": true,
	// "is_magic": true,
	// "is_projectile": true,
	// "direct_entity": {
	// "type": "minecraft:creeper",
	// "distance": 1,
	// "distance": {"min":1,"max":1}
	// },
	// "source_entity": {
	// "type": "minecraft:creeper",
	// "distance": 1,
	// "distance": {"min":1,"max":1}
	// }
	// },
	// "distance": 1,
	// "distance": {"min":1,"max":1},
	// "parent": {
	// "type": "minecraft:creeper",
	// "distance": 1,
	// "distance": {"min":1,"max":1}
	// },
	// "partner": {
	// "type": "minecraft:creeper",
	// "distance": 1,
	// "distance": {"min":1,"max":1}
	// }
	// "child": {
	// "type": "minecraft:creeper",
	// "distance": 1,
	// "distance": {"min":1,"max":1}
	// },
	// "potion": "minecraft:invisibility",
	// "level": 1,
	// "level": {"min":1,"max":1},
	// "damage": {
	// "dealt": 1,
	// "dealt": {"min":1,"max":1},
	// "taken": 1,
	// "taken": {"min":1,"max":1},
	// "blocked": true,
	// "type": {
	// "bypasses_armor": true,
	// "bypasses_invulnerability": true,
	// "bypasses_magic": true,
	// "is_explosion": true,
	// "is_fire": true,
	// "is_magic": true,
	// "is_projectile": true,
	// "direct_entity": {
	// "type": "minecraft:creeper",
	// "distance": 1,
	// "distance": {"min":1,"max":1}
	// },
	// "source_entity": {
	// "type": "minecraft:creeper",
	// "distance": 1,
	// "distance": {"min":1,"max":1}
	// }
	// },
	// "source_entity": {
	// "type": "minecraft:creeper",
	// "distance": 1,
	// "distance": {"min":1,"max":1}
	// }
	// }
	// }
	// }
	// },
	// "requirements": [["generic_trigger_name"]],
	// "parent": "namespace:path/to/parent_advancement",
	// "display": {
	// "icon": {
	// "item": "minecraft:stone_pickaxe",
	// "data": 0
	// },
	// "title": "Display title",
	// "description": "Display description",
	// "frame": "task",
	// "background": "minecraft:path/to/texture.png"
	// },
	// "rewards": {
	// "recipes": ["namespace:path/to/recipe"],
	// "loot": ["namespace:path/to/loot_table"],
	// "experience": 1,
	// "commands": ["/say Command to run upon completion"]
	// }
	// }
}
