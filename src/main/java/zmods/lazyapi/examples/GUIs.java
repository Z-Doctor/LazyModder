package zmods.lazyapi.examples;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import zmods.lazyapi.easy.EasyGUI;
import zmods.lazyapi.easy.EasyGUI.GUIHandler;

public class GUIs {
	public static int testGuiID;

	public static void init(FMLInitializationEvent e) {
		testGuiID = GUIHandler.registerGUI(new EasyGUI.GuiScreen(true, true), EasyGUI.Empty_Container);
	}
}
