package zdoctor.bloodbaubles.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BaubleHelper {

  private EntityPlayer player;

  private InventoryBaubles inventory;

  public BaubleHelper(EntityPlayer player) {
    this.player = player;
    this.inventory = PlayerHandler.getPlayerBaubles(player);
  }

  public boolean isWearing(Item item) {
    return this.indexOf(item) > -1;
  }

  public int indexOf(Item item) {
    for (int i = 0; i < this.inventory.getSizeInventory(); i++) {
      if (this.inventory.getStackInSlot(i) != null
          && this.inventory.getStackInSlot(i).getItem().equals(item)) {
        return i;
      }
    }
    return -1;
  }

  public boolean isWearing(ItemStack itemStack) {
    return this.indexOf(itemStack) > -1;
  }

  public int indexOf(ItemStack itemStack) {
    for (int i = 0; i < this.inventory.getSizeInventory(); i++) {
      if (this.inventory.getStackInSlot(i) != null
          && this.inventory.getStackInSlot(i).isItemEqual(itemStack)) {
        return i;
      }
    }
    return -1;
  }

  private ArrayList<ItemStack> asArray() {
    return new ArrayList<>(Arrays.asList(this.inventory.stackList));
  }

  public boolean replaceBauble(ItemStack itemStack, ItemStack itemStack2) {
    int index = indexOf(itemStack);
    if (index > -1) {
      this.inventory.setInventorySlotContents(index, itemStack2);
    }
    return index > -1;
  }

  public ItemStack getBaubleStack(Item item) {
    return this.inventory.getStackInSlot(this.indexOf(item));
  }

  public List<ItemStack> getBaubleStacks(Item item) {
    List<ItemStack> baubleStacks = new ArrayList<>();
    for (int i = 0; i < this.inventory.getSizeInventory(); i++) {
      if (this.inventory.getStackInSlot(i) != null
          && this.inventory.getStackInSlot(i).getItem().equals(item)) {
        baubleStacks.add(this.inventory.getStackInSlot(i));
      }
    }
    return baubleStacks;
  }
}
