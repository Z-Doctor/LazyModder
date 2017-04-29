package zdoctor.bloodbaubles.baubles;

import zdoctor.bloodbaubles.api.IVariantMaterial;
import zdoctor.bloodbaubles.enums.EnumRingMaterial;

public abstract class MaterialBloodRing extends VariantBloodRing
    implements
      IVariantMaterial {

  public MaterialBloodRing(String nameIn) {
    super(nameIn);
    for (EnumRingMaterial material : EnumRingMaterial.values()) {
      this.addVariant(material.name());
    }
  }
}
