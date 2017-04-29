package zdoctor.bloodbaubles.helpers;

import java.util.ArrayList;
import java.util.List;

import WayofTime.bloodmagic.api.recipe.TartaricForgeRecipe;
import WayofTime.bloodmagic.api.registry.TartaricForgeRecipeRegistry;
import WayofTime.bloodmagic.api.soul.EnumDemonWillType;
import WayofTime.bloodmagic.item.soul.ItemSoulGem;
import WayofTime.bloodmagic.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import zdoctor.bloodbaubles.baubles.EssenceBloodRing.CustomTartaricRecipe;

public class SoulForgeRecipeHelper {

  private final Item result;

  private int meta;

  private int amount = 1;

  private int minSouls = 0;

  private int drain = 0;

  private List<ItemStack> components = new ArrayList<>();

  private boolean registered = false;

  private ItemStack minGem = new ItemStack(ModItems.soulGem);

  public SoulForgeRecipeHelper(Item item) {
    this(item, 0);
  }

  public SoulForgeRecipeHelper(Block block) {
    this(Item.getItemFromBlock(block));
  }

  public SoulForgeRecipeHelper(Block block, int meta) {
    this(Item.getItemFromBlock(block), meta);
  }

  public SoulForgeRecipeHelper(Item item, int meta) {
    this.result = item;
    this.meta = meta;
  }

  public SoulForgeRecipeHelper(Block block,
      SoulForgeRecipeHelper soulForgeRecipeHelper) {
    this(Item.getItemFromBlock(block), soulForgeRecipeHelper);
  }

  public SoulForgeRecipeHelper(Item item,
      SoulForgeRecipeHelper soulForgeRecipeHelper) {
    this.result = item;
    this.meta = soulForgeRecipeHelper.meta;
    this.amount = soulForgeRecipeHelper.amount;
    this.components = new ArrayList<>(soulForgeRecipeHelper.components);
  }

  public void setMinimumGem(ItemStack itemStack) {
    this.minGem = itemStack;
  }

  public void setMeta(int meta) {
    this.meta = meta;
  }

  public void addCompnents(Item item) {
    components.add(new ItemStack(item));
  }

  public void addCompnents(Item item, int meta) {
    components.add(new ItemStack(item, 1, meta));
  }

  public void addCompnents(Block block) {
    components.add(new ItemStack(block));
  }

  public void addCompnents(Block block, int meta) {
    components.add(new ItemStack(block, 1, meta));
  }

  public void addCompnents(ItemStack itemStack) {
    components.add(itemStack);
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public ItemStack getResult() {
    return new ItemStack(this.result, this.amount, this.meta);
  }

  public int getMinimumSouls() {
    return this.minSouls;
  }

  public int getDrain() {
    return this.drain;
  }

  public Object[] getComponents() {
    return this.components.toArray();
  }

  public TartaricForgeRecipe getRecipe() {
    return new TartaricForgeRecipe(
        new ItemStack(this.result, this.amount, this.meta), this.minSouls,
        this.drain, this.components.toArray());
  }

  public void registerRecipe() {
    if (!this.registered) {
      TartaricForgeRecipeRegistry.registerRecipe(this.getRecipe());
      this.registered = true;
    }
  }

  public void registerRecipe(CustomTartaricRecipe customRecipe) {
    if (!this.registered) {
      TartaricForgeRecipeRegistry.registerRecipe(customRecipe);
      this.registered = true;
    }
  }

  public ItemStack getMinGem() {
    return this.minGem;
  }

  public void setDrain(int drain) {
    this.drain = drain;
  }

  public static double getMaxWill(ItemStack itemStack) {
    return ((ItemSoulGem) ModItems.soulGem)
        .getMaxWill(EnumDemonWillType.DEFAULT, itemStack);
  }
}
