package zdoctor.bloodbaubles.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import zdoctor.bloodbaubles.base.AutoBlock;

public class Blocks {

  public static final Block Test = new AutoBlock("Test", Material.ROCK)
      .setCreativeTab(ModCreativeTabs.BloodRings);

  public static void initBlocks() {
  }
}
