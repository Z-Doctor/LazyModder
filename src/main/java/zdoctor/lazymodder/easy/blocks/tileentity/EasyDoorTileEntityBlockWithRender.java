package zdoctor.lazymodder.easy.blocks.tileentity;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public abstract class EasyDoorTileEntityBlockWithRender extends EasyDoorTileEntity {

	public EasyDoorTileEntityBlockWithRender(String name, Class<? extends TileEntity> tileEntity) {
		this(name, tileEntity, false, Material.IRON);
	}
	
	public EasyDoorTileEntityBlockWithRender(String name, Class<? extends TileEntity> tileEntity, Material material) {
		this(name, tileEntity, false, material);
	}

	public EasyDoorTileEntityBlockWithRender(String name, Class<? extends TileEntity> tileEntity, boolean powerOpens, Material materialIn) {
		super(name, tileEntity, powerOpens, materialIn);
	}
	
	// Defaults
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isTranslucent(IBlockState state) {
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

}
