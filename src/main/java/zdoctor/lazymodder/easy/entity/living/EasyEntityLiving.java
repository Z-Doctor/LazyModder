package zdoctor.lazymodder.easy.entity.living;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import zdoctor.lazymodder.easy.interfaces.IRenderLiving;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

/**
 * Used to create entities with models. Can also create eggs for them and
 * existing model. If the renderer is null, the client will used an previously
 * associated model with the class.
 * 
 * 
 * @author Z_Doctor
 */
public class EasyEntityLiving {

	private boolean hasEgg = false;
	private int primaryColor = 0;
	private int secondaryColor = 0;

	private int trackingRange = 48;
	private int updateFrequenc = 3;
	private boolean sendsVelocityUpdates = true;

	private String entityName;

	private ResourceLocation registryName;
	private Class<? extends EntityLiving> entityClass;

	public EasyEntityLiving(String entityName, Class<? extends EntityLiving> entityClass) {
		this(entityName, entityClass, 0, 0);
	}

	public EasyEntityLiving(String entityName, Class<? extends EntityLiving> entityClass, int primaryColor, int secondaryColor) {
		this(entityName, entityClass, 48, 3, true, primaryColor, secondaryColor, true);
	}

	public EasyEntityLiving(String entityName, Class<? extends EntityLiving> entityClass, int trackingRange, int updateFrequenc, boolean sendsVelocityUpdates) {
		this(entityName, entityClass, trackingRange, updateFrequenc, sendsVelocityUpdates, 0, 0, false);
	}

	/**
	 * Used to register entities to the game. If entities implent {@link IRenderLiving}
	 * then their renderer will be automatically created
	 * 
	 * @param entityName
	 * @param entityClass
	 * @param trackingRange
	 * @param updateFrequenc
	 * @param sendsVelocityUpdates
	 * @param primaryColor
	 * @param secondaryColor
	 * @param hasEgg
	 */
	public EasyEntityLiving(String entityName, Class<? extends EntityLiving> entityClass, int trackingRange, int updateFrequenc, boolean sendsVelocityUpdates,
			int primaryColor, int secondaryColor, boolean hasEgg) {
		this.entityName = entityName;
		this.entityClass = entityClass;

		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;

		this.trackingRange = trackingRange;
		this.updateFrequenc = updateFrequenc;
		this.sendsVelocityUpdates = sendsVelocityUpdates;

		this.hasEgg = hasEgg;

		registryName = new ResourceLocation(Loader.instance().activeModContainer().getModId(), entityName);
		EasyRegistry.register(this);
	}

	public ResourceLocation getRegistryName() {
		return registryName;
	}

	public Class<? extends EntityLiving> getEntityClass() {
		return entityClass;
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

}
