package zdoctor.lazymodder.easy.crafting;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.FMLLog;

public abstract class IngredientList {
//	public static final int UIDStart = 97;
//	protected int UID = 0;
//	protected Map<Item, Character> ingredientMap = new HashMap<>();
	
	protected NonNullList<Ingredient> ingredientList;
	private ItemStack result;
	private int width;
	private int height;
	
	public IngredientList(ItemStack result) {
		this.result = result;
	}
	
	public IngredientList(int width, int height, ItemStack result) {
		this.width = width;
		this.height = height;
		this.result = result;
	}
	
	/**
	 * Used to create ingredient lists in a grid pattern. Pattern
	 * goes left to right, up to down starting from top-left most
	 * square and continues for the width.
	 */
	public static class ShapedList extends IngredientList {
		public ShapedList(ItemStack result) {
			this(3, 3, result);
		}
		
		public ShapedList(int width, int height, ItemStack result) {
			super(width, height, result);
			ingredientList = NonNullList.withSize(height * width, Ingredient.EMPTY);
		}
		
		public boolean addIngredient(int x, int y, Item item) {
			return addIngredient(x, y, Ingredient.fromItem(item));
		}
		
		public boolean addIngredient(int x, int y, ItemStack stack) {
			return addIngredient(x, y, Ingredient.fromStacks(stack));
		}
		
		public boolean addIngredient(int x, int y, Ingredient ingredient) {
			try {
				ingredientList.set(getWidth() * (y - 1) + (x - 1), ingredient);
				return true;
			} catch (ArrayIndexOutOfBoundsException e) {
				FMLLog.log.catching(Level.FATAL, e);
				return false;
			}
		}

		@Override
		public boolean isShaped() {
			return true;
		}
	}
	
	public static class ShapelessList extends IngredientList {

		public ShapelessList(ItemStack result) {
			super(result);
			ingredientList = NonNullList.create();
		}
		
		public boolean addIngredient(Item item) {
			ingredientList.add(Ingredient.fromItem(item));
			return true;
		}
		
		public boolean addIngredient(ItemStack stack) {
			ingredientList.add(Ingredient.fromStacks(stack));
			return true;
		}

		@Override
		public boolean isShaped() {
			return false;
		}
		
	}
	
	public abstract boolean isShaped();

	public NonNullList<Ingredient> asList() {
		Ingredient listCopy[] = new Ingredient[ingredientList.size()];
		return NonNullList.from(null, ingredientList.toArray(listCopy));
	}

	public ItemStack getResult() {
		return result;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
