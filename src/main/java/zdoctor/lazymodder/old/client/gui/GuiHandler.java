package zdoctor.lazymodder.old.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * This class is used to create a GuiHandler. GUI IDs are handled internally
 * but can be fetched from the class.
 */
public class GuiHandler implements IGuiHandler {
	static Map<String, ArrayList<GuiHandler>> GUI_MAP = new HashMap<>();
	private int uid;
	private Class<? extends GuiScreen> clientGui;
	private Class<? extends Container> serverGui;
	
	public GuiHandler(Class<? extends GuiScreen> clientGui) {
		this.serverGui = null;
		this.clientGui = clientGui;
		
		String mod = Loader.instance().activeModContainer().getModId();
		ArrayList<GuiHandler> guiList = GUI_MAP.putIfAbsent(mod, new ArrayList<>());
		guiList = guiList == null ? GUI_MAP.get(mod) : guiList;
		
		uid = guiList.size();
		guiList.add(this);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(mod, this);
	}
	
	public GuiHandler(Class<? extends Container> serverGui, Class<? extends GuiContainer> clientGui) {
		this.serverGui = serverGui;
		this.clientGui = clientGui;
		
		String mod = Loader.instance().activeModContainer().getModId();
		ArrayList<GuiHandler> guiList = GUI_MAP.putIfAbsent(mod, new ArrayList<>());
		guiList = guiList == null ? GUI_MAP.get(mod) : guiList;
		
		uid = guiList.size();
		guiList.add(this);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(mod, this);
	}
	
	public GuiHandler setServerContainer(Class<? extends Container> serverGui) {
		this.serverGui = serverGui;
		return this;
	}
	
	public int getUID() {
		return uid;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == uid && serverGui != null)
			try {
				return serverGui.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} 
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == uid && clientGui != null)
			try {
				return clientGui.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} 
		return null;
	}
	
	public static int getGuiID(GuiHandler handler) {
		for (ArrayList<GuiHandler> guiList : GUI_MAP.values()) {
			if(guiList.contains(handler))
				return guiList.indexOf(handler);
		};
		return -1;
	}

}
