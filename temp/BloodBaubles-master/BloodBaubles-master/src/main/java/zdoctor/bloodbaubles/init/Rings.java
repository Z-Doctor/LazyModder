package zdoctor.bloodbaubles.init;

import net.minecraft.item.Item;
import zdoctor.bloodbaubles.baubles.rings.ApprenticeEssenceRing;
import zdoctor.bloodbaubles.baubles.rings.BasicRing;
import zdoctor.bloodbaubles.baubles.rings.GodsGift;
import zdoctor.bloodbaubles.baubles.rings.WeakEssenceRing;

/**
 * Rings should be registered here, everything else should be taken care of in
 * the class (where needed).
 * 
 * @author Z_Doctor
 */
public final class Rings {

  public static final Item BasicRing = new BasicRing();

  public static final Item WeakEssenceRing = new WeakEssenceRing();

  public static final Item ApprenticeEssenceRing = new ApprenticeEssenceRing();

  public static final Item GodsGift = new GodsGift();

  public static void initRings() {
  }
}
