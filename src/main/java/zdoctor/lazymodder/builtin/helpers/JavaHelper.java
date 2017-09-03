package zdoctor.lazymodder.builtin.helpers;

import java.io.File;
import java.net.URISyntaxException;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ModContainer;

public class JavaHelper {
	/**
	 * potentially only used in dev mod
	 */
	public static File getLocationOfClass(Class class1) {
		try {
			return new File(class1.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Used to find get the jar of the mod, making it possible for self modification
	 */
	public static File getLocationOfMod(String modid) {
		ModContainer container = FMLCommonHandler.instance().findContainerFor(modid);
		if (container == null)
			return null;
		return container.getSource();
	}
}
