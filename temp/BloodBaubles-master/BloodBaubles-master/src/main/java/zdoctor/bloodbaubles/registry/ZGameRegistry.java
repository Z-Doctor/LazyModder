package zdoctor.bloodbaubles.registry;

import java.util.ArrayList;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zdoctor.bloodbaubles.api.IAutoRecipe;
import zdoctor.bloodbaubles.api.IAutoRegister;

/**
 * An ArrayList<IAutoReister> is created and new objects are added through
 * {@code registerBauble(IBauble bauble)}. It is recommended that you make sure
 * objects being registered are the ones you want to be registered.
 * 
 * @author Z_Doctor
 */
public final class ZGameRegistry {

  private static ArrayList<IAutoRegister> Auto_REGISTRY = new ArrayList<>();

  private static ArrayList<IAutoRecipe> Recipe_REGISTRY = new ArrayList<>();

  public static void registerGameItems() {
    Auto_REGISTRY.forEach(auto -> {
      auto.registerToGame();
    });
  }

  public static void registerGameRecipes() {
    Recipe_REGISTRY.forEach(auto -> {
      auto.registerRecipe();
    });
  }

  @SideOnly(Side.CLIENT)
  public static void registerGameRenders() {
    Auto_REGISTRY.forEach(auto -> {
      auto.registerRender();
    });
  }

  public static void registerAuto(Object o) {
    if (o instanceof IAutoRegister)
      Auto_REGISTRY.add((IAutoRegister) o);
    if (o instanceof IAutoRecipe)
      Recipe_REGISTRY.add((IAutoRecipe) o);
  }
}
