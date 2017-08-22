package zdoctor.lazymodder.easy.blocks.tileentity;

import org.apache.logging.log4j.Level;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import zdoctor.lazymodder.easy.blocks.EasyRotatingBlock;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.interfaces.IEasyTileEntity;
import zdoctor.lazymodder.easy.items.EasyItemBlockTileEntity;

public class EasyRotatingTileEntityBlock extends EasyRotatingBlock
		implements ITileEntityProvider, IEasyTileEntity, IEasyRegister {
	protected Class<? extends TileEntity> tileEntity;

	public EasyRotatingTileEntityBlock(String name, Class<? extends TileEntity> tileEntity) {
		this(name, tileEntity, Material.IRON);
	}

	public EasyRotatingTileEntityBlock(String name, Class<? extends TileEntity> tileEntity, Material materialIn) {
		super(name, materialIn);
		this.tileEntity = tileEntity;
		this.isBlockContainer = true;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(getItemBlock(), 1, damageDropped(state));
	}

	@Override
	public Class<? extends TileEntity> getTileEntity() {
		return this.tileEntity;
	}

	@Override
	public String getTileEntityRegistryName() {
		return getRegistryName().getResourcePath();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		try {
			return this.tileEntity.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			FMLLog.log.log(Level.TRACE, "Unable to create new instance of {}", this.tileEntity.getName());
		}
		return null;
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
	public ItemBlock createItem() {
		return new EasyItemBlockTileEntity(this);
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

}
