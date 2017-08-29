package zdoctor.lazymodder.easy.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zdoctor.lazymodder.easy.blocks.EasyMultiBlock;

public class EasyItemMultiBlock extends EasyItemBlock {

	private EasyMultiBlock multiBlock;

	public EasyItemMultiBlock(EasyMultiBlock multiBlock) {
		super(multiBlock, 1);
		this.multiBlock = multiBlock;
	}
	
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ, IBlockState newState) {
		if (!world.setBlockState(pos, newState, 11))
			return false;
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == this.block) {
			setTileEntityNBT(world, player, pos, stack);
			this.block.onBlockPlacedBy(world, pos, state, player, stack);

			if (player instanceof EntityPlayerMP)
				CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos, stack);
		}

		return true;
	}

}
