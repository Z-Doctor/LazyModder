package zdoctor.lazymodder.easy.interfaces;

import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public interface ICustomStateMap {
	/**
	 * Used to register custom state map during model registry. Be sure to add
	 * side only annotation
	 * @return
	 */
	@SideOnly(Side.CLIENT)
	public IStateMapper getStateMap();
	
}
