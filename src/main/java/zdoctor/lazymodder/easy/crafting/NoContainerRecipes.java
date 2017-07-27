package zdoctor.lazymodder.easy.crafting;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class NoContainerRecipes extends EasyRecipe {

		private ItemStack container = ItemStack.EMPTY;

		public NoContainerRecipes(String group, int width, int height, NonNullList ingredients, ItemStack output) {
			super(group, width, height, ingredients, output);
		}
		
		public NoContainerRecipes setCustomContainer(ItemStack stack) {
			this.container  = stack;
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

//		public static NoContainerRecipes addRecipe(ItemStack stack, Object... recipeComponents) {
//			String s = "";
//			int i = 0;
//			int j = 0;
//			int k = 0;
//
//			if (recipeComponents[i] instanceof String[]) {
//				String[] astring = (String[]) ((String[]) recipeComponents[i++]);
//
//				for (String s2 : astring) {
//					++k;
//					j = s2.length();
//					s = s + s2;
//				}
//			} else {
//				while (recipeComponents[i] instanceof String) {
//					String s1 = (String) recipeComponents[i++];
//					++k;
//					j = s1.length();
//					s = s + s1;
//				}
//			}
//
//			Map<Character, ItemStack> map;
//
//			for (map = Maps.<Character, ItemStack>newHashMap(); i < recipeComponents.length; i += 2) {
//				Character character = (Character) recipeComponents[i];
//				ItemStack itemstack = ItemStack.EMPTY;
//
//				if (recipeComponents[i + 1] instanceof Item) {
//					itemstack = new ItemStack((Item) recipeComponents[i + 1]);
//				} else if (recipeComponents[i + 1] instanceof Block) {
//					itemstack = new ItemStack((Block) recipeComponents[i + 1], 1, 32767);
//				} else if (recipeComponents[i + 1] instanceof ItemStack) {
//					itemstack = (ItemStack) recipeComponents[i + 1];
//				}
//
//				map.put(character, itemstack);
//			}
//
//			ItemStack[] aitemstack = new ItemStack[j * k];
//
//			for (int l = 0; l < j * k; ++l) {
//				char c0 = s.charAt(l);
//
//				if (map.containsKey(Character.valueOf(c0))) {
//					aitemstack[l] = ((ItemStack) map.get(Character.valueOf(c0))).copy();
//				} else {
//					aitemstack[l] = ItemStack.EMPTY;
//				}
//			}

//			NoContainerRecipes noContainerRecipes = new NoContainerRecipes(j, k, aitemstack, stack);
//			GameRegistry.addr (noContainerRecipes);
//			return noContainerRecipes;
//		}

	}