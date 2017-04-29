package zdoctor.bloodbaubles.base;

import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import zdoctor.bloodbaubles.registry.BaubleRegistry;

/**
 * An abstract class that handles Bauble Registration and provides default
 * functionality. Used for quick Bauble creation. See {@link BaubleRegistry} for
 * more information
 * 
 * @author Z_Doctor
 */
public abstract class AutoBauble extends AutoItem implements IBauble {

  public AutoBauble(String nameIn) {
    super(nameIn);
    BaubleRegistry.registerBauble(this);
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn,
      World worldIn, EntityPlayer playerIn, EnumHand hand) {
    if (!worldIn.isRemote) {
      InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(playerIn);
      for (int i = 0; i < baubles.getSizeInventory(); i++) {
        if (baubles.getStackInSlot(i) == null
            && baubles.isItemValidForSlot(i, itemStackIn)) {
          baubles.setInventorySlotContents(i, itemStackIn.copy());
          if (!playerIn.capabilities.isCreativeMode) {
            playerIn.inventory
                .setInventorySlotContents(playerIn.inventory.currentItem, null);
          }
          onEquipped(itemStackIn, playerIn);
          break;
        }
      }
    }
    return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
  }

  @Override
  public boolean canEquip(ItemStack itemStackIn, EntityLivingBase playerIn) {
    return true;
  }

  @Override
  public boolean canUnequip(ItemStack itemStackIn, EntityLivingBase playerIn) {
    return true;
  }

  @Override
  public void onUnequipped(ItemStack itemStackIn, EntityLivingBase playerIn) {
  }

  @Override
  public void onWornTick(ItemStack itemStackIn, EntityLivingBase playerIn) {
  }

  @Override
  public void onEquipped(ItemStack itemStackIn, EntityLivingBase playerIn) {
    if (!playerIn.worldObj.isRemote) {
      playerIn.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.1F, 1.3f);
    }
  }
}
