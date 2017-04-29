package zmods.lazyapi.easy;

import org.apache.logging.log4j.Level;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import zmods.lazyapi.easy.EasyBlock.EasyItemBlock;
import zmods.lazyapi.easy.EasyEntity.InventoryEntity.InventoryTileEntity;

public class EasyEntity extends BlockContainer {
	{
		EasyFunctions.registerTileEntity(EasyTileEntity.class, "EasyEntity");
		EasyFunctions.registerTileEntity(EasyTileEntity.UpdateTileEntity.class, "EasyEntity");
		EasyFunctions.registerTileEntity(InventoryTileEntity.class, "EasyEntity");
	}

	protected Class<? extends TileEntity> tileEntity;
	protected EasyItemBlock itemBlock;

	public EasyEntity(String name, Class<? extends TileEntity> tileEntity) {
		this(name, tileEntity, Material.IRON);
	}

	public EasyEntity(String name, Class<? extends TileEntity> tileEntity, Material materialIn) {
		super(materialIn);
		this.tileEntity = tileEntity;
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		EasyFunctions.register(this);
		this.itemBlock = new EasyBlock.EasyItemBlock(this);
	}
	
	public Class<? extends TileEntity> getTileEntity() {
		return this.tileEntity;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		try {
			return this.tileEntity.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			FMLLog.getLogger().log(Level.TRACE, "Unable to create new instance of {}", this.tileEntity.getName());
		}
		return null;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	public static class EasyTileEntity extends TileEntity {

		public static class UpdateTileEntity extends EasyTileEntity implements ITickable {
			@Override
			public void update() {

			}
		}

	}

	public static class InventoryEntity extends EasyEntity {
		private String inventoryName;
		private int inventorySize;
		private int maxStackSize = 64;

		public InventoryEntity(String name) {
			this(name, 32, Material.IRON);
		}

		public InventoryEntity(String name, int inventorySize) {
			this(name, inventorySize, Material.IRON);
		}

		public InventoryEntity(String name, int inventorySize, Material materialIn) {
			super(name, InventoryTileEntity.class, materialIn);
			this.inventoryName = name;
			this.inventorySize = inventorySize;
		}

		public InventoryEntity(String name, Class<? extends TileEntity> tileEntity) {
			this(name, tileEntity, Material.IRON);
		}

		public InventoryEntity(String name, Class<? extends TileEntity> tileEntity, Material materialIn) {
			super(name, tileEntity, materialIn);
		}

		public InventoryEntity setMaxStackSize(int stackSize) {
			this.maxStackSize = stackSize;
			return this;
		}

		@Override
		public TileEntity createTileEntity(World world, IBlockState state) {
			TileEntity te = super.createTileEntity(world, state);
			((InventoryTileEntity) te).registerInventory(this.inventoryName, this.inventorySize, this.maxStackSize);
			return te;
		}

		@Override
		public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
			((InventoryTileEntity) worldIn.getTileEntity(pos)).dropInventory(worldIn, pos);
			super.breakBlock(worldIn, pos, state);
		}

		@Override
		public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
				ItemStack stack) {
			if (stack.hasDisplayName()) {
				((InventoryTileEntity) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
			}
		}

		public static class InventoryTileEntity extends TileEntity implements IInventory {
			private String inventoryName;
			private int inventorySize;
			private NonNullList<ItemStack> inventory;
			private String customName;
			private int maxStackSize;

			public InventoryTileEntity() {
			}

			public InventoryTileEntity setInventorySize(int inventorySize) {
				this.inventorySize = inventorySize;
				return this;
			}

			public InventoryTileEntity(String inventoryName, int inventorySize) {
				this.inventoryName = "container." + inventoryName;
				this.inventorySize = inventorySize;
				this.inventory = NonNullList.withSize(this.inventorySize, ItemStack.EMPTY);
			}

			public void dropInventory(World worldIn, BlockPos pos) {
				InventoryHelper.dropInventoryItems(worldIn, pos, this);
			}

			public void setCustomName(String customName) {
				this.customName = customName;
			}

			public InventoryTileEntity setMaxStackSize(int size) {
				this.maxStackSize = size;
				return this;
			}

			public InventoryTileEntity registerInventory(String inventoryName, int inventorySize,
					int maxStackSize) {
				this.inventoryName = "container." + inventoryName;
				this.inventorySize = inventorySize;
				this.maxStackSize = maxStackSize;
				this.inventory = NonNullList.withSize(this.inventorySize, ItemStack.EMPTY);
				return this;
			}

			@Override
			public String getName() {
				return this.hasCustomName() ? this.customName : this.inventoryName;
			}

			@Override
			public boolean hasCustomName() {
				return this.customName != null && this.customName != "";
			}

			@Override
			public ITextComponent getDisplayName() {
				return this.hasCustomName() ? new TextComponentString(this.getName())
						: new TextComponentTranslation(this.getName());
			}

			@Override
			public int getSizeInventory() {
				return this.inventorySize;
			}

			@Override
			public boolean isEmpty() {
				boolean empty = true;
				for (ItemStack itemStack : this.inventory) {
					if (!itemStack.isEmpty()) {
						empty = false;
						break;
					}
				}
				return empty;
			}

			@Override
			public ItemStack getStackInSlot(int index) {
				if (index < 0 || index >= this.getSizeInventory())
					return ItemStack.EMPTY;
				return this.inventory.get(index);
			}

			@Override
			public ItemStack decrStackSize(int index, int count) {
				if (!this.getStackInSlot(index).isEmpty()) {
					ItemStack itemstack;

					if (this.getStackInSlot(index).getCount() <= count) {
						itemstack = this.getStackInSlot(index);
						this.setInventorySlotContents(index, itemstack.EMPTY);
						this.markDirty();
						return itemstack;
					} else {
						itemstack = this.getStackInSlot(index).splitStack(count);

						if (this.getStackInSlot(index).getCount() <= 0) {
							this.setInventorySlotContents(index, itemstack.EMPTY);
						} else {
							this.setInventorySlotContents(index, this.getStackInSlot(index));
						}

						this.markDirty();
						return itemstack;
					}
				} else {
					return null;
				}
			}

			@Override
			public ItemStack removeStackFromSlot(int index) {
				if (index < 0 || index >= this.getSizeInventory())
					return ItemStack.EMPTY;
				else if (this.inventory.get(index).isEmpty())
					return ItemStack.EMPTY;
				ItemStack itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, itemstack.EMPTY);
				this.markDirty();
				return itemstack;
			}

			@Override
			public void setInventorySlotContents(int index, ItemStack stack) {
				if (index < 0 || index >= this.getSizeInventory())
					return;

				if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
					stack.setCount(this.getInventoryStackLimit());

				if (!stack.isEmpty() && stack.getCount() == 0)
					stack = ItemStack.EMPTY;

				this.inventory.set(index, stack);
				this.markDirty();
			}

			@Override
			public int getInventoryStackLimit() {
				return this.maxStackSize;
			}

			@Override
			public boolean isUsableByPlayer(EntityPlayer player) {
				return this.getWorld().getTileEntity(this.getPos()) == this
						&& player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
			}

			@Override
			public void openInventory(EntityPlayer player) {

			}

			@Override
			public void closeInventory(EntityPlayer player) {

			}

			@Override
			public boolean isItemValidForSlot(int index, ItemStack stack) {
				if (index < 0 || index > this.inventorySize)
					return false;
				ItemStack itemStack = this.getStackInSlot(index);
				if (itemStack.getCount() + stack.getCount() > this.getInventoryStackLimit())
					return false;
				return true;
			}

			@Override
			public int getField(int id) {
				return 0;
			}

			@Override
			public void setField(int id, int value) {

			}

			@Override
			public int getFieldCount() {
				return 0;
			}

			@Override
			public void clear() {
				for (int i = 0; i < this.getSizeInventory(); i++)
					this.setInventorySlotContents(i, ItemStack.EMPTY);
			}

			@Override
			public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
				super.writeToNBT(nbt);

				NBTTagList list = new NBTTagList();
				for (int i = 0; i < this.getSizeInventory(); ++i) {
					if (!this.getStackInSlot(i).isEmpty()) {
						NBTTagCompound stackTag = new NBTTagCompound();
						stackTag.setByte("Slot", (byte) i);
						this.getStackInSlot(i).writeToNBT(stackTag);
						list.appendTag(stackTag);
					}
				}
				nbt.setTag("Items", list);

				if (this.hasCustomName()) {
					nbt.setString("CustomName", this.getName());
				}
				return nbt;
			}

			@Override
			public void readFromNBT(NBTTagCompound nbt) {
				super.readFromNBT(nbt);
				ItemStackHelper.loadAllItems(nbt, this.inventory);
				if (nbt.hasKey("CustomName", 8)) {
					this.setCustomName(nbt.getString("CustomName"));
				}
			}
		}
	}
	
	public static class TESR extends EasyEntity {
		public TESR(String name, Class<? extends TileEntity> tileEntity) {
			super(name, tileEntity);
		}

		public TESR(String name, Class<? extends TileEntity> tileEntity, Material materialIn) {
			super(name, tileEntity, materialIn);
		}

		@Override
		public EnumBlockRenderType getRenderType(IBlockState state) {
			return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
		}
		
		@Override
		public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
			return 0;
		}
		
		@Override
		public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
			// TODO Auto-generated method stub
			return 15;
		}
	}

}
