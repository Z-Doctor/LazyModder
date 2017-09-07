package zdoctor.lazymodder.easy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import zdoctor.lazymodder.easy.common.EasyStateMap.Builder;
import zdoctor.lazymodder.easy.interfaces.ICustomMeshDefinition;
import zdoctor.lazymodder.easy.interfaces.ICustomStateMap;
import zdoctor.lazymodder.easy.interfaces.IEasyRegister;
import zdoctor.lazymodder.easy.items.EasyItemBlock;

public class EasyClassicFluid extends BlockFluidClassic implements IEasyRegister, ICustomStateMap, ICustomMeshDefinition {

	protected ItemBlock item;
	private ModelResourceLocation model;

	public EasyClassicFluid(String name, Fluid fluid, Material material) {
		super(fluid, material);
		setUnlocalizedName(name);
		setRegistryName(name);
		item = new EasyItemBlock(this);
		item.setCreativeTab(CreativeTabs.MISC);
		
		this.model = new ModelResourceLocation(getRegistryName().getResourceDomain() + ":" + name, "fluid");
	}

	@Override
	public String getNameFromMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

	@Override
	public String getRegistryNameForMeta(int meta) {
		return getRegistryName().getResourcePath();
	}

//	@Override
//	public IStateMapper getStateMap() {
//		return new StateMapperBase() {
//			
//			@Override
//			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
//				return model;
//			}
//		};
//	}

	@Override
	public ItemMeshDefinition getMeshDefinition() {
		return new ItemMeshDefinition() {
			
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return model;
			}
		};
	}

	@Override
	public Builder getStateMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
