package zdoctor.lazymodder.easy.interfaces;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;

public interface IHaveRecipe {

	public void addRecipeToList(NonNullList<IRecipe> recipeList);
	
}
