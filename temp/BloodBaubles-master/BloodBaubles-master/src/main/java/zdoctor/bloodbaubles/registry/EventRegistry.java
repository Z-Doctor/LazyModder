package zdoctor.bloodbaubles.registry;

import java.util.ArrayList;

import zdoctor.bloodbaubles.api.events.ISubEvent;
import zdoctor.bloodbaubles.events.SubEvent;

/**
 * Events extending SubEvent will register themselves here. During the post init
 * phase, all babubles will be subscribed and called when appropriate.
 *
 * @author Z_Doctor
 */
public final class EventRegistry {

  private static final ArrayList<SubEvent> Event_REGISTRY = new ArrayList<>();

  public static void registerEvent(SubEvent event) {
    Event_REGISTRY.add(event);
  }

  public static void registerSubscibers() {
    BaubleRegistry.forEach(bauble -> {
      if (bauble instanceof ISubEvent) {
        Event_REGISTRY.forEach(event -> {
          event.registerSub((ISubEvent) bauble);
        });
      }
    });
  }
}
