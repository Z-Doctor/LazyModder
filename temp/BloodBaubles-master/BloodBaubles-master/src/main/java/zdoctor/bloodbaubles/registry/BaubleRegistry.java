package zdoctor.bloodbaubles.registry;

import java.util.ArrayList;
import java.util.function.Consumer;

import baubles.api.IBauble;

/**
 * An ArrayList<IAutoReister> is created and new objects are added through
 * {@code registerBauble(IBauble bauble)}. It is recommended that you make sure
 * objects being registered are the ones you want to be registered.
 * 
 * @author Z_Doctor
 */
public final class BaubleRegistry {

  private static ArrayList<IBauble> Bauble_REGISTRY = new ArrayList<>();

  public static void registerBauble(IBauble bauble) {
    Bauble_REGISTRY.add(bauble);
  }

  public static void forEach(Consumer<IBauble> action) {
    for (IBauble bauble : Bauble_REGISTRY)
      action.accept(bauble);
  }

  public static int indexOf(Object o) {
    return Bauble_REGISTRY.indexOf(o);
  }
}
