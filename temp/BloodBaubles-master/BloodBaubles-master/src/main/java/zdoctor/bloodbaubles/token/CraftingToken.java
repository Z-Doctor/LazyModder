package zdoctor.bloodbaubles.token;

import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class CraftingToken extends EventToken<ItemCraftedEvent> {

  public CraftingToken(ItemCraftedEvent e) {
    super(e);
  }
}
