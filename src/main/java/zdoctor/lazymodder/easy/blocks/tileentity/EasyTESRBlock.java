package zdoctor.lazymodder.easy.blocks.tileentity;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import zdoctor.lazymodder.common.client.EasyTileEntitySpecialRenderer;
import zdoctor.lazymodder.easy.interfaces.IEasyTESR;

public class EasyTESRBlock extends EasyTileEntityBlock implements IEasyTESR {
	protected Class<? extends EasyTileEntitySpecialRenderer> renderer;

	public EasyTESRBlock(String name, Class<? extends TileEntity> tileEntity,
			Class<? extends EasyTileEntitySpecialRenderer> renderer) {
		this(name, tileEntity, renderer, Material.IRON);
	}

	public EasyTESRBlock(String name, Class<? extends TileEntity> tileEntity,
			Class<? extends EasyTileEntitySpecialRenderer> renderer, Material materialIn) {
		super(name, tileEntity, materialIn);
		this.renderer = renderer;
	}

	@Override
	public Class<? extends EasyTileEntitySpecialRenderer> getTileEntityRenderer() {
		return renderer;
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
