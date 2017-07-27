package zdoctor.lazymodder.easy.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import zdoctor.lazymodder.registery.ItemRegistry;

public class EasyItem extends Item {
	int subCount = 1;

	public EasyItem(String name) {
		this(name, false);
	}
	
	public EasyItem(String name, boolean hasSubTypes) {
		setUnlocalizedName(name);
		setHasSubtypes(hasSubTypes);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MISC);
		setMaxStackSize(64);
		ItemRegistry.register(this);
	}

	public EasyItem noStack() {
		this.setMaxStackSize(1);
		return this;
	}
	
	public void setSubCount(int i) {
		this.subCount = i;
	}
	
	public int getSubCount() {
		return this.hasSubtypes ? this.subCount : 1;
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if(this.hasSubtypes)
			for(int i = 0; i < this.getSubCount(); i++)
				subItems.add(new ItemStack(this, 1, i));
		else
			super.getSubItems(tab, subItems);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return "item." + this.getNameFromMeta(par1ItemStack.getItemDamage()).toLowerCase();
	}

	public String getNameFromMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	public static class ContainerItem extends EasyItem {
		public static final int UNBREAKING = Short.MIN_VALUE;
		public ContainerItem(String name, int uses) {
			super(name);
			this.noStack();
			if(uses == UNBREAKING)
				this.setContainerItem(this);
			else
				this.setMaxDamage(uses);
		}
		
		@Override
		public boolean hasContainerItem(ItemStack stack) {
			return true;
		}
		
		@Override
		public Item getContainerItem() {
			return Items.DIAMOND;
		}

		@Override
		public ItemStack getContainerItem(ItemStack itemStack) {
			if (itemStack.attemptDamageItem(1, itemRand, null)) {
				return ItemStack.EMPTY;
			}
			return itemStack.copy();
		}

	}

}
