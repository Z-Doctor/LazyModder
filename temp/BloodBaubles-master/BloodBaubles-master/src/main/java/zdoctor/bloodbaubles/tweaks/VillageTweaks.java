package zdoctor.bloodbaubles.tweaks;

import java.util.Random;
import java.util.function.Consumer;

import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import zdoctor.bloodbaubles.References;
import zdoctor.bloodbaubles.init.Rings;

public class VillageTweaks {

  public VillageTweaks() {
    new PriestBlessing();
  }

  private static class PriestBlessing implements ITradeList {

    public PriestBlessing() {
      System.out.println("Blessing");
      forEachProfession(job -> {
        if (job.getRegistryName().getResourcePath().equals("priest")) {
          VillagerCareer priest = job.getCareer(1);
          priest.addTrade(1, this);
        }
      });
    }

    @Override
    public void modifyMerchantRecipeList(MerchantRecipeList recipeList,
        Random random) {
      recipeList.add(new MerchantRecipe(new ItemStack(Rings.GodsGift, 1, 0),
          new ItemStack(Items.EMERALD, References.Blessing_Cost, 0),
          new ItemStack(Rings.GodsGift, 1, 1)));
    }
  }

  public static void forEachProfession(Consumer<VillagerProfession> action) {
    for (VillagerProfession job : getVillageRegistry().getValues()) {
      action.accept(job);
    }
  }

  public static IForgeRegistry<VillagerProfession> getVillageRegistry() {
    return VillagerRegistry.instance().getRegistry();
  }
}
