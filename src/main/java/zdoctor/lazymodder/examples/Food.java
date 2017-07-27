package zdoctor.lazymodder.examples;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import zdoctor.lazymodder.ModMain;
import zdoctor.lazymodder.easy.items.EasyFood;

public class Food {
	public static void preInit() {
		if(ModMain.DEV_ENV) {
			Item testFood = new EasyFood("TestFood", 10, 1F, true);
			Item testPoorFood = new EasyFood("TestPoorFood", 1, .2F);
			Item testDrink = new EasyFood.EasyDrink("TestDrink", 1, 1f) {
				
				@Override
				public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
					// TODO Auto-generated method stub
					return new ItemStack(Items.BUCKET);
				}
				
			}.setAlwaysEdible().setMaxStackSize(1);
		}
	}
}
