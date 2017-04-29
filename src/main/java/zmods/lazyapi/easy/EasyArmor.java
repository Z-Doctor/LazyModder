package zmods.lazyapi.easy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;

public class EasyArmor extends ItemArmor {
	public EasyArmor(String armorName, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(armorName);
		this.setRegistryName(armorName);
		this.setCreativeTab(CreativeTabs.COMBAT);
	}
	
	public static Map<EntityEquipmentSlot, ItemArmor> createArmorSet(String armorName, ArmorMaterial materialIn) {
		Map<EntityEquipmentSlot, ItemArmor> armorSet = new HashMap<>();
		armorSet.put(EntityEquipmentSlot.HEAD, new EasyArmor(armorName + "_helmet", materialIn, 1, EntityEquipmentSlot.HEAD));
		armorSet.put(EntityEquipmentSlot.CHEST, new EasyArmor(armorName + "_chestplate", materialIn, 1, EntityEquipmentSlot.CHEST));
		armorSet.put(EntityEquipmentSlot.LEGS, new EasyArmor(armorName + "_leggings", materialIn, 2, EntityEquipmentSlot.LEGS));
		armorSet.put(EntityEquipmentSlot.FEET, new EasyArmor(armorName + "_boots", materialIn, 1, EntityEquipmentSlot.FEET));
		for (ItemArmor item : armorSet.values()) {
			EasyFunctions.register(item);
		}
		return armorSet;
	}

	public static ArmorMaterial addMaterial(String materialName, String textureName, int durability,
			int[] reductionAmounts, int enchantability, SoundEvent soundOnEquip, float toughness) {

		return EnumHelper.addArmorMaterial(materialName, textureName, durability, reductionAmounts, enchantability,
				soundOnEquip, toughness);
	}
}
