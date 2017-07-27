package zdoctor.lazymodder.registery;

import java.util.ArrayList;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;
import zdoctor.lazymodder.easy.crafting.EasyRecipe;

public class RecipeRegistry {
	
	private static ArrayList<EasyRecipe> recipeList = new ArrayList<>();

	public static void registerRecipes(Register<IRecipe> event) {
		IForgeRegistry<IRecipe> registry = event.getRegistry();
		System.out.println("registring recipes");
//		GameRegistry.addShapelessRecipe(name, group, output, params);
		
		recipeList.forEach(recipe -> {
			if(recipe.isShaped())
				registry.register(((ShapedRecipes)recipe).setRegistryName(recipe.getName()));
			else
				registry.register(((ShapelessRecipes)recipe).setRegistryName(recipe.getName()));
		});
	}
	
	public static void addRecipe(EasyRecipe recipe) {
		recipeList.add(recipe);
	}
	
//	public static void addShapedRecipe(EasyRecipe recipe) {
//		recipeList.add(new ShapedRecipes(recipe.getGroup(), recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), recipe.getRecipeOutput()));
//	}
//
//	public static void addShapedRecipe(String group, int width, int height, NonNullList<Ingredient> ingredients, ItemStack result) {
//		recipeList.add(new ShapedRecipes(group, width, height, ingredients, result));
//		//GameRegistry.addShapedRecipe(name, group, output, params); (itemStack, components);
//	}
//
//	public static void addShapelessRecipe(String group, ItemStack output, NonNullList<Ingredient> ingredients) {
//		recipeList.add(new ShapelessRecipes(group, output, ingredients));
//		GameRegistry.addShapelessRecipe(itemStack, components);
//	}
//	
//	public static void addShapelessRecipe(EasyRecipe recipe) {
//		recipeList.add(new ShapelessRecipes(recipe.getGroup(), recipe.getRecipeOutput(), recipe.getIngredients()));
//	}

}
