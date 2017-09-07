package zdoctor.lazymodder.easy.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.properties.IProperty;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * 
 * Created to fix errors associated with using client side classes
 */
public class EasyStateMap {
	public static class Builder {
		private IProperty<?> name;
		private String suffix;
		private final List<IProperty<?>> ignored = Lists.<IProperty<?>>newArrayList();

		public EasyStateMap.Builder withName(IProperty<?> builderPropertyIn) {
			this.name = builderPropertyIn;
			return this;
		}

		public EasyStateMap.Builder withSuffix(String builderSuffixIn) {
			this.suffix = builderSuffixIn;
			return this;
		}

		/**
		 * Add properties that will not be used to compute all possible states
		 * of a block, used for block rendering to ignore some property that
		 * does not alter block's appearance
		 */
		public EasyStateMap.Builder ignore(IProperty<?>... p_178442_1_) {
			Collections.addAll(this.ignored, p_178442_1_);
			return this;
		}

		public Object build() {
			if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
				try {
					Class<?> clazz = Class.forName("net.minecraft.client.renderer.block.statemap.StateMap");
					Constructor<?> constructor = clazz.getConstructor(IProperty.class, String.class, List.class);
					return constructor.newInstance(this.name, this.suffix, this.ignored);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException
						| ClassNotFoundException e) {
					e.printStackTrace();
				}
				// return new StateMap(this.name, this.suffix, this.ignored);
			}
			return null;
		}
	}
}
