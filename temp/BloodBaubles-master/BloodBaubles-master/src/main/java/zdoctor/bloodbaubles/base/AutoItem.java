package zdoctor.bloodbaubles.base;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zdoctor.bloodbaubles.api.IAutoRegister;
import zdoctor.bloodbaubles.registry.ZGameRegistry;

public class AutoItem extends Item implements IAutoRegister {

  public AutoItem(String nameIn) {
    this.setRegistryName(nameIn);
    this.setUnlocalizedName(nameIn);
    ZGameRegistry.registerAuto(this);
  }

  @Override
  public void registerToGame() {
    GameRegistry.register(this);
  }

  @Override
  public void registerRender() {
    ModelLoader.setCustomModelResourceLocation(this, 0,
        new ModelResourceLocation(this.getRegistryName().toString()));
  }
}
