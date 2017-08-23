package zdoctor.lazymodder.easy;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class EasyMisc {
	/**
     * Register a new seed to be dropped when breaking tall grass.
     *
     * @param drop The item to drop as a seed.
     * @param dropChance The relative probability of the seeds,
     *               where wheat seeds are 10.
     **/
	public static void addGrassDrop(ItemStack drop, int dropChance) {
		MinecraftForge.addGrassSeed(drop, dropChance);
	}
}
