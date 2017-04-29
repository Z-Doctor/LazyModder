package zdoctor.bloodbaubles.baubles;

import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;
import zdoctor.bloodbaubles.base.AutoBauble;
import zdoctor.bloodbaubles.init.ModCreativeTabs;

public abstract class BloodBauble extends AutoBauble {

  public BloodBauble(String nameIn) {
    super(nameIn);
    this.setMaxStackSize(1);
    this.setCreativeTab(ModCreativeTabs.BloodRings);
  }

  public static class BloodRing extends BloodBauble {

    public BloodRing(String nameIn) {
      super(nameIn);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStackIn) {
      return BaubleType.RING;
    }
  }

  public static class BloodAmulet extends BloodBauble {

    public BloodAmulet(String nameIn) {
      super(nameIn);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStackIn) {
      return BaubleType.AMULET;
    }
  }

  public static class BloodBelt extends BloodBauble {

    public BloodBelt(String nameIn) {
      super(nameIn);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStackIn) {
      return BaubleType.BELT;
    }
  }
}