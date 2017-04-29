package zdoctor.bloodbaubles.init;

import zdoctor.bloodbaubles.events.ItemCraftedEvent;
import zdoctor.bloodbaubles.events.LPDrainEvent;
import zdoctor.bloodbaubles.events.PlayerDeathEvent;

public final class Events {

  public static void createEvents() {
    new PlayerDeathEvent();
    new LPDrainEvent();
    new ItemCraftedEvent();
  }
}
