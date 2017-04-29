package zdoctor.bloodbaubles.baubles.rings;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import zdoctor.bloodbaubles.References;
import zdoctor.bloodbaubles.api.IAutoRecipe;
import zdoctor.bloodbaubles.api.events.ISubPlayerDeath;
import zdoctor.bloodbaubles.baubles.VariantBloodRing;
import zdoctor.bloodbaubles.enums.EnumRingMaterial;
import zdoctor.bloodbaubles.helpers.BaubleHelper;
import zdoctor.bloodbaubles.helpers.RecipeHelper;
import zdoctor.bloodbaubles.init.Rings;
import zdoctor.bloodbaubles.token.PlayerEventToken;

public final class GodsGift extends VariantBloodRing
    implements
      ISubPlayerDeath<PlayerEventToken.DeathToken>,
      IAutoRecipe {

  public GodsGift() {
    super(References.GODSGIFT);
    this.addVariant(References.GODSGIFT_INACTIVE, State.Inactive.getMeta());
    this.addVariant(References.GODSGIFT_ACTIVE, State.Active.getMeta());
  }

  @Override
  public void onEvent(PlayerEventToken.DeathToken token) {
    BaubleHelper bH = new BaubleHelper(token.entity);
    if (bH.isWearing(this.varientStackItem(References.GODSGIFT_ACTIVE))) {
      if (token.source != DamageSource.outOfWorld) {
        if (bH.replaceBauble(this.varientStackItem(References.GODSGIFT_ACTIVE),
            this.varientStackItem(References.GODSGIFT_INACTIVE))) {
          token.event.setCanceled(true);
          token.voidDamage();
          token.healPlayer();
        }
      }
    }
  }

  @Override
  public void registerRecipe() {
    RecipeHelper rH = new RecipeHelper(this);
    rH.define('n', Items.NETHER_STAR);
    rH.define('r',
        new ItemStack(Rings.BasicRing, 1, EnumRingMaterial.Gold.getMeta()));
    rH.setLayer1("n");
    rH.setLayer2("r");
    rH.registerRecipe();
  }

  public static enum State {
    Active(1), Inactive(0);

    private int meta;

    private State(int meta) {
      this.meta = meta;
    }

    public int getMeta() {
      return this.meta;
    }
  }
}
