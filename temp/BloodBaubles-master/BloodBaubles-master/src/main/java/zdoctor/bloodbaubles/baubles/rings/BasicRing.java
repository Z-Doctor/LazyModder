package zdoctor.bloodbaubles.baubles.rings;

import zdoctor.bloodbaubles.References;
import zdoctor.bloodbaubles.api.IAutoRecipe;
import zdoctor.bloodbaubles.baubles.MaterialBloodRing;
import zdoctor.bloodbaubles.enums.EnumRingMaterial;
import zdoctor.bloodbaubles.helpers.SoulForgeRecipeHelper;

public class BasicRing extends MaterialBloodRing implements IAutoRecipe {

  private int[] cost = new int[]{30, 100, 325, 775, 1000};

  public BasicRing() {
    super(References.BASICRING);
  }

  @Override
  public void registerRecipe() {
    this.forEachVariant((meta, varName) -> {
      SoulForgeRecipeHelper reciperHelper = new SoulForgeRecipeHelper(this,
          meta);
      EnumRingMaterial material = EnumRingMaterial.valueOf(varName);
      reciperHelper.addCompnents(material.getMaterialStack());
      reciperHelper.addCompnents(material.getMaterialStack());
      reciperHelper.addCompnents(material.getMaterialStack());
      reciperHelper.addCompnents(material.getMaterialStack());
      reciperHelper.setDrain(this.cost[meta]);
      reciperHelper.registerRecipe();
    });
  }
}
