package zdoctor.bloodbaubles.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import zdoctor.bloodbaubles.References;
import zdoctor.bloodbaubles.baubles.EssenceBloodRing;

public class EssenceHelper {

  private ItemStack itemStack;

  private EssenceBloodRing ring;

  private NBTTagCompound tagCompound;

  public EssenceHelper(ItemStack itemStack) {
    if (itemStack.getItem() instanceof EssenceBloodRing) {
      this.itemStack = itemStack;
      this.ring = (EssenceBloodRing) itemStack.getItem();
      if (!itemStack.hasTagCompound()) {
        this.tagCompound = new NBTTagCompound();
        itemStack.setTagCompound(this.tagCompound);
        this.tagCompound.setInteger(References.MAX_ESSENCE,
            this.ring.getMaxEssence(itemStack));
        this.tagCompound.setInteger(References.CURRENT_ESSENCE, 0);
      } else
        this.tagCompound = itemStack.getTagCompound();
    }
  }

  public int syphonFromReserve(int drainAmount) {
    int diff = this.getCurrentEssence() - drainAmount;
    if (diff < 0) {
      this.setCurrentEssence(0);
      return diff * -1;
    } else {
      this.setCurrentEssence(diff);
      return 0;
    }
  }

  private void setCurrentEssence(int i) {
    this.tagCompound.setInteger(References.CURRENT_ESSENCE, i);
  }

  private void addCurrentEssence(int i) {
    this.setCurrentEssence(this.getCurrentEssence() + i);
    if (this.getCurrentEssence() > this.getMaxEssence())
      this.setCurrentEssence(this.getMaxEssence());
  }

  public int getCurrentEssence() {
    return this.tagCompound.getInteger(References.CURRENT_ESSENCE);
  }

  public int getMaxEssence() {
    return this.tagCompound.getInteger(References.MAX_ESSENCE);
  }

  public int getNeededEssence() {
    return this.getMaxEssence() - this.getCurrentEssence();
  }

  public boolean isFull(ItemStack itemStackIn) {
    return this.getCurrentEssence() == this.getMaxEssence();
  }

  public int attemptToFillFrom(EntityPlayer playerIn) {
    SoulNetworkHelper sH = new SoulNetworkHelper(playerIn);
    int supply = sH.attemptToDrain(this.getNeededEssence());
    this.addCurrentEssence(supply);
    return supply;
  }
}
