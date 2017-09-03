package zdoctor.lazymodder.easy.items;

import com.google.common.collect.Multimap;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

public class EasyItemHoe extends ItemHoe implements IEasyRegister {
	protected int subCount;
	protected float speed;

	public EasyItemHoe(String name, Item.ToolMaterial material) {
		this(name, 1, material);
	}

	public EasyItemHoe(String name, int subCount, Item.ToolMaterial material) {
		super(material);
		this.subCount = 1;
		this.speed = material.getDamageVsEntity() + 1.0F;
		setRegistryName(name);
		setUnlocalizedName(name);
		EasyRegistry.register(this);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
					new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0D, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
					new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double) (getSpeed() - 4.0F), 0));
		}

		return multimap;
	}

	private float getSpeed() {
		return speed;
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (isInCreativeTab(tab))
			for (int i = 0; i < this.getSubCount(); i++)
				subItems.add(new ItemStack(this, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return "item." + this.getNameFromMeta(itemStack.getMetadata()).toLowerCase();
	}

	@Override
	public String getNameFromMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	@Override
	public String getRegistryNameForMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	@Override
	public int getSubCount() {
		return subCount;
	}

}
