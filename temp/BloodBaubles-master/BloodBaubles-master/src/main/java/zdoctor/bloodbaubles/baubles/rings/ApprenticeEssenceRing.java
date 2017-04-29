package zdoctor.bloodbaubles.baubles.rings;

import WayofTime.bloodmagic.registry.ModItems;
import zdoctor.bloodbaubles.References;
import zdoctor.bloodbaubles.baubles.EssenceBloodRing;

public class ApprenticeEssenceRing extends EssenceBloodRing {

  private int[] cost = new int[]{50, 135, 350, 875, 1125};

  public ApprenticeEssenceRing() {
    super(References.APPRENTICE, ModItems.orbApprentice);
  }

  @Override
  public int getCost(Integer meta) {
    return this.cost[meta];
  }
}
