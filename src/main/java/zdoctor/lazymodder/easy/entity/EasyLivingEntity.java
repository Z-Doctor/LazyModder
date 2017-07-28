package zdoctor.lazymodder.easy.entity;

import java.util.ArrayList;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.common.Loader;
import zdoctor.lazymodder.client.model.ModelMushromoid;
import zdoctor.lazymodder.registery.EntityRegistry;
/**
 * Used to create entities with models. Can also create eggs for them and existing model.
 * If the renderer is null, the client will used an previously associated model with the class.s
 * 
 * 
 * @author Z_Doctor
 */
public class EasyLivingEntity {

	private boolean hasEgg = false;
	private int primaryColor = 0;
	private int secondaryColor = 0;

	private int trackingRange = 48;
	private int updateFrequenc = 3;
	private boolean sendsVelocityUpdates = true;

	private String entityName;

	private ResourceLocation registryName;

	private Class<? extends EntityLiving> entityClass;
	private Class<? extends RenderLiving> entityRenderer;

	public EasyLivingEntity(Class<? extends EntityLiving> entityClass, Class<? extends RenderLiving> entityRenderer,
			String entityName) {
		this.entityClass = entityClass;
		this.entityName = entityName;

		this.entityRenderer = entityRenderer;

		registryName = new ResourceLocation(Loader.instance().activeModContainer().getModId(), entityName);

		EntityRegistry.register(this);
	}

	public EasyLivingEntity addEgg(int primaryColor, int secondaryColor) {
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
		hasEgg = true;

		return this;
	}

	public EasyLivingEntity updateValues(int trackingRange, int updateFrequenc, boolean sendsVelocityUpdates) {
		this.trackingRange = trackingRange;
		this.updateFrequenc = updateFrequenc;
		this.sendsVelocityUpdates = sendsVelocityUpdates;

		return this;
	}

	public ResourceLocation getRegistryName() {
		return registryName;
	}

	public Class<? extends EntityLiving> getEntityClass() {
		return entityClass;
	}

	public Class<? extends RenderLiving> getEntityRenderer() {
		return entityRenderer;
	}

	public boolean hasEgg() {
		return hasEgg;
	}

	public int getTrackingRange() {
		return trackingRange;
	}

	public int getUpdateFrequency() {
		return updateFrequenc;
	}

	public boolean sendsVelocityUpdates() {
		return sendsVelocityUpdates;
	}

	public int getPrimaryEggColor() {
		return primaryColor;
	}

	public int getSecondaryEggColor() {
		return secondaryColor;
	}

	/**
	 * Add a spawn entry for the supplied entity in the supplied {@link Biome}
	 * list
	 * 
	 * @param weightedProb
	 *            Probability
	 * @param min
	 *            Min spawn count
	 * @param max
	 *            Max spawn count
	 * @param typeOfCreature
	 *            Type of spawn
	 * @param biomes
	 *            List of biomes
	 */
	public void registerNaturalSpawning(int weightedProb, int min, int max, EnumCreatureType typeOfCreature,
			Biome... biomes) {
		EntityRegistry.addSpawn(getEntityClass(), weightedProb, min, max, typeOfCreature, biomes);
	}

	/**
	 * Custom entities should extend this class or directly extend EntityLiving
	 * or one of the other classes.
	 * 
	 * These classes should be used to code mob behavior and interaction. This
	 * class created out of convenience to explain.
	 *
	 */
	public static class CustomLivingEntity extends EntityLiving {

		public CustomLivingEntity(World worldIn) {
			super(worldIn);
		}

		/**
		 * Where the ai is added into tasks.
		 */
		@Override
		protected void initEntityAI() {
			// tasks.addTask(priority, task);
		}

		/**
		 * Where the ai is updated by code
		 */
		@Override
		protected void updateAITasks() {

		}
		
		/**
		 * Where to add modifiers to health, speed, etc
		 */
		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
		}
		
		//Check in existing models and parent class for more examples
	}

	/**
	 * Custom models should extend this class or directly extend RenderLiving.
	 * This class should be used to code mob rendering. This class created out
	 * of convenience to explain.
	 *
	 * The new class should have a constructor with only the RenderManager, and
	 * hard code the model class and shadow size in.
	 */
	public static abstract class CustomRenderLiving extends RenderLiving {
		public CustomRenderLiving(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
			super(rendermanagerIn, modelbaseIn, shadowsizeIn);
		}

	}

	public static class CustomModel extends ModelBase {

	}

}
