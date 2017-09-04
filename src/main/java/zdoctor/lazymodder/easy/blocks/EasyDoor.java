package zdoctor.lazymodder.easy.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.items.EasyItemDoor;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

public class EasyDoor extends BlockDoor implements IEasyRegister {
	protected ItemDoor itemDoor;

	protected boolean powerOpens;

	public EasyDoor(String name) {
		this(name, false, Material.IRON);
	}

	public EasyDoor(String name, boolean powerOpens, Material materialIn) {
		super(materialIn);
		this.isBlockContainer = true;
		setUnlocalizedName(name);
		setRegistryName(name);
		this.powerOpens = powerOpens;
		EasyRegistry.register(this);
		itemDoor = createItem();
	}

	public ItemDoor createItem() {
		return new EasyItemDoor(this);
	}

	@Override
	public Block setCreativeTab(CreativeTabs tab) {
		itemDoor.setCreativeTab(tab);
		return super.setCreativeTab(tab);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return false;
	}

	public void playOpenSound(World worldIn, BlockPos pos, boolean open) {

	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER) {
			BlockPos blockpos = pos.down();
			IBlockState iblockstate = worldIn.getBlockState(blockpos);

			if (iblockstate.getBlock() != this) {
				worldIn.setBlockToAir(pos);
			} else if (blockIn != this) {
				iblockstate.neighborChanged(worldIn, blockpos, blockIn, fromPos);
			}
		} else {
			boolean flag1 = false;
			BlockPos blockpos1 = pos.up();
			IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

			if (iblockstate1.getBlock() != this) {
				worldIn.setBlockToAir(pos);
				flag1 = true;
			}

			if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP)) {
				worldIn.setBlockToAir(pos);
				flag1 = true;

				if (iblockstate1.getBlock() == this) {
					worldIn.setBlockToAir(blockpos1);
				}
			}

			if (flag1) {
				if (!worldIn.isRemote) {
					this.dropBlockAsItem(worldIn, pos, state, 0);
				}
			} else if (powerOpens) {
				boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(blockpos1);

				if (blockIn != this && (flag || blockIn.getDefaultState().canProvidePower())
						&& flag != ((Boolean) iblockstate1.getValue(POWERED)).booleanValue()) {
					worldIn.setBlockState(blockpos1, iblockstate1.withProperty(POWERED, Boolean.valueOf(flag)), 2);

					if (flag != ((Boolean) state.getValue(OPEN)).booleanValue()) {
						worldIn.setBlockState(pos, state.withProperty(OPEN, Boolean.valueOf(flag)), 2);
						worldIn.markBlockRangeForRenderUpdate(pos, pos);
						playOpenSound(worldIn, blockpos1, flag);
					}
				}
			}
		}

	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(itemDoor);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return itemDoor;
	}

	@Override
	public String getNameFromMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	@Override
	public String getRegistryNameForMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	// Defaults
	@Override
	public int getSubCount() {
		return 1;
	}
	
	public ItemDoor getItemBlock() {
		return itemDoor;
	}

}
