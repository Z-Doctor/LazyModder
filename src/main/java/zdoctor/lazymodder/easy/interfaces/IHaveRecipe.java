package zdoctor.lazymodder.easy.interfaces;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import zdoctor.lazymodder.easy.builders.RecipeBuilder;

public interface IHaveRecipe {

	public void addRecipeToList(NonNullList<IRecipe> recipeList);
	
}
