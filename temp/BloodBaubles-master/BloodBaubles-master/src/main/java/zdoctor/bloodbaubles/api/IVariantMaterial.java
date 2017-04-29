package zdoctor.bloodbaubles.api;

import zdoctor.bloodbaubles.enums.EnumRingMaterial;

public interface IVariantMaterial extends IVariant {

  public default boolean isValidMaterial(EnumRingMaterial material) {
    return true;
  }
}
