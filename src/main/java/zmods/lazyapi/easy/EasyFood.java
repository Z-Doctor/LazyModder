package zmods.lazyapi.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
/**
 * Some common values are:
 * Food Hunger Points Saturation
 * Apple - 4 - 0.3f <p>
 * Bread - 5 -0.6f <p>
 * Raw Porkchop/Beef - 3 - 0.3f <p>
 * Cooked Porkchop/Beef - 8 - 0.8f <p>
 * Golden Apple - 4 - 1.2f <p>
 */
public class EasyFood extends ItemFood {
	ArrayList<PotionEffect> effectsOnEaten = new ArrayList<>();
	Map<PotionEffect, Float> chanceOnEaten = new HashMap<>();

	public EasyFood(String name, int amount) {
		this(name, amount, 0.6F);
	}

	public EasyFood(String name, int amount, float saturation) {
		this(name, amount, saturation, false);
	}

	public EasyFood(String name, int amount, boolean isWolfFood) {
		this(name, amount, 0.6F, isWolfFood);
	}
	
	/**
	 * @param name The name of the food
	 * @param amount How many hunger points are rewarded for eating this item
	 * @param saturation How many points go into the saturation bar (a secondary bar that destermines how fast hunger points drain)
	 * @param isWolfFood Does a wolf it it.
	 */
	public EasyFood(String name, int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(CreativeTabs.FOOD);
		this.setMaxStackSize(64);
		EasyFunctions.register(this);
	}
	
	
	public EasyFood addPotionEffect(PotionEffect effect) {
		effectsOnEaten.add(effect);
		return this;
	}
	
	public EasyFood addEffectWithChance(PotionEffect effect, float probability) {
		this.chanceOnEaten.put(effect, probability);
		return this;
	}
	

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		if (!world.isRemote) {
			for (PotionEffect effect : this.effectsOnEaten) {
				EasyFunctions.addPotionEffect(player, effect);
			}
			for (PotionEffect effect : chanceOnEaten.keySet()) {
				EasyFunctions.addPotionEffectWithChance(player, world, effect, chanceOnEaten.get(effect));
			}
		}
	}

	
	public static class EasyDrink extends EasyFood {
		public EasyDrink(String name, int amount) {
			this(name, amount, 0.6F);
		}

		public EasyDrink(String name, int amount, float saturation) {
			this(name, amount, saturation, false);
		}

		public EasyDrink(String name, int amount, boolean isWolfFood) {
			this(name, amount, 0.6F, isWolfFood);
		}
		
		public EasyDrink(String name, int amount, float saturation, boolean isWolfFood) {
			super(name, amount, saturation, isWolfFood);
		}
		
		@Override
		public EnumAction getItemUseAction(ItemStack stack) {
			return EnumAction.DRINK;
		}
	}
}
