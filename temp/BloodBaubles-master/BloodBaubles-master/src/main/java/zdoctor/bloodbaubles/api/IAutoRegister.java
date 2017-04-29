package zdoctor.bloodbaubles.api;

import zdoctor.bloodbaubles.registry.BaubleRegistry;

/**
 * 
 * This interface is to register automated registering and rendering See
 * {@link BaubleRegistry} for an example implementation
 *
 * @author Z_Doctor
 */
public interface IAutoRegister {

  public void registerToGame();

  public void registerRender();
}
