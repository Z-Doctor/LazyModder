package zdoctor.bloodbaubles.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface CommonProxy {

  public void preInit(FMLPreInitializationEvent e);

  public void init(FMLInitializationEvent e);

  public void postInit(FMLPostInitializationEvent e);
}
