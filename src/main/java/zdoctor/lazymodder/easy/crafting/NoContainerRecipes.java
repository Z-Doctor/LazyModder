package zdoctor.lazymodder.easy.crafting;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zdoctor.lazymodder.easy.crafting.EasyRecipe.Groups;

public interface NoContainerRecipes extends EasyRecipe {

	public static class ShapedRecipe extends ShapedRecipes implements NoContainerRecipes {

		private ItemStack container = ItemStack.EMPTY;

		public ShapedRecipe(IngredientList ingredientList) {
			this(Groups.NONE, ingredientList.asList(), ingredientList.getResult());
		}

		public ShapedRecipe(String groupId, IngredientList ingredientList) {
			this(groupId, ingredientList.asList(), ingredientList.getResult());
		}

		public ShapedRecipe(NonNullList<Ingredient> ingredients, ItemStack result) {
			this(Groups.NONE, ingredients, result);
		}

		public ShapedRecipe(String group, NonNullList<Ingredient> ingredients, ItemStack result) {
			super(group, 3, 3, ingredients, result);
		}

		public ShapedRecipe(String group, int width, int height, NonNullList<Ingredient> ingredients,
				ItemStack output) {
			super(group, width, height, ingredients, output);
		}

		@Override
		public ShapedRecipe setCustomContainer(ItemStack stack) {
			this.container = stack;
			return this;
		}

		@Override
		public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
			NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>withSize(inv.getSizeInventory(),
					ItemStack.EMPTY);

			for (int i = 0; i < nonnulllist.size(); ++i) {
				ItemStack itemstack = inv.getStackInSlot(i);
				nonnulllist.set(i, this.container);
			}

			return nonnulllist;
		}

		@Override
		public String getName() {
			return getRecipeOutput().getItem().getRegistryName().toString();
		}

		@Override
		public ResourceLocation getGroupResource() {
			return new ResourceLocation(this.getGroup());
		}

		@Override
		public boolean isShaped() {
			return true;
		}

	}
	
	public static class ShapelessRecipe extends ShapelessRecipes implements NoContainerRecipes {

		private ItemStack container = ItemStack.EMPTY;

		public ShapelessRecipe(IngredientList ingredientList) {
			this(Groups.NONE, ingredientList.asList(), ingredientList.getResult());
		}

		public ShapelessRecipe(String groupId, IngredientList ingredientList) {
			this(groupId, ingredientList.asList(), ingredientList.getResult());
		}

		public ShapelessRecipe(NonNullList<Ingredient> ingredients, ItemStack result) {
			this(Groups.NONE, ingredients, result);
		}

		public ShapelessRecipe(String group, NonNullList<Ingredient> ingredients, ItemStack result) {
			super(group, result, ingredients);
		}

		@Override
		public ShapelessRecipe setCustomContainer(ItemStack stack) {
			this.container = stack;
			return this;
		}

		@Override
		public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
			NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>withSize(inv.getSizeInventory(),
					ItemStack.EMPTY);

			for (int i = 0; i < nonnulllist.size(); ++i) {
				ItemStack itemstack = inv.getStackInSlot(i);
				nonnulllist.set(i, this.container);
			}

			return nonnulllist;
		}

		@Override
		public String getName() {
			return getRecipeOutput().getItem().getRegistryName().toString();
		}

		@Override
		public ResourceLocation getGroupResource() {
			return new ResourceLocation(this.getGroup());
		}

		@Override
		public boolean isShaped() {
			return false;
		}

	}
	
	public NoContainerRecipes setCustomContainer(ItemStack stack);
	
}

// public static NoContainerRecipes addRecipe(ItemStack stack, Object...
// recipeComponents) {
// String s = "";
// int i = 0;
// int j = 0;
// int k = 0;
//
// if (recipeComponents[i] instanceof String[]) {
// String[] astring = (String[]) ((String[]) recipeComponents[i++]);
//
// for (String s2 : astring) {
// ++k;
// j = s2.length();
// s = s + s2;
// }
// } else {
// while (recipeComponents[i] instanceof String) {
// String s1 = (String) recipeComponents[i++];
// ++k;
// j = s1.length();
// s = s + s1;
// }
// }
//
// Map<Character, ItemStack> map;
//
// for (map = Maps.<Character, ItemStack>newHashMap(); i <
// recipeComponents.length; i += 2) {
// Character character = (Character) recipeComponents[i];
// ItemStack itemstack = ItemStack.EMPTY;
//
// if (recipeComponents[i + 1] instanceof Item) {
// itemstack = new ItemStack((Item) recipeComponents[i + 1]);
// } else if (recipeComponents[i + 1] instanceof Block) {
// itemstack = new ItemStack((Block) recipeComponents[i + 1], 1, 32767);
// } else if (recipeComponents[i + 1] instanceof ItemStack) {
// itemstack = (ItemStack) recipeComponents[i + 1];
// }
//
// map.put(character, itemstack);
// }
//
// ItemStack[] aitemstack = new ItemStack[j * k];
//
// for (int l = 0; l < j * k; ++l) {
// char c0 = s.charAt(l);
//
// if (map.containsKey(Character.valueOf(c0))) {
// aitemstack[l] = ((ItemStack) map.get(Character.valueOf(c0))).copy();
// } else {
// aitemstack[l] = ItemStack.EMPTY;
// }
// }

// NoContainerRecipes noContainerRecipes = new NoContainerRecipes(j, k,
// aitemstack, stack);
// GameRegistry.addr (noContainerRecipes);
// return noContainerRecipes;
// }

// }