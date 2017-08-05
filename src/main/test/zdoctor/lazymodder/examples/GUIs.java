package zdoctor.lazymodder.examples;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import zdoctor.lazymodder.client.gui.GuiHandler;

public class GUIs {
	// public static int testGuiID;

	public static void init() {
		// testGuiID = GUIHandler.registerGUI(new EasyGUI.GuiScreen(true, true),
		// EasyGUI.Empty_Container);
		GuiHandler testGui = new GuiHandler(Test.class);
	}

	public static class Test extends GuiScreen {
		@Override
		public void drawScreen(int mouseX, int mouseY, float partialTicks) {
			// drawDefaultBackground();
			// drawBackground(0);

			// drawRect(0, 0, this.width, this.height, 0x550000f0);

			Tessellator t = Tessellator.getInstance();
			BufferBuilder bb = t.getBuffer();

			int color = 0x80ffffff;

			float f3 = (float) (color >> 24 & 255) / 255.0F;
			float f = (float) (color >> 16 & 255) / 255.0F;
			float f1 = (float) (color >> 8 & 255) / 255.0F;
			float f2 = (float) (color & 255) / 255.0F;
			
			GlStateManager.clearColor(0, 0, 0, 0);
			
			GlStateManager.disableAlpha();
			
			// GlStateManager.disableTexture2D();
//			 GlStateManager.color(f, f1, f2, f3);
			// GlStateManager.disable

			// GlStateManager.generateTexture();

			TextureManager renderEngine = Minecraft.getMinecraft().getTextureManager();
			renderEngine.bindTexture(new ResourceLocation("lazymodder:textures/guis/data_terminal/back.png"));
//			renderEngine.bindTexture(new ResourceLocation("lazymodder:textures/entity/mushroomoid/normal.png"));

//			drawTexturedModalRect(0, 0, 0, 0, 268, 327);
//			drawTexturedModalRect(100, 100, 100 + (268/4), 100 + (327/4), 100 + (268/4), 100 + (327/4));
//			drawTexturedModalRect(100, 0, 0, 0, 268, 327);
			
//			GlStateManager.disableAlpha();
			
//			drawRect(100,100, 100 + (268/4), 100 + (327/4), color);
			
			// GlStateManager.bindTexture(texture);
			// GL11.glBindTexture(target, texture);

			// bb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
			//
			// bb.pos((double) this.width, (double) 0, (double) 0).endVertex();
			// bb.pos((double) 0, (double) 0, (double) 0).endVertex();
			// bb.pos((double) 0, (double) this.height, (double) 0).endVertex();
			// bb.pos((double) this.width, (double) this.height, (double)
			// 0).endVertex();
			//
			// t.draw();

//			 GlStateManager.shadeModel(7424);
//			 GlStateManager.disableBlend();
//			 GlStateManager.enableAlpha();
//			 GlStateManager.enableTexture2D();

			 super.drawScreen(mouseX, mouseY, partialTicks);
		}

		@Override
		public boolean doesGuiPauseGame() {
			return true;
		}

		// @Override
		// public void onGuiClosed() {
		// super.onGuiClosed();
		// }

		@Override
		public void initGui() {
			// this.buttonList.add(this.a = new GuiButton(0, this.width / 2 -
			// 100, this.height / 2 - 24, "This is button a"));
			// this.buttonList.add(this.b = new GuiButton(1, this.width / 2 -
			// 100, this.height / 2 + 4, "This is button b"));
			super.initGui();
		}

		@Override
		protected void actionPerformed(GuiButton button) throws IOException {
			super.actionPerformed(button);
		}
	}
}
