package zdoctor.lazymodder.builtin.helpers;

import net.minecraft.entity.EntityLiving;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.EntityDataManager;

public class EntityDataHelper {

	public static <T> DataParameter <T> createKey(Class<? extends EntityLiving> entityClassIn, DataSerializer<T> dataSerializer) {
		return EntityDataManager.<T>createKey(entityClassIn, dataSerializer);
	}

}
