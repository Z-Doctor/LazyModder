package zdoctor.lazymodder.examples;

import zdoctor.lazymodder.easy.EasyEntity;
import zdoctor.lazymodder.easy.EasyEntity.TESR;
import zdoctor.lazymodder.easy.EasyEntity.EasyTileEntity.UpdateTileEntity;

public class Entity {
	public static void preInit() {
		// EasyEntity test = new EasyEntity("TestEntity", EasyTileEntity.class);
		EasyEntity testUpdate = new TESR("TestUpdateEntity", UpdateTileEntity.class);
		// InventoryEntity testInventory = new InventoryEntity("TestInventory");
		// InventoryEntity testSmallInventory = new
		// InventoryEntity("TestSmallInventory", 3).setMaxStackSize(16);
	}
}
