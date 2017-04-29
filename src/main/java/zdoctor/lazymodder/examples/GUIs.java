package zdoctor.lazymodder.examples;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import zdoctor.lazymodder.easy.EasyGUI;
import zdoctor.lazymodder.easy.EasyGUI.GUIHandler;

public class GUIs {
	public static int testGuiID;

	public static void init(FMLInitializationEvent e) {
		testGuiID = GUIHandler.registerGUI(new EasyGUI.GuiScreen(true, true), EasyGUI.Empty_Container);
	}
}
