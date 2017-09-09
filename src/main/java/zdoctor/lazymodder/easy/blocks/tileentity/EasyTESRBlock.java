package zdoctor.lazymodder.easy.blocks.tileentity;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import zdoctor.lazymodder.easy.interfaces.IRenderTESR;

public abstract class EasyTESRBlock extends EasyTileEntityBlock implements IRenderTESR {
	public EasyTESRBlock(String name, Class<? extends TileEntity> tileEntity) {
		this(name, tileEntity, Material.IRON);
	}

	/**
	 * Used to create blocks that support {@link TileEntitySpecialRenderer}
	 * behavior. The {@link TileEntity} should implement {@link IRenderTESR}
	 * 
	 * @param name
	 * @param tileEntity
	 * @param materialIn
	 */
	public EasyTESRBlock(String name, Class<? extends TileEntity> tileEntity, Material materialIn) {
		super(name, tileEntity, materialIn);
	}

	// Defaults
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
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

}
