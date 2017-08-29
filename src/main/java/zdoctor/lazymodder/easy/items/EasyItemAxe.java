package zdoctor.lazymodder.easy.items;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class EasyItemAxe extends ItemTool {
	protected static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG,
			Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER,
			Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);
	protected static final float[] ATTACK_DAMAGES = new float[] { 6.0F, 8.0F, 8.0F, 8.0F, 6.0F };
	protected static final float[] ATTACK_SPEEDS = new float[] { -3.2F, -3.2F, -3.1F, -3.0F, -3.0F };

	public EasyItemAxe(String toolName, int subCount, float attackDamageIn, float attackSpeedIn,
			Item.ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
		super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
	}

	public EasyItemAxe(String toolName, Item.ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
		this(toolName, 1, 0.0F, 0.0F, materialIn, effectiveBlocksIn);
	}

	/**
	 * Used for custom materials
	 */
	public EasyItemAxe(Item.ToolMaterial material, float damage, float speed) {
		super(material, EFFECTIVE_ON);
		this.damageVsEntity = damage;
		this.attackSpeed = speed;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE
				? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
	}

}
