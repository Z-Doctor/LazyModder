package zdoctor.lazymodder.easy.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zdoctor.lazymodder.easy.blocks.EasyBlockCrops;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

public class EasyItemSeeds extends ItemSeeds implements IEasyRegister {
	private int subCount;
	protected Block crop;
	protected Block farmland;

	public EasyItemSeeds(Block crop, Block farmLand) {
		this(crop, farmLand, 1);
	}

	public EasyItemSeeds(Block crop, Block farmland, int subCount) {
		super(crop, farmland);
		this.crop = crop;
		this.farmland = farmland;
		this.setUnlocalizedName(crop.getRegistryName().toString() + "seed");
		this.setRegistryName(crop.getRegistryName() + "seed");
		this.subCount = subCount;
		this.setHasSubtypes(subCount > 1);
		if (hasSubtypes)
			this.addPropertyOverride(new ResourceLocation("meta"), ItemProperties.META_GETTER);
		this.setCreativeTab(CreativeTabs.MISC);
		EasyRegistry.register(this);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);
		net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
		if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack)
				&& state.getBlock() == farmland && worldIn.isAirBlock(pos.up())) {
			
			state = this.crop.getDefaultState();
			
			if(crop instanceof EasyBlockCrops) {
				EasyBlockCrops ec = (EasyBlockCrops) crop;
				if(!ec.canGrow(worldIn, pos.up(), state, worldIn.isRemote))
					return EnumActionResult.FAIL;
			}
			worldIn.setBlockState(pos.up(), state);

			if (player instanceof EntityPlayerMP) {
				CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos.up(), itemstack);
			}

			itemstack.shrink(1);
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getNameFromMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	@Override
	public int getSubCount() {
		return subCount;
	}

	@Override
	public String getRegistryNameForMeta(int meta) {
		return getNameFromMeta(meta);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (isInCreativeTab(tab))
			for (int i = 0; i < this.getSubCount(); i++)
				subItems.add(new ItemStack(this, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		if (this.crop instanceof IEasyRegister)
			return "item.seed." + ((IEasyRegister) this.crop).getNameFromMeta(itemStack.getMetadata()).toLowerCase();
		return "item.seed." + this.getNameFromMeta(itemStack.getMetadata()).toLowerCase();
	}
}
