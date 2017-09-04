package zdoctor.lazymodder.easy.blocks.tileentity;

import org.apache.logging.log4j.Level;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import zdoctor.lazymodder.easy.blocks.EasyDoor;
import zdoctor.lazymodder.easy.interfaces.IEasyTileEntity;
import zdoctor.lazymodder.easy.registry.EasyRegistry;

public class EasyDoorTileEntity extends EasyDoor implements ITileEntityProvider, IEasyTileEntity {
	protected Class<? extends TileEntity> tileEntity;

	public EasyDoorTileEntity(String name, Class<? extends TileEntity> tileEntity) {
		this(name, tileEntity, false, Material.IRON);
	}

	public EasyDoorTileEntity(String name, Class<? extends TileEntity> tileEntity, boolean powerOpens,
			Material materialIn) {
		super(name, powerOpens, materialIn);
		this.tileEntity = tileEntity;
		this.isBlockContainer = true;
		setUnlocalizedName(name);
		setRegistryName(name);
		this.powerOpens = powerOpens;
		EasyRegistry.register(this);
		itemDoor = createItem();
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
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

}
