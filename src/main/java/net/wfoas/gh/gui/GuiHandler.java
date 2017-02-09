package net.wfoas.gh.gui;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.backpack.big.BigContainerItem;
import net.wfoas.gh.backpack.big.BigGuiItemInventory;
import net.wfoas.gh.backpack.big.BigInventoryItem;
import net.wfoas.gh.backpack.tiny.ContainerItem;
import net.wfoas.gh.backpack.tiny.GuiItemInventory;
import net.wfoas.gh.backpack.tiny.InventoryItem;
import net.wfoas.gh.backpack.ultra.UltraContainerItem;
import net.wfoas.gh.backpack.ultra.UltraGuiItemInventory;
import net.wfoas.gh.backpack.ultra.UltraInventoryItem;
import net.wfoas.gh.enchaltar.EnchantmentAltarContainer;
import net.wfoas.gh.enchaltar.EnchantmentAltarGui;
import net.wfoas.gh.gui.login.GuiScreenLogin;
import net.wfoas.gh.gui.ownworld.GuiScreenOwnWorld;
import net.wfoas.gh.gui.vanillaguis.ContainerAnvil;
import net.wfoas.gh.gui.vanillaguis.ContainerEnchantmentTable;
import net.wfoas.gh.gui.vanillaguis.ContainerWorkbench;
import net.wfoas.gh.gui.vanillaguis.GuiContainerAnvil;
import net.wfoas.gh.gui.vanillaguis.GuiContainerEnchantmentTable;
import net.wfoas.gh.gui.vanillaguis.GuiContainerWorkbench;
import net.wfoas.gh.gui.world.CreateImportWorld;
import net.wfoas.gh.gui.world.GuiListPermDialog;
import net.wfoas.gh.gui.world.GuiSetPermScreen;
import net.wfoas.gh.instench.TileEntityInstantEnchantmentTable;
import net.wfoas.gh.minersinventory.MinersInventoryContainer;
import net.wfoas.gh.minersinventory.MinersInventoryGui;
import net.wfoas.gh.minersinventory.layer.MinersInventoryHelper;
import net.wfoas.gh.op_anvil.ContainerOPAnvil;
import net.wfoas.gh.op_anvil.GuiContainerOPAnvil;
import net.wfoas.gh.protected_blocks.GuiChangePermission;
import net.wfoas.gh.protected_blocks.chest.ContainerProtectedChest;
import net.wfoas.gh.protected_blocks.chest.GuiContainerProtectedChest;
import net.wfoas.gh.protected_blocks.chest.InventoryLargeProtectedChest;
import net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntity;
import net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntityBlock;
import net.wfoas.gh.protected_blocks.furnace.ContainerProtectedFurnace;
import net.wfoas.gh.protected_blocks.furnace.GuiContainerProtectedFurnace;
import net.wfoas.gh.protected_blocks.furnace.ProtectedFurnaceTileEntity;
import net.wfoas.gh.uncraftingtable.UncraftingTableContainer;
import net.wfoas.gh.uncraftingtable.UncraftingTableGui;
import net.wfoas.gh.wrapper.WorldNameableWrapper;

public class GuiHandler implements IGuiHandler {

	public static final int PROTECTED_GUI_CHANGE_PERMISSIONS = 24, PROTECTED_CHEST = 25, PROTECTED_FURNACE = 26,
			PROTECTED_BREWING_STAND = 27, PROTECTED_HOPPER = 28, THERMAL_INVENTORY = 29, OWNWORLD_DIALOG = 30,
			GH_LOGIN = 31, VANILLA_GH_ANVIL = 32, VANILLA_GH_ENCHANTMENT_TABLE = 33, VANILLA_GH_CRAFTING_TABLE = 34,
			ENCHANTMENT_ALTAR_GUI = 35, BACKPACK_GUI = 36, BIG_BACKPACK_GUI = 37, ULTRA_BACKPACK_GUI = 38,
			OP_ANVIL_GUI = 39, CREATE_IMPORT_WORLD_DIALOG = 40, MINERS_INVENTORY = 41, UNCRAFTING_TABLE_INVENTORY = 42,
			GH_PROGRESS_DIALOG = 43, SET_PERMISSION_DIALOG = 44, LIST_PERMISSION_DIALOG = 45;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == VANILLA_GH_CRAFTING_TABLE) {
			return new ContainerWorkbench(player.inventory, world);
		} else if (ID == ENCHANTMENT_ALTAR_GUI) {
			return new EnchantmentAltarContainer(player.inventory, new BlockPos(x, y, z));
		} else if (ID == BACKPACK_GUI) {
			return new ContainerItem(player, player.inventory, new InventoryItem(player.getHeldItem(), player));
		} else if (ID == BIG_BACKPACK_GUI) {
			return new BigContainerItem(player, player.inventory, new BigInventoryItem(player.getHeldItem(), player));
		} else if (ID == ULTRA_BACKPACK_GUI) {
			return new UltraContainerItem(player, player.inventory,
					new UltraInventoryItem(player.getHeldItem(), player));
		} else if (ID == CREATE_IMPORT_WORLD_DIALOG) {
			return null;
		} else if (ID == MINERS_INVENTORY) {
			return new MinersInventoryContainer(player.inventory, MinersInventoryHelper.getMinersInventory(player));
		} else if (ID == UNCRAFTING_TABLE_INVENTORY) {
			return new UncraftingTableContainer();
		} else if (ID == GH_PROGRESS_DIALOG) {
			return null;
		} else if (ID == VANILLA_GH_ENCHANTMENT_TABLE) {
			if (y == -1) {
				return new ContainerEnchantmentTable(player.inventory, world, z, 0, 0, 0);
			}
			IBlockState bs = world.getBlockState(new BlockPos(x, y, z));
			if (Block.isEqualTo(bs.getBlock(), GameHelperCoreModule.INST_ENCH)) {
				return new ContainerEnchantmentTable(player.inventory, world, 15, x, y, z);
			}
		} else if (ID == VANILLA_GH_ANVIL) {
			return new ContainerAnvil(player.inventory, world, player);
		} else if (ID == SET_PERMISSION_DIALOG) {
			return null;
		} else if (ID == LIST_PERMISSION_DIALOG) {
			return null;
		} else if (ID == GH_LOGIN) {
			return null;
		} else if (ID == OWNWORLD_DIALOG) {
			return null;
		} else if (ID == THERMAL_INVENTORY) {
			// TODO implement
		} else if (ID == PROTECTED_CHEST) {
			ProtectedChestTileEntityBlock chest = (ProtectedChestTileEntityBlock) GameHelperCoreModule.SEC_CHEST;
			return new ContainerProtectedChest(player.inventory,
					chest.getLockableContainer(world, new BlockPos(x, y, z)), player);
		} else if (ID == PROTECTED_FURNACE) {
			return new ContainerProtectedFurnace(player.inventory,
					(ProtectedFurnaceTileEntity) world.getTileEntity(new BlockPos(x, y, z)));
		} else if (ID == PROTECTED_BREWING_STAND) {

		} else if (ID == PROTECTED_HOPPER) {

		} else if (ID == PROTECTED_GUI_CHANGE_PERMISSIONS) {
			return null;
		} else if (ID == OP_ANVIL_GUI) {
			return new ContainerOPAnvil(player.inventory, world, player, new BlockPos(x, y, z));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == VANILLA_GH_CRAFTING_TABLE) {
			return new GuiContainerWorkbench(player.inventory, world);
		} else if (ID == ENCHANTMENT_ALTAR_GUI) {
			return new EnchantmentAltarGui(player.inventory);
		} else if (ID == BACKPACK_GUI) {
			return new GuiItemInventory(
					new ContainerItem(player, player.inventory, new InventoryItem(player.getHeldItem(), player)));
		} else if (ID == BIG_BACKPACK_GUI) {
			return new BigGuiItemInventory(
					new BigContainerItem(player, player.inventory, new BigInventoryItem(player.getHeldItem(), player)));
		} else if (ID == ULTRA_BACKPACK_GUI) {
			return new UltraGuiItemInventory(new UltraContainerItem(player, player.inventory,
					new UltraInventoryItem(player.getHeldItem(), player)));
		} else if (ID == CREATE_IMPORT_WORLD_DIALOG) {
			return new CreateImportWorld();
		} else if (ID == MINERS_INVENTORY) {
			return new MinersInventoryGui(
					new MinersInventoryContainer(player.inventory, MinersInventoryHelper.getMinersInventory(player)));
		} else if (ID == UNCRAFTING_TABLE_INVENTORY) {
			return new UncraftingTableGui(new UncraftingTableContainer());
		} else if (ID == GH_PROGRESS_DIALOG) {
			return new GHProgressDialog();
		} else if (ID == VANILLA_GH_ENCHANTMENT_TABLE) {
			if (y == -1) {
				return new GuiContainerEnchantmentTable(player.inventory, world,
						new WorldNameableWrapper("container.enchant"), z, 0, 0, 0);
			}
			IBlockState bs = world.getBlockState(new BlockPos(x, y, z));
			if (Block.isEqualTo(bs.getBlock(), GameHelperCoreModule.INST_ENCH)) {
				String name = ((TileEntityInstantEnchantmentTable) world.getTileEntity(new BlockPos(x, y, z)))
						.getName();
				return new GuiContainerEnchantmentTable(player.inventory, world, new WorldNameableWrapper(name), 15, x,
						y, z);
			} else {
				return new GuiContainerEnchantmentTable(player.inventory, world,
						new WorldNameableWrapper("container.enchant"), 15, x, y, z);
			}
		} else if (ID == VANILLA_GH_ANVIL) {
			return new GuiContainerAnvil(player.inventory, world);
		} else if (ID == SET_PERMISSION_DIALOG) {
			return new GuiSetPermScreen();
		} else if (ID == LIST_PERMISSION_DIALOG) {
			return new GuiListPermDialog();
		} else if (ID == GH_LOGIN) {
			return new GuiScreenLogin();
		} else if (ID == OWNWORLD_DIALOG) {
			return new GuiScreenOwnWorld();
		} else if (ID == THERMAL_INVENTORY) {
			// TODO implement
		} else if (ID == PROTECTED_CHEST) {
			ProtectedChestTileEntityBlock chest = (ProtectedChestTileEntityBlock) GameHelperCoreModule.SEC_CHEST;
			if (chest.getLockableContainer(world, new BlockPos(x, y, z)) == null)
				return null;
			if (chest.getLockableContainer(world, new BlockPos(x, y, z)) instanceof InventoryLargeProtectedChest) {
				InventoryLargeProtectedChest lpc = (InventoryLargeProtectedChest) chest.getLockableContainer(world,
						new BlockPos(x, y, z));
				if (lpc.getILockContainerProtected()[0] == null && lpc.getILockContainerProtected()[1] == null)
					return null;
			}
			// else if(chest.getLockableContainer(world, new BlockPos(x, y, z))
			// instanceof ProtectedChestTileEntity){
			//
			// }
			return new GuiContainerProtectedChest(player.inventory,
					chest.getLockableContainer(world, new BlockPos(x, y, z)));
		} else if (ID == PROTECTED_FURNACE) {
			return new GuiContainerProtectedFurnace(player.inventory,
					(ProtectedFurnaceTileEntity) world.getTileEntity(new BlockPos(x, y, z)));
		} else if (ID == PROTECTED_BREWING_STAND) {

		} else if (ID == PROTECTED_HOPPER) {

		} else if (ID == PROTECTED_GUI_CHANGE_PERMISSIONS) {
			return new GuiChangePermission(x, y, z);
		} else if (ID == OP_ANVIL_GUI) {
			return new GuiContainerOPAnvil(player.inventory, world);
		}
		return null;
	}
}