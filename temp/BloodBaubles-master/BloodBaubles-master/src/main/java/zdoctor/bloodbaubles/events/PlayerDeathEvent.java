package zdoctor.bloodbaubles.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zdoctor.bloodbaubles.api.events.ISubPlayerDeath;
import zdoctor.bloodbaubles.token.PlayerEventToken;

public class PlayerDeathEvent extends SubEvent<ISubPlayerDeath> {

  public PlayerDeathEvent() {
    super();
  }

  @SubscribeEvent(receiveCanceled = false)
  public void deathEvent(LivingHurtEvent e) {
    if (e.getEntity() instanceof EntityPlayer && e.isCancelable()) {
      if (e.getEntityLiving().getHealth() - e.getAmount() <= 0) {
        PlayerEventToken.DeathToken token = new PlayerEventToken.DeathToken(e);
        REGISTRY.forEach(sub -> {
          if (e.isCanceled() && !sub.receiveCanceled())
            return;
          else
            sub.onEvent(token);
        });
      }
    }
  }

  @Override
  public boolean isSub(Object sub) {
    return sub instanceof ISubPlayerDeath;
  }
}
