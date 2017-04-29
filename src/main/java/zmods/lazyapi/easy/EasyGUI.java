package zmods.lazyapi.easy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.network.IGuiHandler;
import zmods.lazyapi.easy.EasyGUI.GuiText.ColorCodes;

public interface EasyGUI {
	public static final GuiScreen Empty_GUI = (GuiScreen) null;
	public static final GuiContainer Empty_Container = (GuiContainer) null;

	public static class GUIHandler implements IGuiHandler, EasyGUI {

		private static int nextGUI = 0;

		public static final Map<Integer, GuiScreen> Client_GUI_REG = new HashMap<>();
		public static final Map<Integer, GuiContainer> Server_GUI_REG = new HashMap<>();

		@Override
		public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
			if (Server_GUI_REG.get(ID) != null && Server_GUI_REG.get(ID) != Empty_Container) {
				try {
					Constructor<GuiContainer> constructor;
					if (Server_GUI_REG.get(ID).getClass().isInstance(GuiContainer.class)) {

						constructor = GuiContainer.class.getDeclaredConstructor(EntityPlayer.class, World.class,
								BlockPos.class);
						BlockPos blockPos = new BlockPos(x, y, z);
						return constructor.newInstance(player, world, blockPos);
					} else if (Server_GUI_REG.get(ID).getClass().isInstance(IEasyGuiContainer.class)) {
						return ((IEasyGuiContainer) Server_GUI_REG.get(ID)).createContainer(player, world, x, y, z);
					} else {
						constructor = GuiContainer.class.getDeclaredConstructor(IInventory.class);
						return constructor.newInstance(player.inventory);
					}
				} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
					FMLLog.log(Level.FATAL, "An error occured when creating a Gui Container. ID: {}", ID);
				}
			}
			return Empty_Container;

		}

		@Override
		public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
			if (Client_GUI_REG.get(ID) != null)
				return Client_GUI_REG.get(ID);
			return Empty_GUI;
		}

		public static int registerGUI(GuiScreen clientGui, GuiContainer serverGui) {
			Client_GUI_REG.put(nextGUI, clientGui);
			Server_GUI_REG.put(nextGUI, serverGui);
			return nextGUI++;
		}
	}

	public static class GuiContainer extends net.minecraft.client.gui.inventory.GuiContainer implements EasyGUI {

		public GuiContainer(Container inventorySlotsIn, World worldIn, BlockPos pos) {
			super(inventorySlotsIn);
		}

		public GuiContainer(Container inventorySlotsIn, TileEntity entity) {
			super(inventorySlotsIn);
		}

		@Override
		protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

		}

	}

	public static class GuiScreen extends net.minecraft.client.gui.GuiScreen implements EasyGUI {
		private boolean defualtBackground;
		private boolean guiPauseGame;
		private NonNullList<Object> elements = NonNullList.create();
		private NonNullList<GuiText> textList = NonNullList.create();

		public GuiScreen(boolean defaultBackground) {
			this(defaultBackground, false);
		}

		public GuiScreen(boolean defaultBackground, boolean guiPauseGame) {
			this.defualtBackground = defaultBackground;
			this.guiPauseGame = guiPauseGame;
		}

		public GuiScreen addElement(Object obj) {
			this.elements.add(obj);
			return this;
		}

		@Override
		public void initGui() {
			elements.forEach(e -> {
				if (e instanceof net.minecraft.client.gui.GuiButton)
					this.buttonList.add((net.minecraft.client.gui.GuiButton) e);
				else if (e instanceof GuiText)
					this.textList.add(((GuiText) e).setParent(this));
			});
		}

		@Override
		public boolean doesGuiPauseGame() {
			return this.guiPauseGame;
		}

		@Override
		public void drawScreen(int mouseX, int mouseY, float partialTicks) {
			if (this.defualtBackground)
				this.drawDefaultBackground();
			textList.forEach(text -> {
				text.drawString(this.fontRendererObj);
			});
			super.drawScreen(mouseX, mouseY, partialTicks);
		}

		public static class InventoryScreen extends GuiScreen {
			public InventoryScreen(boolean defaultBackground) {
				super(defaultBackground);
			}

			public InventoryScreen(boolean defaultBackground, boolean guiPauseGame) {
				super(defaultBackground, guiPauseGame);
			}

		}

		public static class TextScreen extends GuiScreen {
			private NonNullList<GuiText> textList = NonNullList.create();

			public TextScreen(boolean defaultBackground) {
				super(defaultBackground);
			}

			public TextScreen(boolean defaultBackground, boolean guiPauseGame) {
				super(defaultBackground, guiPauseGame);
			}

			public TextScreen addString(String string, float x, float y) {
				return this.addString(string, x, y, ColorCodes.BLACK);
			}

			public TextScreen addString(String string, float x, float y, int color) {
				return this.addString(string, x, y, color);
			}

			public TextScreen addString(String string, float x, float y, boolean shadow) {
				return this.addString(string, x, y, ColorCodes.BLACK, shadow);
			}

			public TextScreen addString(String string, float x, float y, int color, boolean shadow) {
				return this.addString(new GuiText(string, x, y, color, shadow));
			}

			public TextScreen addString(GuiText guiText) {
				this.textList.add(guiText);
				return this;
			}

		}
	}

	public static class GuiButton extends net.minecraft.client.gui.GuiButton implements EasyGUI {

		public GuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
			super(buttonId, x, y, widthIn, heightIn, buttonText);
		}

	}

	public static class GuiText implements EasyGUI {
		private String text;
		private float x;
		private float y;
		private int color;
		private boolean shadow;
		private GuiScreen guiParent;

		public GuiText(String text, float x, float y) {
			this(text, x, y, ColorCodes.BLACK, false);
		}

		public GuiText(String text, float x, float y, int color, boolean shadow) {
			this.text = text;
			this.x = x;
			this.y = y;
			this.color = color;
			this.shadow = shadow;
		}

		public void drawString(FontRenderer fontRenderer) {
			GuiHelper.drawString(fontRenderer, this.getText(fontRenderer), this.getX(fontRenderer),
					this.getY(fontRenderer), this.getColor(fontRenderer), this.withShadow(fontRenderer));
		}

		public String getText(FontRenderer fontRenderer) {
			return text;
		}

		public float getX(FontRenderer fontRenderer) {
			return x;
		}

		public float getY(FontRenderer fontRenderer) {
			return y;
		}

		public int getColor(FontRenderer fontRenderer) {
			return color;
		}

		public boolean withShadow(FontRenderer fontRenderer) {
			return shadow;
		}

		public GuiText setParent(GuiScreen guiParent) {
			this.guiParent = guiParent;
			return this;
		}

		public GuiScreen getParent() {
			return this.guiParent;
		}

		public static class ColorCodes implements EasyGUI {
			public static int BLACK = 0x000000;
			public static int WHITE = 0xFFFFFF;
			public static int DARKRED = 0x800000;
			public static int BRIGHTRED = 0xFF0000;
			public static int DARKGREEN = 0x008000;
			public static int BRIGHTGREEN = 0x00FF00;
			public static int DARKBLUE = 0x000080;
			public static int BRIGHTBLUE = 0x0000FF;
		}
	}

	public static class GuiHelper implements EasyGUI {

		public static void drawString(FontRenderer fontRenderer, String text, float x, float y, int color,
				boolean withShadow) {
			fontRenderer.drawString(text, x, y, color, withShadow);
		}

		public static int centerHeight(net.minecraft.client.gui.GuiScreen screen) {
			return screen.height / 2;
		}

		public static int centerWidth(net.minecraft.client.gui.GuiScreen screen) {
			return screen.width / 2;
		}

		/*
		 * Some usefule function is the GuiScreen Class drawRect
		 * drawTexturedModalRect drawModalRectWithCustomSizedTexture
		 */
	}
}
