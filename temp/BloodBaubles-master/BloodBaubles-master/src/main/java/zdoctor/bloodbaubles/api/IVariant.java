package zdoctor.bloodbaubles.api;

import java.util.List;
import java.util.function.BiConsumer;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IVariant {

  void addVariant(String varName);

  void addVariant(String varName, int meta);

  default void forEachVariant(List<String> varNames,
      BiConsumer<Integer, String> action) {
    for (String name : varNames) {
      action.accept(varNames.indexOf(name), name);
    }
  }

  public default ItemStack varientStackItem(String varName) {
    return new ItemStack((Item) this, 1, this.getVariantMeta(varName));
  }

  public default ItemStack varientStackBlock(String varName) {
    return new ItemStack((Block) this, 1, this.getVariantMeta(varName));
  }

  public String getVariantName(int meta);

  public int getVariantMeta(String varName);

  public void forEachVariant(BiConsumer<Integer, String> action);
}
