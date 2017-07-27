package zdoctor.lazymodder.easy.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public interface EasyRecipe extends IRecipe {
	
	public static class ShapedRecipe extends ShapedRecipes implements EasyRecipe {
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
		
		public ShapedRecipe(String group, int width, int height, NonNullList<Ingredient> ingredients, ItemStack output) {
			super(group, width, height, ingredients, output);
//			GameRegistry.addShapedRecipe(name, group, output, params);
		}

		@Override
		public boolean isShaped() {
			return true;
		}

		@Override
		public ResourceLocation getGroupResource() {
			return new ResourceLocation(this.getGroup());
		}
		
		@Override
		public String getName() {
			return getRecipeOutput().getItem().getRegistryName().toString();
		}
	}
	
	public static class ShapelessRecipe extends ShapelessRecipes implements EasyRecipe {
		public ShapelessRecipe(IngredientList ingredientList) {
			this(Groups.NONE, ingredientList.asList(), ingredientList.getResult());
		}
		
		public ShapelessRecipe(String groupId, IngredientList ingredientList) {
			this(groupId, ingredientList.asList(), ingredientList.getResult());
		}
		
		public ShapelessRecipe(NonNullList<Ingredient> ingredients, ItemStack result) {
			this(Groups.NONE, ingredients, result);
		}
		
		public ShapelessRecipe(String group, NonNullList<Ingredient> ingredients, ItemStack output) {
			super(group, output, ingredients);
//			GameRegistry.addShapedRecipe(name, group, output, params);
		}

		@Override
		public boolean isShaped() {
			return false;
		}
		
		@Override
		public ResourceLocation getGroupResource() {
			return new ResourceLocation(this.getGroup());
		}

		@Override
		public String getName() {
			return getRecipeOutput().getItem().getRegistryName().toString();
		}
	}
	

	public String getGroup();
	
	public String getName();
	
	public ResourceLocation getGroupResource();


	default public int getWidth() {
		return -1;
	}


	default public int getHeight(){
		return -1;
	}

	public boolean isShaped();

	public NonNullList<Ingredient> getIngredients();

	public ItemStack getRecipeOutput();
	
	public static class Groups {
		public static final String NONE = "";
		public static final String WOOL = "wool";
		public static final String STAINED_HARDENED_CLAY = "stained_hardened_clay";
		public static final String STAINED_GLASS_PANE = "stained_glass_pane";
		public static final String STAINED_GLASS = "stained_glass";
		public static final String YELLOW_DYE = "yellow_dye";
		public static final String CONCRETE_POWDER = "concrete_powder";
		public static final String CARPET = "carpet";
		public static final String DYED_BED = "dyed_bed";
		public static final String BED = "bed";
		public static final String BANNER = "banner";
		public static final String WOODEN_DOOR = "wooden_door";
		public static final String WOODEN_SLAB = "wooden_slab";
		public static final String WOODEN_STAIRS = "wooden_stairs";
		public static final String PLANKS = "planks";
		public static final String WOODEN_FENCE_GATE = "wooden_fence_gate";
		public static final String WOODEN_FENCE = "wooden_fence";
		public static final String boat = "boat";
		public static final String RED_DYE = "red_dye";
		public static final String RABBIT_STEW = "rabbit_stew";
		public static final String PINK_DYE = "pink_dye";
		public static final String ORANGE_DYE = "orange_dye";
		public static final String MAGENTA_DYE = "magenta_dye";
		public static final String LIGHT_GRAY_DYE = "light_gray_dye";
		public static final String LIGHT_BLUE_DYE = "light_blue_dye";
		public static final String IRON_INGOT = "iron_ingot";
		public static final String GOLD_INGOT = "gold_ingot";
		public static final String BONEMEAL = "bonemeal";
	}
}
