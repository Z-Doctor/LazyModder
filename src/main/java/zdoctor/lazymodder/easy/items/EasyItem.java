package zdoctor.lazymodder.easy.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import zdoctor.lazymodder.interfaces.INoModel;
import zdoctor.lazymodder.registery.ItemRegistry;

/**
 * @author Z_Doctor
 * 
 *         Base class for items to be put into the game by the library. Extend
 *         this class for added control of the item.
 *
 */
public class EasyItem extends Item implements IEasyItem {
	public static final IItemPropertyGetter META_GETTER = new IItemPropertyGetter() {

		@Override
		public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
			return stack.getMetadata();
		}
	};
	int subCount = 1;

	/**
	 * 
	 * @param name
	 *            - the name of the item
	 */
	public EasyItem(String name) {
		this(name, 1);
	}

	/**
	 * Items will be automatically registered along with their json files. Items
	 * created this way will be prompt if the json is missing and offer to
	 * create a default json file in dev mode. Implement {@link INoModel} to
	 * disable this behavior.
	 * 
	 * @param name
	 *            - the name of the item
	 * @param subTypeCount
	 *            - The number of subitems
	 */
	public EasyItem(String name, int subTypeCount) {
		setUnlocalizedName(name);
		setSubCount(subTypeCount);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MISC);
		setMaxStackSize(64);
		addPropertyOverride(new ResourceLocation("meta"), META_GETTER);
		ItemRegistry.register(this);
	}

	public EasyItem noStack() {
		this.setMaxStackSize(1);
		return this;
	}

	private EasyItem setSubCount(int i) {
		this.subCount = i;
		if (i > 1)
			setHasSubtypes(true);
		return this;
	}

	public int getSubCount() {
		return this.hasSubtypes ? this.subCount : 1;
	}

	/**
	 * This handles the subitems that are also made. Override this if you want
	 * to change what goes in the creative tab.
	 */
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (this.isInCreativeTab(tab))
			for (int i = 0; i < this.getSubCount(); i++)
				subItems.add(new ItemStack(this, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return "item." + this.getNameFromMeta(par1ItemStack.getItemDamage()).toLowerCase();
	}

	@Override
	public String getNameFromMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	@Override
	public String getRegistryNameForMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	public static class ContainerItem extends EasyItem {
		public static final int UNBREAKING = Short.MIN_VALUE;

		public ContainerItem(String name, int uses) {
			super(name);
			this.noStack();
			if (uses == UNBREAKING)
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
