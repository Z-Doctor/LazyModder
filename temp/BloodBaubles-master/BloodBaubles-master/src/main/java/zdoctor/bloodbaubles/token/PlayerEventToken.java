package zdoctor.bloodbaubles.token;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract class PlayerEventToken<T extends EntityEvent>
    extends
      EntityEventToken<T, EntityPlayer> {

  public PlayerEventToken(T event) {
    super(event);
  }

  public static class DamageToken extends PlayerEventToken<LivingHurtEvent> {

    public final DamageSource source;

    public float damage;

    public DamageToken(LivingHurtEvent e) {
      super(e);
      this.source = e.getSource();
      this.damage = e.getAmount();
    }

    public void healPlayer() {
      this.entity.heal(this.entity.getMaxHealth());
    }

    public void voidDamage() {
      this.event.setAmount(0);
    }

    public void setDamage(float f) {
      this.event.setAmount(f);
    }
  }

  public static class DeathToken extends DamageToken {

    public DeathToken(LivingHurtEvent e) {
      super(e);
    }
  }
}
