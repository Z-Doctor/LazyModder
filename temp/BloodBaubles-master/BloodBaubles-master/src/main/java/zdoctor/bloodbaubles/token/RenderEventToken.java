package zdoctor.bloodbaubles.token;

import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent.Pre;

public abstract class RenderEventToken<T>
    extends
      EventToken<GuiScreenEvent.DrawScreenEvent.Pre> {

  public RenderEventToken(Pre e) {
    super(e);
  }
}
