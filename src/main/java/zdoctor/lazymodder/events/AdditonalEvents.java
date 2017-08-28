package zdoctor.lazymodder.events;

import net.minecraft.block.IGrowable;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AdditonalEvents {
	@SubscribeEvent
	public void onConfigChanged(BonemealEvent e) {
		if(e.getBlock().getBlock() instanceof IGrowable && e.isCancelable())
			e.setCanceled(!((IGrowable)e.getBlock().getBlock()).canUseBonemeal(e.getWorld(), e.getWorld().rand, e.getPos(), e.getBlock()));
	}
}
