package zdoctor.bloodbaubles.helpers;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeHelper {

  private final Item result;

  private int meta;

  private boolean shaped;

  private int amount = 1;

  private String layer1 = "";

  private String layer2 = "";

  private String layer3 = "";

  private Map<Character, ItemStack> map = new HashMap<>(9);

  private boolean registered = false;

  public RecipeHelper(Item item) {
    this(item, 0, true);
  }

  public RecipeHelper(Block block) {
    this(Item.getItemFromBlock(block));
  }

  public RecipeHelper(Block block, int meta) {
    this(Item.getItemFromBlock(block), meta);
  }

  public RecipeHelper(Block block, boolean shaped) {
    this(Item.getItemFromBlock(block), shaped);
  }

  public RecipeHelper(Item item, int meta) {
    this(item, meta, true);
  }

  public RecipeHelper(Item item, boolean shaped) {
    this(item, 0, shaped);
  }

  public RecipeHelper(Item item, int meta, boolean shaped) {
    this.result = item;
    this.meta = meta;
    this.shaped = shaped;
  }

  public RecipeHelper(Block block, RecipeHelper recipeHelper) {
    this(Item.getItemFromBlock(block), recipeHelper);
  }

  public RecipeHelper(Item item, RecipeHelper recipeHelper) {
    this.result = item;
    this.meta = recipeHelper.meta;
    this.shaped = recipeHelper.shaped;
    this.amount = recipeHelper.amount;
    this.layer1 = recipeHelper.layer1;
    this.layer2 = recipeHelper.layer2;
    this.layer3 = recipeHelper.layer3;
    this.map = new HashMap<>(recipeHelper.map);
  }

  public void setMeta(int meta) {
    this.meta = meta;
  }

  public void setShaped(boolean shaped) {
    this.shaped = shaped;
  }

  public void define(char key, Item item) {
    map.put(key, new ItemStack(item));
  }

  public void define(char key, Item item, int meta) {
    map.put(key, new ItemStack(item, 1, meta));
  }

  public void define(char key, Block block) {
    map.put(key, new ItemStack(block));
  }

  public void define(char key, Block block, int meta) {
    map.put(key, new ItemStack(block, 1, meta));
  }

  public void define(char key, ItemStack itemStack) {
    map.put(key, itemStack);
  }

  public void setLayer1(String string) {
    this.layer1 = string;
  }

  public void setLayer2(String string) {
    this.layer2 = string;
  }

  public void setLayer3(String string) {
    this.layer3 = string;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public boolean isShaped() {
    return this.shaped;
  }

  public ItemStack getResult() {
    return new ItemStack(this.result, this.amount, this.meta);
  }

  public ShapedRecipes getRecipe() {
    if (this.isShaped())
      return new ShapedRecipes(this.getWidth(), this.getHeight(),
          this.getRecipeMatrix(), this.getResult());
    else
      return null;
  }

  private int getWidth() {
    return Math.max(this.layer1.length(),
        Math.max(this.layer2.length(), this.layer3.length()));
  }

  private int getHeight() {
    return (this.layer1.length() > 0 ? 1 : 0)
        + (this.layer2.length() > 0 ? 1 : 0)
        + (this.layer3.length() > 0 ? 1 : 0);
  }

  private char[] getMaxtrix() {
    if (this.getHeight() >= 1 && this.layer1.length() < this.getWidth()) {
      while (this.layer1.length() < this.getWidth())
        this.layer1 += " ";
    }
    if (this.getHeight() >= 2 && this.layer2.length() < this.getWidth()) {
      while (this.layer2.length() < this.getWidth())
        this.layer2 += " ";
    }
    if (this.getHeight() >= 3 && this.layer3.length() < this.getWidth()) {
      while (this.layer3.length() < this.getWidth())
        this.layer3 += " ";
    }
    return (this.layer1 + this.layer2 + this.layer3).toCharArray();
  }

  private ItemStack[] getRecipeMatrix() {
    char[] matrix = this.getMaxtrix();
    ItemStack[] temp = new ItemStack[this.getWidth() * this.getHeight()];
    for (int height = 0; height < this.getHeight(); height++) {
      for (int width = 0; width < this.getWidth(); width++) {
        ItemStack itemStack = this.map
            .get(matrix[height * this.getWidth() + width]);
        temp[height * this.getWidth() + width] = itemStack;
      }
    }
    return temp;
  }

  public void registerRecipe() {
    if (!this.registered) {
      GameRegistry.addRecipe(this.getRecipe());
      this.registered = true;
    } else
      FMLLog.getLogger().debug("Skipping: Recipe already registered for '"
          + this.result.getRegistryName() + "'");
  }
}
