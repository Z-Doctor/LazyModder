package zdoctor.lazymodder.easy.blocks;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.interfaces.INoModel;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

/**
 * Nothing is really supposed to directly extend this
 */
public class EasyBlockBush extends BlockBush implements IEasyRegister, INoModel {

	protected Item itemCrop;

	public EasyBlockBush(String name, Item crop) {
		this(name, crop, Material.PLANTS);
	}

	public EasyBlockBush(String name, Item crop, Material material) {
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setHardness(0.0F);
		this.setSoundType(SoundType.PLANT);
		itemCrop = crop;
		EasyRegistry.register(this);
	}

	public Item getCrop() {
		return itemCrop;
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
		return 1;
	}

	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		if (state.getBlock() == this) {
			IBlockState soil = worldIn.getBlockState(pos.down());
			return soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
		}
		return this.canSustainBush(worldIn.getBlockState(pos.down()));
	}

}
