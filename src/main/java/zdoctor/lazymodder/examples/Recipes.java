package zdoctor.lazymodder.examples;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zdoctor.lazymodder.easy.crafting.EasyRecipe;
import zdoctor.lazymodder.easy.crafting.EasyRecipe.ShapedRecipe;
import zdoctor.lazymodder.easy.crafting.IngredientList.ShapedList;
import zdoctor.lazymodder.easy.crafting.IngredientList.ShapelessList;
import zdoctor.lazymodder.registery.RecipeRegistry;

public class Recipes {
	public static void preInit() {
//		ShapedList list1 = new ShapedList(new ItemStack(ZItems.testItem));
//		list1.addIngredient(1, 1, Items.STICK);
//		list1.addIngredient(2, 2, Items.DIAMOND);
////		list1.addIngredient(3, 3, Items.STICK);
//		EasyRecipe test1 = new ShapedRecipe(list1);
//		RecipeRegistry.addRecipe(test1);
		
		ShapelessList list2 = new ShapelessList(new ItemStack(ZItems.testItem));
		list2.addIngredient(Items.STICK);
//		list2.addIngredient(Items.GLASS_BOTTLE);
//		list2.addIngredient(Items.STICK);
//		GameRegistry.addShapelessRecipe(name, group, output, params);
		EasyRecipe test2 = new EasyRecipe.ShapelessRecipe(list2);
		RecipeRegistry.addRecipe(test2);
		
	}
}
