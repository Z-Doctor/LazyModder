package zdoctor.lazymodder.easy;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EasyFunctions {

	public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
		GameRegistry.registerTileEntity(tileEntityClass, id);
	}

	public static void addPotionEffect(EntityLivingBase entity, PotionEffect effect) {
		entity.addPotionEffect(effect);
	}

	public static void addPotionEffectWithChance(EntityLivingBase entity, World world, PotionEffect effect,
			Float chance) {
		if (world.rand.nextFloat() < chance) {
			addPotionEffect(entity, effect);
		}
	}

}
