package zdoctor.bloodbaubles.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import zdoctor.bloodbaubles.baubles.rings.GodsGift;

public final class ModCreativeTabs {

  public static final CreativeTabs BloodRings = new CreativeTabs(
      "BloodBaubles") {

    @Override
    public ItemStack getIconItemStack() {
      return new ItemStack(Rings.GodsGift, 1, GodsGift.State.Active.getMeta());
    }

    @Override
    public Item getTabIconItem() {
      return Rings.GodsGift;
    };
  };

  public static void initTabs() {
  }
}
