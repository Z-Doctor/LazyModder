package zdoctor.bloodbaubles.enums;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public enum EnumRingMaterial {
  Wood(0.1d, Blocks.LOG), Stone(0.25d, Blocks.STONE), Iron(0.85d,
      Items.IRON_INGOT), Gold(1.45d,
          Items.GOLD_INGOT), Diamond(2.15d, Items.DIAMOND);

  private double multiplier;

  private Item material;

  EnumRingMaterial(double multiIn, Block blockIn) {
    this(multiIn, Item.getItemFromBlock(blockIn));
  }

  EnumRingMaterial(double multiIn, Item material) {
    this.multiplier = multiIn;
    this.material = material;
  }

  public double getMultiplier() {
    return this.multiplier;
  }

  public int getMeta() {
    return this.ordinal();
  }

  public ItemStack getMaterialStack() {
    return new ItemStack(this.material, 1, OreDictionary.WILDCARD_VALUE);
  }
}
