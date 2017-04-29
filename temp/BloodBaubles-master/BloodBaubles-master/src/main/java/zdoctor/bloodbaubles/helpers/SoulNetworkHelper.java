package zdoctor.bloodbaubles.helpers;

import WayofTime.bloodmagic.api.saving.BMWorldSavedData;
import WayofTime.bloodmagic.api.saving.SoulNetwork;
import WayofTime.bloodmagic.api.util.helper.NetworkHelper;
import net.minecraft.entity.player.EntityPlayer;

public class SoulNetworkHelper {

  public EntityPlayer player;

  public BMWorldSavedData bMWorld;

  private SoulNetwork soulNetwork;

  public SoulNetworkHelper(EntityPlayer player) {
    this.player = player;
    this.bMWorld = new BMWorldSavedData();
    this.soulNetwork = NetworkHelper.getSoulNetwork(player);
  }

  public int getCurrentEssence() {
    return this.soulNetwork.getCurrentEssence();
  }

  public boolean canDrain(int neededEssence) {
    return this.getCurrentEssence() >= neededEssence;
  }

  /**
   * Returns how much was able to be drained
   * 
   * @param neededEssence
   * @return
   */
  public int attemptToDrain(int neededEssence) {
    int diff = this.getCurrentEssence() - neededEssence;
    int drainAmount;
    if (diff < 0) {
      drainAmount = neededEssence + diff;
    } else {
      drainAmount = neededEssence;
    }
    this.drain(drainAmount);
    return drainAmount;
  }

  public boolean drain(int drainAmount) {
    return this.soulNetwork.syphonAndDamage(this.player, drainAmount);
  }

  public boolean hasEnough(int drainAmount) {
    return this.getCurrentEssence() >= drainAmount;
  }
}
