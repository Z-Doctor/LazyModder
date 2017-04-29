package zmods.lazyapi.examples;

import zmods.lazyapi.easy.EasyEntity;
import zmods.lazyapi.easy.EasyEntity.TESR;
import zmods.lazyapi.easy.EasyEntity.EasyTileEntity.UpdateTileEntity;

public class Entity {
	public static void preInit() {
		// EasyEntity test = new EasyEntity("TestEntity", EasyTileEntity.class);
		EasyEntity testUpdate = new TESR("TestUpdateEntity", UpdateTileEntity.class);
		// InventoryEntity testInventory = new InventoryEntity("TestInventory");
		// InventoryEntity testSmallInventory = new
		// InventoryEntity("TestSmallInventory", 3).setMaxStackSize(16);
	}
}
