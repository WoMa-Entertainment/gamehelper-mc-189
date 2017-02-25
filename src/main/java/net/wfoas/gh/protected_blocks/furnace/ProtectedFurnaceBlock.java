package net.wfoas.gh.protected_blocks.furnace;

import java.util.Random;
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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.core.gh.GameHelperCore;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.blocks.IGHModBlock;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.gui.GuiHelper;
import net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntity;

public class ProtectedFurnaceBlock extends BlockContainer implements IGHModBlock {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	private final boolean isBurning;
	private static boolean keepInventory;

	public ProtectedFurnaceBlock(boolean isBurning) {
		super(Material.rock);
		this.setHardness(4f);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.isBurning = isBurning;
		if (isBurning) {
			this.setUnlocalizedName(GameHelper.MODID + "." + "protected_furnace_lit");
			GameRegistry.registerBlock(this, "protected_furnace_lit");
		} else {
			this.setUnlocalizedName(GameHelper.MODID + "." + "protected_furnace");
			GameRegistry.registerBlock(this, "protected_furnace");
		}
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock((ProtectedFurnaceBlock) GameHelperCoreModule.SEC_FURNACE);
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.setDefaultFacing(worldIn, pos, state);
	}

	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			Block block = worldIn.getBlockState(pos.north()).getBlock();
			Block block1 = worldIn.getBlockState(pos.south()).getBlock();
			Block block2 = worldIn.getBlockState(pos.west()).getBlock();
			Block block3 = worldIn.getBlockState(pos.east()).getBlock();
			EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

			if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock()) {
				enumfacing = EnumFacing.SOUTH;
			} else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock()) {
				enumfacing = EnumFacing.NORTH;
			} else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock()) {
				enumfacing = EnumFacing.EAST;
			} else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock()) {
				enumfacing = EnumFacing.WEST;
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("incomplete-switch")
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (this.isBurning) {
			EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
			double d2 = (double) pos.getZ() + 0.5D;
			double d3 = 0.52D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;

			switch (enumfacing) {
			case WEST:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D,
						new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
				break;
			case EAST:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D,
						new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
				break;
			case NORTH:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D,
						new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
				break;
			case SOUTH:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D,
						new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof ProtectedFurnaceTileEntity) {
				if (((ProtectedFurnaceTileEntity) tileentity).isPlayerCapableOfOpeningBlock(playerIn)) {
					playerIn.openGui(GameHelper.instance, GuiHandler.PROTECTED_FURNACE, worldIn, pos.getX(), pos.getY(),
							pos.getZ());
					playerIn.triggerAchievement(StatList.field_181741_Y);
				} else
					playerIn.addChatComponentMessage(
							new ChatComponentTranslation("gamehelper.error.protected_furnace.noperm"));
			}
			return true;
		}
	}

	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		keepInventory = true;
		if (active) {
			worldIn.setBlockState(pos, ((ProtectedFurnaceBlock) GameHelperCoreModule.SEC_FURNACE_LIT).getDefaultState()
					.withProperty(FACING, iblockstate.getValue(FACING)), 3);
			worldIn.setBlockState(pos, ((ProtectedFurnaceBlock) GameHelperCoreModule.SEC_FURNACE_LIT).getDefaultState()
					.withProperty(FACING, iblockstate.getValue(FACING)), 3);
		} else {
			worldIn.setBlockState(pos, ((ProtectedFurnaceBlock) GameHelperCoreModule.SEC_FURNACE).getDefaultState()
					.withProperty(FACING, iblockstate.getValue(FACING)), 3);
			worldIn.setBlockState(pos, ((ProtectedFurnaceBlock) GameHelperCoreModule.SEC_FURNACE).getDefaultState()
					.withProperty(FACING, iblockstate.getValue(FACING)), 3);
		}
		keepInventory = false;
		if (tileentity != null) {
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new ProtectedFurnaceTileEntity();
	}

	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof ProtectedFurnaceTileEntity)
			if (placer instanceof EntityPlayer)
				((ProtectedFurnaceTileEntity) tileentity).setOwner((((EntityPlayer) placer).getUniqueID()));
		if (stack.hasDisplayName()) {
			if (tileentity instanceof ProtectedFurnaceTileEntity) {
				((ProtectedFurnaceTileEntity) tileentity).setCustomInventoryName(stack.getDisplayName());
			}
		}
	}

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!keepInventory) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof ProtectedFurnaceTileEntity) {
				InventoryHelper.dropInventoryItems(worldIn, pos, (ProtectedFurnaceTileEntity) tileentity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}
		}
		super.breakBlock(worldIn, pos, state);
	}

	public boolean hasComparatorInputOverride() {
		return true;
	}

	public int getComparatorInputOverride(World worldIn, BlockPos pos) {
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, BlockPos pos) {
		return Item.getItemFromBlock(((ProtectedFurnaceBlock) GameHelperCoreModule.SEC_FURNACE));
	}

	public int getRenderType() {
		return 3;
	}

	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
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
		return "protected_furnace" + (isBurning ? "_lit" : "");
	}

	@Override
	public void updateInitEvent(CreativeTabs tab) {
		ModelResourceLocation l = new ModelResourceLocation(GameHelper.MODID + ":" + getName(), "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 0, l);
		System.out.println(l);
		updateCreativeTab(tab);
	}

	@Override
	public void updateCreativeTab(CreativeTabs tab) {
		this.setCreativeTab(tab);
	}
}