package net.wfoas.gh.protected_blocks.chest;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.blocks.IGHModBlock;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.notifysettings.NotifyTable;

public class ProtectedChestTileEntityBlock extends BlockContainer implements IGHModBlock {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public ProtectedChestTileEntityBlock() {
		super(Material.wood);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		this.setUnlocalizedName(GameHelper.MODID + "." + "protected_chest");
		this.setHardness(3.0f);
		GameRegistry.registerBlock(this, "protected_chest");
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean isFullCube() {
		return false;
	}

	public int getRenderType() {
		return 2;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos.north()).getBlock() == this) {
			this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
		} else if (worldIn.getBlockState(pos.south()).getBlock() == this) {
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
		} else if (worldIn.getBlockState(pos.west()).getBlock() == this) {
			this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		} else if (worldIn.getBlockState(pos.east()).getBlock() == this) {
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
		} else {
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		}
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.checkForSurroundingChests(worldIn, pos, state);

		for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
			BlockPos blockpos = pos.offset(enumfacing);
			IBlockState iblockstate = worldIn.getBlockState(blockpos);

			if (iblockstate.getBlock() == this) {
				this.checkForSurroundingChests(worldIn, blockpos, iblockstate);
			}
		}
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to
	 * allow for adjustments to the IBlockstate
	 */
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}

	/**
	 * Called by ItemBlocks after a block is set in the world, to allow
	 * post-place logic
	 */
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		EnumFacing enumfacing = EnumFacing
				.getHorizontal(MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3)
				.getOpposite();
		state = state.withProperty(FACING, enumfacing);
		BlockPos blockpos = pos.north();
		BlockPos blockpos1 = pos.south();
		BlockPos blockpos2 = pos.west();
		BlockPos blockpos3 = pos.east();
		boolean flag = this == worldIn.getBlockState(blockpos).getBlock();
		boolean flag1 = this == worldIn.getBlockState(blockpos1).getBlock();
		boolean flag2 = this == worldIn.getBlockState(blockpos2).getBlock();
		boolean flag3 = this == worldIn.getBlockState(blockpos3).getBlock();

		if (!flag && !flag1 && !flag2 && !flag3) {
			worldIn.setBlockState(pos, state, 3);
		} else if (enumfacing.getAxis() != EnumFacing.Axis.X || !flag && !flag1) {
			if (enumfacing.getAxis() == EnumFacing.Axis.Z && (flag2 || flag3)) {
				if (flag2) {
					worldIn.setBlockState(blockpos2, state, 3);
				} else {
					worldIn.setBlockState(blockpos3, state, 3);
				}

				worldIn.setBlockState(pos, state, 3);
			}
		} else {
			if (flag) {
				worldIn.setBlockState(blockpos, state, 3);
			} else {
				worldIn.setBlockState(blockpos1, state, 3);
			}
			worldIn.setBlockState(pos, state, 3);
		}
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof ProtectedChestTileEntity) {
			if (placer instanceof EntityPlayer)
				((ProtectedChestTileEntity) tileentity).owner((EntityPlayer) placer);
			if (stack.hasDisplayName()) {
				tileentity = worldIn.getTileEntity(pos);
				((ProtectedChestTileEntity) tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}

	public IBlockState checkForSurroundingChests(World worldIn, BlockPos pos, IBlockState state) {
		if (worldIn.isRemote) {
			return state;
		} else {
			IBlockState iblockstate = worldIn.getBlockState(pos.north());
			IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
			IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
			IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
			Block block = iblockstate.getBlock();
			Block block1 = iblockstate1.getBlock();
			Block block2 = iblockstate2.getBlock();
			Block block3 = iblockstate3.getBlock();
			if (block != this && block1 != this) {
				boolean flag = block.isFullBlock();
				boolean flag1 = block1.isFullBlock();
				if (block2 == this || block3 == this) {
					BlockPos blockpos1 = block2 == this ? pos.west() : pos.east();
					IBlockState iblockstate6 = worldIn.getBlockState(blockpos1.north());
					IBlockState iblockstate7 = worldIn.getBlockState(blockpos1.south());
					enumfacing = EnumFacing.SOUTH;
					EnumFacing enumfacing2;
					if (block2 == this) {
						enumfacing2 = (EnumFacing) iblockstate2.getValue(FACING);
					} else {
						enumfacing2 = (EnumFacing) iblockstate3.getValue(FACING);
					}
					if (enumfacing2 == EnumFacing.NORTH) {
						enumfacing = EnumFacing.NORTH;
					}
					Block block6 = iblockstate6.getBlock();
					Block block7 = iblockstate7.getBlock();
					if ((flag || block6.isFullBlock()) && !flag1 && !block7.isFullBlock()) {
						enumfacing = EnumFacing.SOUTH;
					}
					if ((flag1 || block7.isFullBlock()) && !flag && !block6.isFullBlock()) {
						enumfacing = EnumFacing.NORTH;
					}
				}
			} else {
				BlockPos blockpos = block == this ? pos.north() : pos.south();
				IBlockState iblockstate4 = worldIn.getBlockState(blockpos.west());
				IBlockState iblockstate5 = worldIn.getBlockState(blockpos.east());
				enumfacing = EnumFacing.EAST;
				EnumFacing enumfacing1;
				if (block == this) {
					enumfacing1 = (EnumFacing) iblockstate.getValue(FACING);
				} else {
					enumfacing1 = (EnumFacing) iblockstate1.getValue(FACING);
				}
				if (enumfacing1 == EnumFacing.WEST) {
					enumfacing = EnumFacing.WEST;
				}
				Block block4 = iblockstate4.getBlock();
				Block block5 = iblockstate5.getBlock();
				if ((block2.isFullBlock() || block4.isFullBlock()) && !block3.isFullBlock() && !block5.isFullBlock()) {
					enumfacing = EnumFacing.EAST;
				}
				if ((block3.isFullBlock() || block5.isFullBlock()) && !block2.isFullBlock() && !block4.isFullBlock()) {
					enumfacing = EnumFacing.WEST;
				}
			}
			state = state.withProperty(FACING, enumfacing);
			worldIn.setBlockState(pos, state, 3);
			return state;
		}
	}

	public IBlockState correctFacing(World worldIn, BlockPos pos, IBlockState state) {
		EnumFacing enumfacing = null;

		for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL) {
			IBlockState iblockstate = worldIn.getBlockState(pos.offset(enumfacing1));

			if (iblockstate.getBlock() == this) {
				return state;
			}

			if (iblockstate.getBlock().isFullBlock()) {
				if (enumfacing != null) {
					enumfacing = null;
					break;
				}

				enumfacing = enumfacing1;
			}
		}

		if (enumfacing != null) {
			return state.withProperty(FACING, enumfacing.getOpposite());
		} else {
			EnumFacing enumfacing2 = (EnumFacing) state.getValue(FACING);

			if (worldIn.getBlockState(pos.offset(enumfacing2)).getBlock().isFullBlock()) {
				enumfacing2 = enumfacing2.getOpposite();
			}

			if (worldIn.getBlockState(pos.offset(enumfacing2)).getBlock().isFullBlock()) {
				enumfacing2 = enumfacing2.rotateY();
			}

			if (worldIn.getBlockState(pos.offset(enumfacing2)).getBlock().isFullBlock()) {
				enumfacing2 = enumfacing2.getOpposite();
			}

			return state.withProperty(FACING, enumfacing2);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) { // TODO check
																	// player of
																	// nearby
																	// chests to
																	// deny
																	// placing
																	// chest
		int i = 0;
		BlockPos blockpos = pos.west();
		BlockPos blockpos1 = pos.east();
		BlockPos blockpos2 = pos.north();
		BlockPos blockpos3 = pos.south();

		if (worldIn.getBlockState(blockpos).getBlock() == this) {
			if (this.isDoubleChest(worldIn, blockpos)) {
				return false;
			}
			// ((ProtectedChestTileEntity)worldIn.getTileEntity(blockpos)).owner
			// TODO
			++i;
		}

		if (worldIn.getBlockState(blockpos1).getBlock() == this) {
			if (this.isDoubleChest(worldIn, blockpos1)) {
				return false;
			}

			++i;
		}

		if (worldIn.getBlockState(blockpos2).getBlock() == this) {
			if (this.isDoubleChest(worldIn, blockpos2)) {
				return false;
			}

			++i;
		}

		if (worldIn.getBlockState(blockpos3).getBlock() == this) {
			if (this.isDoubleChest(worldIn, blockpos3)) {
				return false;
			}

			++i;
		}

		return i <= 1;
	}

	private boolean isDoubleChest(World worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos).getBlock() != this) {
			return false;
		} else {
			for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
				if (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() == this) {
					return true;
				}
			}

			return false;
		}
	}

	/**
	 * Called when a neighboring block changes.
	 */
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof ProtectedChestTileEntity) {
			tileentity.updateContainingBlockInfo();
		}
	}

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			ILockableContainer ilockablecontainer = this.getLockableContainer(worldIn, pos);

			if (ilockablecontainer != null) {
				TileEntity tileentity = worldIn.getTileEntity(pos);
				if (tileentity instanceof ProtectedChestTileEntity) {
					// if
					// (tileentity.getTileData().hasKey("whitelisted_players"))
					// {
					if (((ProtectedChestTileEntity) tileentity).isPlayerCapableOfOpeningBlock(playerIn))
						// playerIn.displayGUIChest(ilockablecontainer);
						playerIn.openGui(GameHelper.instance, GuiHandler.PROTECTED_CHEST, worldIn, pos.getX(),
								pos.getY(), pos.getZ());
					else
						NotifyTable.notifyPlayer((EntityPlayerMP)playerIn,
								new ChatComponentTranslation("gamehelper.error.protected_chest.noperm"));
					// }
				}
			}
			return true;
		}
	}

	public ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (!(tileentity instanceof ProtectedChestTileEntity)) {
			return null;
		} else {
			ILockableContainer ilockablecontainer = (ProtectedChestTileEntity) tileentity;
			if (this.isBlocked(worldIn, pos)) {
				return null;
			} else {
				for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
					BlockPos blockpos = pos.offset(enumfacing);
					Block block = worldIn.getBlockState(blockpos).getBlock();
					if (block == this) {
						if (this.isBlocked(worldIn, blockpos)) {
							return null;
						}
						TileEntity tileentity1 = worldIn.getTileEntity(blockpos);
						if (tileentity1 instanceof ProtectedChestTileEntity) {
							if (enumfacing != EnumFacing.WEST && enumfacing != EnumFacing.NORTH) {
								ilockablecontainer = new InventoryLargeProtectedChest(
										"tile.gamehelper.protected_chest.large.name", ilockablecontainer,
										(ProtectedChestTileEntity) tileentity1);
							} else {
								ilockablecontainer = new InventoryLargeProtectedChest(
										"tile.gamehelper.protected_chest.large.name",
										(ProtectedChestTileEntity) tileentity1, ilockablecontainer);
							}
						}
					}
				}
				return ilockablecontainer;
			}
		}
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing
	 * the block.
	 */
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new ProtectedChestTileEntity();
	}

	/**
	 * Can this block provide power. Only wire currently seems to have this
	 * change based on its state.
	 */
	public boolean canProvidePower() {
		return false;
	}

	public int getWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
		if (!this.canProvidePower()) {
			return 0;
		} else {
			int i = 0;
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof ProtectedChestTileEntity) {
				i = ((ProtectedChestTileEntity) tileentity).numPlayersUsing;
			}

			return MathHelper.clamp_int(i, 0, 15);
		}
	}

	public int getStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
		return side == EnumFacing.UP ? this.getWeakPower(worldIn, pos, state, side) : 0;
	}

	private boolean isBlocked(World worldIn, BlockPos pos) {
		return this.isBelowSolidBlock(worldIn, pos) || this.isOcelotSittingOnChest(worldIn, pos);
	}

	private boolean isBelowSolidBlock(World worldIn, BlockPos pos) {
		return worldIn.isSideSolid(pos.up(), EnumFacing.DOWN, false);
	}

	private boolean isOcelotSittingOnChest(World worldIn, BlockPos pos) {
		for (Entity entity : worldIn.getEntitiesWithinAABB(EntityOcelot.class,
				new AxisAlignedBB((double) pos.getX(), (double) (pos.getY() + 1), (double) pos.getZ(),
						(double) (pos.getX() + 1), (double) (pos.getY() + 2), (double) (pos.getZ() + 1)))) {
			EntityOcelot entityocelot = (EntityOcelot) entity;

			if (entityocelot.isSitting()) {
				return true;
			}
		}

		return false;
	}

	public boolean hasComparatorInputOverride() {
		return true;
	}

	public int getComparatorInputOverride(World worldIn, BlockPos pos) {
		return Container.calcRedstoneFromInventory(this.getLockableContainer(worldIn, pos));
	}

	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { FACING });
	}

	@Override
	public String getName() {
		return "protected_chest";
	}

	@Override
	public void updateInitEvent(CreativeTabs tab) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 0,
				new ModelResourceLocation(GameHelper.MODID + ":" + getName(), "inventory"));
		updateCreativeTab(tab);
	}

	@Override
	public void updateCreativeTab(CreativeTabs tab) {
		this.setCreativeTab(tab);
	}
}