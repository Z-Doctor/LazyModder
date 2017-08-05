package zdoctor.lazymodder.registery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import zdoctor.lazymodder.easy.entity.EasyLivingEntity;

public class EntityRegistry {
	static ArrayList<EasyLivingEntity> entityList = new ArrayList<>();
	
	static Map<String, Integer> UID = new HashMap<>();

	public static void registerEntities() {
//		entityList.forEach(entity -> {
//			String mod = entity.getRegistryName().getResourceDomain();
//			Integer temp = UID.putIfAbsent(mod, 0);
//			int id = temp == null ? 0 : temp.intValue();
//			UID.put(mod, ++id);
//			
//			if (entity.hasEgg())
//				net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entity.getRegistryName(),
//						entity.getEntityClass(), entity.getRegistryName().getResourcePath(), id, mod,
//						entity.getTrackingRange(), entity.getUpdateFrequency(), entity.sendsVelocityUpdates(),
//						entity.getPrimaryEggColor(), entity.getSecondaryEggColor());
//			else
//				net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entity.getRegistryName(),
//						entity.getEntityClass(), entity.getRegistryName().getResourcePath(), id, mod,
//						entity.getTrackingRange(), entity.getUpdateFrequency(), entity.sendsVelocityUpdates());
//		});
	}

	public static void register(EasyLivingEntity entity) {
		System.out.println("Entity Added: " + entity.getRegistryName());
//		entityList.add(entity);
		
		String mod = entity.getRegistryName().getResourceDomain();
		Integer temp = UID.putIfAbsent(mod, 0);
		int id = temp == null ? 0 : temp.intValue();
		UID.put(mod, ++id);
		
		if (entity.hasEgg())
			net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entity.getRegistryName(),
					entity.getEntityClass(), entity.getRegistryName().getResourcePath(), id, mod,
					entity.getTrackingRange(), entity.getUpdateFrequency(), entity.sendsVelocityUpdates(),
					entity.getPrimaryEggColor(), entity.getSecondaryEggColor());
		else
			net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entity.getRegistryName(),
					entity.getEntityClass(), entity.getRegistryName().getResourcePath(), id, mod,
					entity.getTrackingRange(), entity.getUpdateFrequency(), entity.sendsVelocityUpdates());
		
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT && entity.getEntityRenderer() != null) {
			System.out.println("Client side");
			RenderRegistry.registerEntityRenderingHandler(entity.getEntityClass(), entity.getEntityRenderer());
		}
	}

	public static void addSpawn(Class<? extends EntityLiving> entityClass, int weightedProb, int min, int max,
			EnumCreatureType typeOfCreature, Biome[] biomes) {
		net.minecraftforge.fml.common.registry.EntityRegistry.addSpawn(entityClass, weightedProb, min, max, typeOfCreature, biomes);
	}
}
