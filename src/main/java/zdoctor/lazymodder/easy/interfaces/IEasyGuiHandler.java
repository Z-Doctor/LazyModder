package zdoctor.lazymodder.easy.interfaces;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.network.IGuiHandler;
import zdoctor.lazymodder.client.gui.EasyGuiHandler;

public interface IEasyGuiHandler {

	Object getMod();

	IGuiHandler getHandler();

}
