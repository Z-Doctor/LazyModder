package zdoctor.lazymodder.easy.items;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

/**
 * 
 * @author Z_Doctor
 */
public class EasyItemTool extends ItemTool implements IEasyRegister {
	protected int subCount;

	public EasyItemTool(String toolName, int subCount, float attackDamageIn, float attackSpeedIn, Item.ToolMaterial materialIn,
			Set<Block> effectiveBlocksIn) {
		super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
		setUnlocalizedName(toolName);
		setRegistryName(toolName);
		
		this.subCount = subCount;
	}

	public EasyItemTool(String toolName, Item.ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
		this(toolName, 1, 0.0F, 0.0F, materialIn, effectiveBlocksIn);
	}
	

	@Override
	public String getNameFromMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	@Override
	public String getRegistryNameForMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	@Override
	public int getSubCount() {
		return subCount;
	}

}