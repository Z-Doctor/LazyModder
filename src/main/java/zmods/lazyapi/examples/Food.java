package zmods.lazyapi.examples;

import net.minecraft.item.Item;
import zmods.lazyapi.easy.EasyFood;

public class Food {
	public static void preInit() {
		Item testFood = new EasyFood("TestFood", 10, 1F, true);
		Item testPoorFood = new EasyFood("TestPoorFood", 1, .2F);
		Item testDrink = new EasyFood.EasyDrink("TestDrink", 1, 1f);
	}
}
