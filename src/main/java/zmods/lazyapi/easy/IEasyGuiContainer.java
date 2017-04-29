package zmods.lazyapi.easy;

import net.minecraft.client.gui.inventory.GuiContainer;

public interface IEasyGuiContainer {
	GuiContainer createContainer(Object...objects);
}
