package net.wfoas.gh.blocks;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;

import java.util.List;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GHModItemUpdater;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.IMetaBlockName;
import net.wfoas.gh.ItemBlockMeta;

public class OPAnvil extends BlockFalling implements GHModItemUpdater, IMetaBlockName {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyInteger DAMAGE = PropertyInteger.create("damage", 0, 2);

	public OPAnvil() {
		super(Material.anvil);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(DAMAGE,
				Integer.valueOf(0)));
		this.setLightOpacity(0);
		this.name = "op_anvil";
		this.setUnlocalizedName(GameHelper.MODID + "." + name);
		GameRegistry.registerBlock(this, ItemBlockMeta.class, name);
	}

	String name;

	public String getName() {
		return name;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) {
		return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
	}

	public void updateCreativeTab(CreativeTabs tab) {
		this.setCreativeTab(tab);
	}

	@Override
	public void updateInitEvent(CreativeTabs tab) {
		// BlockAnvil
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 0,
				new ModelResourceLocation(GameHelper.MODID + ":" + getName(), "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 1,
				new ModelResourceLocation(GameHelper.MODID + ":" + getName(), "damage=1,inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 2,
				new ModelResourceLocation(GameHelper.MODID + ":" + getName(), "damage=2,inventory"));
		// RenderItem ri = Minecraft.getMinecraft().getRenderItem();
		// ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new
		// ModelResourceLocation(GameHelper.MODID + ":" + this.getName(),
		// "inventory"));
		// ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new
		// ModelResourceLocation(GameHelper.MODID + ":" + this.getName(),
		// "facing=south"));
		// ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new
		// ModelResourceLocation(GameHelper.MODID + ":" + this.getName(),
		// "facing=north"));
		// ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new
		// ModelResourceLocation(GameHelper.MODID + ":" + this.getName(),
		// "facing=west"));
		// ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new
		// ModelResourceLocation(GameHelper.MODID + ":" + this.getName(),
		// "facing=east"));
		updateCreativeTab(tab);
	}

	@Override
	public boolean isFullCube() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		EnumFacing enumfacing1 = placer.getHorizontalFacing().rotateY();
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer)
				.withProperty(FACING, enumfacing1).withProperty(DAMAGE, Integer.valueOf(meta >> 2));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			playerIn.displayGui(new OPAnvil.Anvil(worldIn, pos));
		}

		return true;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return ((Integer) state.getValue(DAMAGE)).intValue();
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		EnumFacing enumfacing = (EnumFacing) worldIn.getBlockState(pos).getValue(FACING);

		if (enumfacing.getAxis() == EnumFacing.Axis.X) {
			this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
		} else {
			this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		list.add(new ItemStack(itemIn, 1, 0));
		list.add(new ItemStack(itemIn, 1, 1));
		list.add(new ItemStack(itemIn, 1, 2));
	}

	@Override
	protected void onStartFalling(EntityFallingBlock fallingEntity) {
		fallingEntity.setHurtEntities(true);
	}

	@Override
	public void onEndFalling(World worldIn, BlockPos pos) {
		worldIn.playAuxSFX(1022, pos, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3)).withProperty(DAMAGE,
				Integer.valueOf((meta & 15) >> 2));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		byte b0 = 0;
		int i = b0 | ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		i |= ((Integer) state.getValue(DAMAGE)).intValue() << 2;
		return i;
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { FACING, DAMAGE });
	}

	public static class Anvil implements IInteractionObject {
		private final World world;
		private final BlockPos position;
		private static final String __OBFID = "CL_00002144";

		public Anvil(World worldIn, BlockPos pos) {
			this.world = worldIn;
			this.position = pos;
		}

		@Override
		public String getName() {
			return "op_anvil";
		}

		@Override
		public boolean hasCustomName() {
			return false;
		}

		@Override
		public IChatComponent getDisplayName() {
			// return new
			// ChatComponentTranslation(OPAnvil.this.getUnlocalizedName() +
			// ".name", new Object[0]);
			return null;
		}

		@Override
		public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
			return new ContainerRepair(playerInventory, this.world, this.position, playerIn);
		}

		@Override
		public String getGuiID() {
			return "gamehelper:op_anvil";
		}
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		if (stack.getItemDamage() == 1) {
			return "slightly_damaged";
		} else if (stack.getItemDamage() == 2) {
			return "very_damaged";
		} else if (stack.getItemDamage() == 0) {
			return "undamaged";
		}
		throw new IllegalArgumentException(stack.getItemDamage() + " is not allowed for type OPAnvil");
	}
}