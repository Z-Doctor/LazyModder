package zdoctor.bloodbaubles.events;

import java.util.function.Consumer;
import java.util.function.Predicate;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zdoctor.bloodbaubles.api.events.ISubCrafting;
import zdoctor.bloodbaubles.baubles.EssenceBloodRing;
import zdoctor.bloodbaubles.token.CraftingToken;

public class ItemCraftedEvent extends SubEvent<ISubCrafting> {

  public ItemCraftedEvent() {
    super();
  }

  @SubscribeEvent(receiveCanceled = false)
  public void itemCraftedEvent(
      net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent e) {
    CraftingToken token = new CraftingToken(e);
    REGISTRY.forEach(sub -> {
      if (!e.isCanceled() || sub.receiveCanceled())
        sub.onEvent(token);
    });
  }

  @SubscribeEvent(receiveCanceled = false)
  public void orbCrafted(
      net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent e) {
    if (!e.player.getEntityWorld().isRemote)
      if (e.crafting.getItem() instanceof EssenceBloodRing) {
        clearGrid(e.craftMatrix);
      }
  }

  // @SubscribeEvent(receiveCanceled = false)
  // public void orbCrafted( e) {
  // TartaricForgeRecipeRegistry.a
  // if (!e.player.getEntityWorld().isRemote)
  // if (e.crafting.getItem() instanceof EssenceBloodRing) {
  // clearGrid(e.craftMatrix);
  // }
  // }
  public static void clearGrid(IInventory craftMatrix) {
    removeItemFromGrid(craftMatrix, Object.class);
  }

  public static void removeItemFromGrid(IInventory craftMatrix,
      Class<?> check) {
    cycleThroughGrid(craftMatrix,
        itemStack -> itemStack != null
            && check.isAssignableFrom(itemStack.getItem().getClass()),
        slotNumber -> craftMatrix.setInventorySlotContents(slotNumber, null));
  }

  /**
   * This method is for quick and advance opertion
   * 
   * @param craftMatrix
   * @param modfier
   * @param block
   */
  public static void cycleThroughGrid(IInventory craftMatrix,
      Predicate<ItemStack> modfier, Consumer<Integer> block) {
    for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
      if (modfier.test(craftMatrix.getStackInSlot(i))) {
        block.accept(i);
      }
    }
  }

  @Override
  public boolean isSub(Object sub) {
    return sub instanceof ISubCrafting;
  }
}
