package zdoctor.bloodbaubles.events;

import java.util.ArrayList;

import net.minecraftforge.common.MinecraftForge;
import zdoctor.bloodbaubles.api.events.ISubEvent;
import zdoctor.bloodbaubles.registry.EventRegistry;

/**
 * Extend and define
 * 
 * @param <T>
 *          This should be the token Buables will implement to get notified of
 *          their subscribed event.
 *
 * @author Z_Doctor
 */
public abstract class SubEvent<T extends ISubEvent> {

  protected final ArrayList<T> REGISTRY = new ArrayList<>();

  public SubEvent() {
    this.registerEvent(this);
    EventRegistry.registerEvent(this);
  }

  public abstract boolean isSub(Object sub);

  public void registerEvent(Object event) {
    MinecraftForge.EVENT_BUS.register(event);
  }

  public void registerSub(ISubEvent sub) {
    if (this.isSub(sub))
      this.REGISTRY.add((T) sub);
  }
}
