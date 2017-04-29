package zdoctor.bloodbaubles.events;

import WayofTime.bloodmagic.api.event.SoulNetworkEvent.ItemDrainNetworkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zdoctor.bloodbaubles.api.events.ISubLPNetworkEvent.ISubLPDrain;
import zdoctor.bloodbaubles.token.NetworkDrainToken;

public class LPDrainEvent extends SubEvent<ISubLPDrain> {

  public LPDrainEvent() {
    super();
  }

  @SubscribeEvent(receiveCanceled = false)
  public void LPDrain(ItemDrainNetworkEvent e) {
    NetworkDrainToken token = new NetworkDrainToken(e);
    REGISTRY.forEach((sub) -> {
      if (e.isCanceled() && !sub.receiveCanceled())
        return;
      else
        sub.onEvent(token);
    });
  }

  @Override
  public boolean isSub(Object sub) {
    return sub instanceof ISubLPDrain;
  }
}
