package net.wfoas.gh.shitmodule.tileentity;

import net.minecraft.block.BlockChest;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.blocks.GameHelperModBlock;

public class ShitChestTileEntityBlock extends GameHelperModBlock implements ITileEntityProvider {

    public ShitChestTileEntityBlock() {
        super(Material.cloth, "shit_chest");
//        this.setUnlocalizedName(unlocalizedName);BlockChest
        this.setHardness(2.0f);
        this.setResistance(6.0f);
        this.isBlockContainer = true;
    }
    
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new ShitChestTileEntity();
    }
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    	EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3).getOpposite();
        state = state.withProperty(BlockChest.FACING, enumfacing);
        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = this == worldIn.getBlockState(blockpos1).getBlock();
        boolean flag1 = this == worldIn.getBlockState(blockpos2).getBlock();
        boolean flag2 = this == worldIn.getBlockState(blockpos3).getBlock();
        boolean flag3 = this == worldIn.getBlockState(blockpos4).getBlock();

        if (!flag && !flag1 && !flag2 && !flag3)
        {
            worldIn.setBlockState(pos, state, 3);
        }
        else if (enumfacing.getAxis() == EnumFacing.Axis.X && (flag || flag1))
        {
            if (flag)
            {
                worldIn.setBlockState(blockpos1, state, 3);
            }
            else
            {
                worldIn.setBlockState(blockpos2, state, 3);
            }

            worldIn.setBlockState(pos, state, 3);
        }
        else if (enumfacing.getAxis() == EnumFacing.Axis.Z && (flag2 || flag3))
        {
            if (flag2)
            {
                worldIn.setBlockState(blockpos3, state, 3);
            }
            else
            {
                worldIn.setBlockState(blockpos4, state, 3);
            }

            worldIn.setBlockState(pos, state, 3);
        }
        if (stack.hasDisplayName()) {
            ((ShitChestTileEntity) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
        }
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
//            player.openGui(GameHelper.instance, ContainerChest., world, pos.getX(), pos.getY(), pos.getZ());
        	ShitChestTileEntity te = (ShitChestTileEntity)world.getTileEntity(pos);
//        	ContainerChest cc = new ContainerChest(player.inventory, te, player);
//        	player.openContainer = cc;
//        	BlockChest
        	player.displayGUIChest(te);
        }
        return true;
    }
    
    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
    	ShitChestTileEntity te = (ShitChestTileEntity) world.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(world, pos, te);
        super.breakBlock(world, pos, state);
        world.removeTileEntity(pos);
    }

    @Override
    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
    }
    
    @Override
    public void updateInitEvent(CreativeTabs tab){
    	RenderItem ri = Minecraft.getMinecraft().getRenderItem();
    	ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(GameHelper.MODID + ":" + this.getName(), "inventory"));
//		ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(GameHelper.MODID + ":" + this.getName(), "facing=south"));
//		ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(GameHelper.MODID + ":" + this.getName(), "facing=north"));
//		ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(GameHelper.MODID + ":" + this.getName(), "facing=west"));
//		ri.getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(GameHelper.MODID + ":" + this.getName(), "facing=east"));
    	super.updateInitEvent(tab);
    	
    }
    
    @Override
	public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(BlockChest.FACING)).getIndex();
    }
    
    @Override
	protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {BlockChest.FACING});
    }

//	@Override
//	public TileEntity createNewTileEntity(World worldIn, int meta) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}