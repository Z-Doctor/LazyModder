package zdoctor.lazymodder.easy;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.ValueType;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.IForgeRegistryEntry;
import zdoctor.lazymodder.events.RuleChangedEvent;

public class EasyFunctions {

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

}
