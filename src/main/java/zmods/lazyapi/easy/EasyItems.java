package zmods.lazyapi.easy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class EasyItems extends Item {
	int subCount = 1;

	public EasyItems(String name) {
		this(name, false);
	}
	
	public EasyItems(String name, boolean hasSubTypes) {
		setUnlocalizedName(name);
		setHasSubtypes(hasSubTypes);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MISC);
		setMaxStackSize(64);
		EasyFunctions.register(this);
	}

	public EasyItems noStack() {
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
	public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if(this.hasSubtypes)
			for(int i = 0; i < this.getSubCount(); i++)
				subItems.add(new ItemStack(itemIn, 1, i));
		else
			super.getSubItems(itemIn, tab, subItems);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		if(this.hasSubtypes)
			return "item." + this.getNameFromDamage(par1ItemStack.getItemDamage()).toLowerCase();
		return super.getUnlocalizedName().toLowerCase();
	}

	public String getNameFromDamage(int itemDamage) {
		return getRegistryName().getResourcePath();
	}

	public static class ContainerItem extends EasyItems {
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
			if (itemStack.attemptDamageItem(1, itemRand)) {
				return ItemStack.EMPTY;
			}
			return itemStack.copy();
		}

	}

}
