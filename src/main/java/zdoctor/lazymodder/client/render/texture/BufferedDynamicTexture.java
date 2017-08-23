package zdoctor.lazymodder.client.render.texture;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;

import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;

public class BufferedDynamicTexture extends DynamicTexture {
	private int[] dynamicTextureData;
	/** width of this icon in pixels */
	private int width;
	/** height of this icon in pixels */
	private int height;

	public BufferedDynamicTexture(BufferedImage bufferedImage) {
		this(bufferedImage.getWidth(), bufferedImage.getHeight());
		bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), this.dynamicTextureData, 0,
				bufferedImage.getWidth());
		this.updateDynamicTexture();
	}

	public BufferedDynamicTexture(int textureWidth, int textureHeight) {
		super(textureWidth, textureHeight);
		this.width = textureWidth;
		this.height = textureHeight;
		this.dynamicTextureData = new int[textureWidth * textureHeight];
		TextureUtil.allocateTexture(this.getGlTextureId(), textureWidth, textureHeight);
	}

	public void updateDynamicTexture(int width, int height, int[] textureData) {
		this.width = width;
		this.height = height;
		this.dynamicTextureData = textureData;
		TextureUtil.allocateTexture(this.getGlTextureId(), this.width, this.height);
		this.updateDynamicTexture();
	}

	@Override
	public int[] getTextureData() {
		return this.dynamicTextureData;
	}
	
	@Override
	public void updateDynamicTexture() {
		TextureUtil.uploadTexture(this.getGlTextureId(), this.dynamicTextureData, this.width, this.height);
	}
	
	

}
