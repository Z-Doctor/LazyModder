package zdoctor.lazymodder.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class RenderHelper {

	public static void bindTexture(ResourceLocation texLocation) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texLocation);
	}

	public static int getCenterX(GuiScreen gui, int guiWidth) {
		return (gui.width/2) - guiWidth/2;
	}

	public static int getCenterY(GuiScreen gui, int guiHeight) {
		return (gui.height/2) - guiHeight/2;
	}

}
