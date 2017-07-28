package zdoctor.lazymodder.examples.test;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TestSpiderGolem extends EntityIronGolem {

	public TestSpiderGolem(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		this.tasks.addTask(1, new EntityAISwimming(this));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
	}

	@Override
	protected int decreaseAirSupply(int air) {
		return air - 1;
	}

	public static class RenderSpiderGoldem<T extends EntityLiving> extends RenderLiving<T> {
		private static final ResourceLocation SPIDER_TEXTURES = new ResourceLocation(
				"textures/entity/spider/spider.png");

		public RenderSpiderGoldem(RenderManager renderManagerIn) {
			super(renderManagerIn, new ModelSpider(), 1.0F);
			this.addLayer(new LayerSpiderEyes(this));
		}

		protected float getDeathMaxRotation(T entityLivingBaseIn) {
			return 180.0F;
		}

		/**
		 * Returns the location of an entity's texture. Doesn't seem to be
		 * called unless you call Render.bindEntityTexture.
		 */
		protected ResourceLocation getEntityTexture(T entity) {
			return SPIDER_TEXTURES;
		}
	}

	public static class LayerSpiderEyes<T extends EntityLiving> implements LayerRenderer<T> {
		private static final ResourceLocation SPIDER_EYES = new ResourceLocation("textures/entity/spider_eyes.png");
		private final RenderSpiderGoldem<T> spiderRenderer;

		public LayerSpiderEyes(RenderSpiderGoldem<T> spiderRendererIn) {
			this.spiderRenderer = spiderRendererIn;
		}

		public void doRenderLayer(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks,
				float ageInTicks, float netHeadYaw, float headPitch, float scale) {
			this.spiderRenderer.bindTexture(SPIDER_EYES);
			GlStateManager.enableBlend();
			GlStateManager.disableAlpha();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);

			if (entitylivingbaseIn.isInvisible()) {
				GlStateManager.depthMask(false);
			} else {
				GlStateManager.depthMask(true);
			}

			int i = 61680;
			int j = i % 65536;
			int k = i / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j, (float) k);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
			this.spiderRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks,
					netHeadYaw, headPitch, scale);
			Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
			i = entitylivingbaseIn.getBrightnessForRender();
			j = i % 65536;
			k = i / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j, (float) k);
			this.spiderRenderer.setLightmap(entitylivingbaseIn);
			GlStateManager.disableBlend();
			GlStateManager.enableAlpha();
		}

		public boolean shouldCombineTextures() {
			return false;
		}
	}

}
