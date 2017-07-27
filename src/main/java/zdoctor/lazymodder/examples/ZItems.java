package zdoctor.lazymodder.examples;

import java.util.Map;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import zdoctor.lazymodder.easy.items.EasyArmor;
import zdoctor.lazymodder.easy.items.EasyItem;
import zdoctor.lazymodder.easy.items.EasyTools;

public class ZItems {

	public static EasyItem testItem;

	public static void preInit() {
		testItem = new EasyItem("TestItem");
		Item testAxe = new EasyTools.EasyAxe("TestAxe", ToolMaterial.DIAMOND);
		Item testPick = new EasyTools.EasyPickaxe("TestPick", ToolMaterial.DIAMOND);
		Item testSpade = new EasyTools.EasySpade("TestSpade", ToolMaterial.DIAMOND);
		Item testHoe = new EasyTools.EasyHoe("TestHoe", ToolMaterial.DIAMOND);
		Item testPickAxe = new EasyTools.EasyHybrid.EasyPickAxe("PickAxe", ToolMaterial.DIAMOND);
		Item testPickSpade = new EasyTools.EasyHybrid.EasyPickSpade("PickSpade", ToolMaterial.DIAMOND);
		Item testAxeSpade = new EasyTools.EasyHybrid.EasyAxeSpade("AxeSpade", ToolMaterial.DIAMOND);
		Item omni = new EasyTools.EasyHybrid.EasyOmniTool("Omni", ToolMaterial.DIAMOND);
		Item trueOmni = new EasyTools.EasyHybrid.EasyOmniTool("TrueOmni", ToolMaterial.DIAMOND, true);
		
		Map<EntityEquipmentSlot, ItemArmor> testArmor = EasyArmor.createArmorSet("TestArmor", ArmorMaterial.DIAMOND);
	}
	
}
