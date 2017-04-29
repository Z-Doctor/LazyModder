package zdoctor.bloodbaubles.events;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zdoctor.bloodbaubles.api.events.ISubPlayerDeath;
import zdoctor.bloodbaubles.api.events.ISubRender;

public class RenderEvent extends SubEvent<ISubRender> {

  public RenderEvent() {
    super();
  }

  @SubscribeEvent(receiveCanceled = false)
  public void renderWoldLast(RenderWorldLastEvent e) {
    // RenderEventToken token = new RenderEventToken(e);
    REGISTRY.forEach(sub -> {
      if (e.isCanceled() && !sub.receiveCanceled())
        return;
      else
        sub.onEvent(null);
    });
    // if (e.getEntity() instanceof EntityPlayer && e.isCancelable()) {
    // if (e.getEntityLiving().getHealth() - e.getAmount() <= 0) {
    // PlayerDeathToken token = new PlayerDeathToken(e);
    // REGISTRY.forEach(sub -> {
    // if (e.isCanceled() && !sub.receiveCanceled())
    // return;
    // else
    // sub.onEvent(token);
    // });
    // }
    // }
  }

  @Override
  public boolean isSub(Object sub) {
    return sub instanceof ISubPlayerDeath;
  }
}
