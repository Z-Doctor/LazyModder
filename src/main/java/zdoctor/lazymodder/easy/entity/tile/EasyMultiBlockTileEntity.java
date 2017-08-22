package zdoctor.lazymodder.easy.entity.tile;

import java.util.Map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import zdoctor.lazymodder.easy.builders.StructureBuilder.Structure;

public class EasyMultiBlockTileEntity extends TileEntity {
	private Structure structure;
	protected Map<BlockPos, IBlockState> structureMap;
	
	public EasyMultiBlockTileEntity() {
	}
	
	public EasyMultiBlockTileEntity(Structure structure) {
		this.structure = structure;
	}
	
	public Structure getStructure() {
		return structure;
	}
	
	public void setStructure(Structure structure) {
		this.structure = structure;
	}
	
	public void spawnStructure(World world, BlockPos pos, EnumFacing facing, boolean replaceByDefault) {
		structureMap = structure.build(world, pos, facing, replaceByDefault);
		markDirty();
	}
	
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
		breakStructure(worldIn);
	}
	
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		breakStructure(worldIn);
	}
	
	protected void breakStructure(World world) {
		structureMap.forEach((blockPos, state) -> {
			setState(world, blockPos, state);
		});
	}
	
	protected void setState(World world, BlockPos blockPos, IBlockState state) {
		world.setBlockState(pos, getReplacementState(blockPos, state), 3);
		markDirty();
	}

	protected IBlockState getReplacementState(BlockPos blockPos, IBlockState oldState) {
		return Blocks.AIR.getDefaultState();
	}
	
	public IBlockState getStateAt(BlockPos pos) {
		return structureMap.get(pos);
	}
	
	public void setStateAt(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, state, 3);
		structureMap.put(pos, state);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if(compound.hasKey("EasyMultiBlockTileEntityStructureTag"))
			structure = new Structure(compound.getCompoundTag("EasyMultiBlockTileEntityStructureTag"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagCompound structureTag = new NBTTagCompound();
		if(structure != null) {
			structure.writeToNBT(structureTag);
			compound.setTag("EasyMultiBlockTileEntityStructureTag", structureTag);
		}
		return super.writeToNBT(compound);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}
	
}
