package zmods.lazyapi.easy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EasyTESR extends TileEntitySpecialRenderer {
	private static Map<Class<? extends TileEntity>, TileEntitySpecialRenderer> tESRList = new HashMap<>();
	private Class<? extends TileEntity> tileEntity;
	
	public EasyTESR() {
//		this.tileEntity = tileEntity;
	}

	public static void addTESR(Class<? extends TileEntity> tileEntity, TileEntitySpecialRenderer specialRenderer) {
		tESRList.put(tileEntity, specialRenderer);
	}
	
	public Class<? extends TileEntity> getTileEntity() {
		return this.tileEntity;
	}
	/**
	 * Should always be called before rendering
	 */
	public void startRender(double x, double y, double z) {
		GlStateManager.pushMatrix();
	    GlStateManager.translate(x, y, z);
	}
	
	public void endRender() {
		GlStateManager.popMatrix();
	}
	
	public void renderTileEntity(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
		// TODO Auto-generated method stub
		
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerEasyTESR() {
		tESRList.forEach((tileEntity, specialRenderer) -> {
			ClientRegistry.bindTileEntitySpecialRenderer(tileEntity, specialRenderer);
		});
		tESRList.clear();
	}

	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		this.startRender(x, y, z);
//		GlStateManager.translate(x, y, z);
		this.renderTileEntity(te, x, y, z, partialTicks, destroyStage);
		this.endRender();
	}

	@Override
	public void renderTileEntityFast(TileEntity te, double x, double y, double z, float partialTicks,
			int destroyStage, VertexBuffer buffer) {
		this.startRender(x, y, z);
		this.renderTileEntity(te, x, y, z, partialTicks, destroyStage);
		this.endRender();
	}
}