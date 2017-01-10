package net.wfoas.gh.luckyblocksmodule;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.blocks.GameHelperModBlock;
import net.wfoas.gh.dropsapi.pdr.EntityType;
import net.wfoas.gh.dropsapi.pdr.LocationA;
import net.wfoas.gh.dropsapi.pdr.LuckyRandom;
import net.wfoas.gh.parseapi.DropsCollector;

public class LuckyBlock extends GameHelperModBlock {
	static LuckyRandom lr;
	DropsCollector dc;

	public LuckyBlock(String name, DropsCollector dc) {
		super(Material.rock, name);
		this.dc = dc;
		lr = new LuckyRandom();
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te) {
		if (GameHelper.getUtils().hasEnchantment(player.getHeldItem(), Enchantment.silkTouch)) {
			EntityType.dropItem(new ItemStack(LuckyBlocksModule.DEFAULT_LUCKY_BLOCK), new LocationA(pos, worldIn));
		} else {
			return;
		}
	}

	public void spawnLuckyDropsBlock(World worldIn, EntityPlayer player, BlockPos pos) {
		// try {
		// Drop d = null;
		// try {
		// switch (lr.nextLuckValue()) {
		// case LUCKY:
		// d = dc.getLucky().get(lr.nextInt(dc.getLucky().size()));
		// break;
		// case NEUTRAL:
		// d = dc.getNeutral().get(lr.nextInt(dc.getNeutral().size()));
		// break;
		// case UNLUCKY:
		// d = dc.getUnlucky().get(lr.nextInt(dc.getUnlucky().size()));
		// break;
		// default:
		// break;
		// }
		// d.exec(player, new Vec3d(pos));
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// System.out.println("Drop: " + d.getName() + " contains an Error!");
		// }
		// } catch (NullPointerException npe) {
		// npe.printStackTrace();
		// }
	}

	@Override
	public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		if (!world.isRemote)
			spawnLuckyDropsBlock(world, player, pos);
		return true;
	}

	// @Override
	// public boolean canSilkHarvest(World world, BlockPos pos, IBlockState
	// state, EntityPlayer player) {
	// return true;
	// }

	@Override
	public void updateInitEvent(CreativeTabs ct) {
		super.updateInitEvent(ct);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	// public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos,
	// IBlockState state) {
	// switch(lr.nextLuckValue()){
	// case LUCKY:
	// dc.getLucky().get(lr.nextInt(dc.getLucky().size())).exec(p, l);
	// break;
	// case NEUTRAL:
	// break;
	// case UNLUCKY:
	// break;
	// default:
	// break;
	// }
	// }

	// public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
	//
	// }

}
