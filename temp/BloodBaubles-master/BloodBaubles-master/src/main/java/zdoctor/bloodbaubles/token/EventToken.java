package zdoctor.bloodbaubles.token;

import net.minecraftforge.fml.common.eventhandler.Event;

public class EventToken<T extends Event> {

  public final T event;

  public EventToken(T event) {
    this.event = event;
  }
}
