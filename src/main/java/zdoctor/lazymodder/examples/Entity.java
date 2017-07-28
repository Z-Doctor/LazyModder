package zdoctor.lazymodder.examples;

import zdoctor.lazymodder.easy.entity.EasyLivingEntity;
import zdoctor.lazymodder.entity.living.EntityMushromoid;
import zdoctor.lazymodder.examples.test.TestSpiderGolem;
import zdoctor.lazymodder.renderer.RenderMushromid;

public class Entity {
	public static void preInit() {
		// EasyEntity test = new EasyEntity("TestEntity", EasyTileEntity.class);
		// EasyEntity testUpdate = new TESR("TestUpdateEntity",
		// UpdateTileEntity.class);
		// InventoryEntity testInventory = new InventoryEntity("TestInventory");
		// InventoryEntity testSmallInventory = new
		// InventoryEntity("TestSmallInventory", 3).setMaxStackSize(16);

		EasyLivingEntity testEntity3 = new EasyLivingEntity(TestSpiderGolem.class,
				TestSpiderGolem.RenderSpiderGoldem.class, "Test3").addEgg(0, 16777215);

		// EasyLivingEntity mushroomoid = new
		// EasyLivingEntity(EntityMushromoid.class, RenderMushromid.class,
		// "Mushromoid").addEgg(0, 16777215);
	}
}
