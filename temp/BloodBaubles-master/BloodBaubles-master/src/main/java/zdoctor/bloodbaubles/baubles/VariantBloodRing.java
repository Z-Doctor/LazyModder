package zdoctor.bloodbaubles.baubles;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import zdoctor.bloodbaubles.api.IVariant;
import zdoctor.bloodbaubles.baubles.BloodBauble.BloodRing;
import zdoctor.bloodbaubles.baubles.rings.GodsGift;

/**
 * This class is for blood rings that have variants depending on the meta. See
 * {@link GodsGift} for an example implementation. Variants are registered by
 * name, and are assigned a meta value in the order they come. It is completely
 * optional to use this class, but it makes life so much easier. If textures are
 * missing, make sure to read console and that the files exist.
 * 
 * @author Z_Doctor
 */
public abstract class VariantBloodRing extends BloodRing implements IVariant {

  List<ModelResourceLocation> variants = new ArrayList<>();

  List<String> variantNames = new ArrayList<>();

  public VariantBloodRing(String nameIn) {
    super(nameIn);
    this.setHasSubtypes(true);
  }

  @Override
  public String getUnlocalizedName(ItemStack stack) {
    return "item." + getVariantName(stack.getMetadata())
        + this.getRegistryName().getResourcePath();
  }

  @Override
  public void getSubItems(Item itemIn, CreativeTabs tab,
      List<ItemStack> subItems) {
    this.variantNames.forEach(varName -> {
      if (varName != null)
        subItems
            .add(new ItemStack(this, 1, this.variantNames.indexOf(varName)));
    });
  }

  @Override
  public void registerToGame() {
    super.registerToGame();
    variantNames.forEach(varName -> {
      if (varName != null)
        this.variants.add(
            new ModelResourceLocation(this.getRegistryName().getResourceDomain()
                + ":" + varName + this.getRegistryName().getResourcePath()));
    });
    ModelBakery.registerItemVariants(this,
        this.variants.toArray(new ResourceLocation[this.variants.size()]));
  }

  @Override
  public void registerRender() {
    this.variants.forEach(variant -> {
      ModelLoader.setCustomModelResourceLocation(this,
          this.variants.indexOf(variant), variant);
    });
  }

  @Override
  public void addVariant(String varName) {
    this.variantNames.add(varName);
  }

  @Override
  public void addVariant(String varName, int meta) {
    if (this.variantNames.size() - 1 < meta) {
      this.addVariant(null);
      while (this.variantNames.size() < meta)
        this.addVariant(null);
    }
    this.variantNames.set(meta, varName);
  }

  @Override
  public String getVariantName(int meta) {
    return this.variantNames.get(meta);
  }

  @Override
  public int getVariantMeta(String varName) {
    return variantNames.indexOf(varName);
  }

  @Override
  public void forEachVariant(BiConsumer<Integer, String> action) {
    this.forEachVariant(this.variantNames, action);
  }
}
