package zdoctor.bloodbaubles.baubles.rings;

import WayofTime.bloodmagic.registry.ModItems;
import zdoctor.bloodbaubles.References;
import zdoctor.bloodbaubles.baubles.EssenceBloodRing;

public class WeakEssenceRing extends EssenceBloodRing {

  private int[] cost = new int[]{15, 75, 250, 625, 1025};

  public WeakEssenceRing() {
    super(References.WEAKESSENCE, ModItems.orbWeak);
  }

  @Override
  public int getCost(Integer meta) {
    return this.cost[meta];
  }
}
