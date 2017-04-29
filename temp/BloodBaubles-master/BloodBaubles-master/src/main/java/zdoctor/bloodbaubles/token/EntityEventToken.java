package zdoctor.bloodbaubles.token;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract class EntityEventToken<T extends EntityEvent, S extends Entity>
    extends
      EventToken<T> {

  public final S entity;

  public EntityEventToken(T event) {
    super(event);
    this.entity = (S) event.getEntity();
  }

  public static class EntityDamageToken<E extends EntityLiving>
      extends
        EntityEventToken<LivingHurtEvent, E> {

    public final DamageSource source;

    public float damage;

    public EntityDamageToken(LivingHurtEvent e) {
      super(e);
      this.source = e.getSource();
      this.damage = e.getAmount();
    }

    public void healEntity() {
      this.entity.heal(this.entity.getMaxHealth());
    }
  }
}
