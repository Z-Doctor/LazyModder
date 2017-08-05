package zdoctor.lazymodder.examples;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import zdoctor.lazymodder.ModMain;
import zdoctor.lazymodder.easy.items.EasyItem;
import zdoctor.lazymodder.examples.GUIs.Test;

public class ZItems {

	public static EasyItem testItem;

	public static void preInit() {
		Item guiItem = new EasyItem("GuiTest") {
			public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
				playerIn.openGui(ModMain.mod, 0, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
//				Test test = new GUIs.Test();
//				Minecraft.getMinecraft().displayGuiScreen(test);
				return super.onItemRightClick(worldIn, playerIn, handIn);
			};
		}.setCreativeTab(CreativeTabs.TOOLS);
//		testItem = new EasyItem("TestItem");
//		Item testAxe = new EasyTools.EasyAxe("TestAxe", ToolMaterial.DIAMOND);
//		Item testPick = new EasyTools.EasyPickaxe("TestPick", ToolMaterial.DIAMOND);
//		Item testSpade = new EasyTools.EasySpade("TestSpade", ToolMaterial.DIAMOND);
//		Item testHoe = new EasyTools.EasyHoe("TestHoe", ToolMaterial.DIAMOND);
//		Item testPickAxe = new EasyTools.EasyHybrid.EasyPickAxe("PickAxe", ToolMaterial.DIAMOND);
//		Item testPickSpade = new EasyTools.EasyHybrid.EasyPickSpade("PickSpade", ToolMaterial.DIAMOND);
//		Item testAxeSpade = new EasyTools.EasyHybrid.EasyAxeSpade("AxeSpade", ToolMaterial.DIAMOND);
//		Item omni = new EasyTools.EasyHybrid.EasyOmniTool("Omni", ToolMaterial.DIAMOND);
//		Item trueOmni = new EasyTools.EasyHybrid.EasyOmniTool("TrueOmni", ToolMaterial.DIAMOND, true);
//		
//		Map<EntityEquipmentSlot, ItemArmor> testArmor = EasyArmor.createArmorSet("TestArmor", ArmorMaterial.DIAMOND);
	}
	
}
