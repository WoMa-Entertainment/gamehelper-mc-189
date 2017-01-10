package net.wfoas.gh.dropsapi.pdr;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.luckyblocksmodule.LuckyBlocksModule;

public class ChestGenerator {
	static List<Material> BONUS_CHEST_LIST, DUNGEON_CHEST_LIST, VILLAGE_CHEST_LIST;
	static List<ItemStack> LUCKY_CHEST;
	static LuckyRandom r;
	static {
		BONUS_CHEST_LIST = new ArrayList<Material>();
		BONUS_CHEST_LIST.add(Material.WOOD);
		BONUS_CHEST_LIST.add(Material.LOG);
		BONUS_CHEST_LIST.add(Material.LOG_2);
		BONUS_CHEST_LIST.add(Material.APPLE);
		BONUS_CHEST_LIST.add(Material.STICK);
		BONUS_CHEST_LIST.add(Material.BREAD);
		BONUS_CHEST_LIST.add(Material.STONE_PICKAXE);
		BONUS_CHEST_LIST.add(Material.WOOD_PICKAXE);
		BONUS_CHEST_LIST.add(Material.WOOD_AXE);
		BONUS_CHEST_LIST.add(Material.STONE_AXE);
		DUNGEON_CHEST_LIST = new ArrayList<Material>();
		DUNGEON_CHEST_LIST.add(Material.SADDLE);
		DUNGEON_CHEST_LIST.add(Material.IRON_INGOT);
		DUNGEON_CHEST_LIST.add(Material.BREAD);
		DUNGEON_CHEST_LIST.add(Material.WHEAT);
		DUNGEON_CHEST_LIST.add(Material.SULPHUR);
		DUNGEON_CHEST_LIST.add(Material.STRING);
		DUNGEON_CHEST_LIST.add(Material.BUCKET);
		DUNGEON_CHEST_LIST.add(Material.REDSTONE);
		DUNGEON_CHEST_LIST.add(Material.INK_SACK);
		DUNGEON_CHEST_LIST.add(Material.RECORD_3);
		DUNGEON_CHEST_LIST.add(Material.RECORD_4);
		DUNGEON_CHEST_LIST.add(Material.RECORD_5);
		DUNGEON_CHEST_LIST.add(Material.RECORD_6);
		DUNGEON_CHEST_LIST.add(Material.RECORD_7);
		DUNGEON_CHEST_LIST.add(Material.RECORD_8);
		DUNGEON_CHEST_LIST.add(Material.RECORD_9);
		DUNGEON_CHEST_LIST.add(Material.RECORD_10);
		DUNGEON_CHEST_LIST.add(Material.RECORD_11);
		DUNGEON_CHEST_LIST.add(Material.RECORD_12);
		DUNGEON_CHEST_LIST.add(Material.GOLDEN_APPLE);
		DUNGEON_CHEST_LIST.add(Material.NAME_TAG);
		DUNGEON_CHEST_LIST.add(Material.IRON_BARDING);
		DUNGEON_CHEST_LIST.add(Material.GOLD_BARDING);
		DUNGEON_CHEST_LIST.add(Material.DIAMOND_BARDING);
		VILLAGE_CHEST_LIST = new ArrayList<Material>();
		VILLAGE_CHEST_LIST.add(Material.BREAD);
		VILLAGE_CHEST_LIST.add(Material.APPLE);
		VILLAGE_CHEST_LIST.add(Material.IRON_INGOT);
		VILLAGE_CHEST_LIST.add(Material.IRON_SWORD);
		VILLAGE_CHEST_LIST.add(Material.IRON_PICKAXE);
		VILLAGE_CHEST_LIST.add(Material.IRON_HELMET);
		VILLAGE_CHEST_LIST.add(Material.IRON_CHESTPLATE);
		VILLAGE_CHEST_LIST.add(Material.IRON_LEGGINGS);
		VILLAGE_CHEST_LIST.add(Material.IRON_BOOTS);
		VILLAGE_CHEST_LIST.add(Material.SAPLING);
		VILLAGE_CHEST_LIST.add(Material.OBSIDIAN);
		VILLAGE_CHEST_LIST.add(Material.GOLD_INGOT);
		VILLAGE_CHEST_LIST.add(Material.DIAMOND);
		VILLAGE_CHEST_LIST.add(Material.IRON_BARDING);
		VILLAGE_CHEST_LIST.add(Material.SADDLE);
		LUCKY_CHEST = new ArrayList<ItemStack>();
		LUCKY_CHEST.add(new ItemStack(Material.DIAMOND.getItem()));
		LUCKY_CHEST.add(new ItemStack(Material.IRON_INGOT.getItem()));
		LUCKY_CHEST.add(new ItemStack(Material.GOLD_INGOT.getItem()));
		LUCKY_CHEST.add(new ItemStack(Material.EMERALD.getItem()));
		LUCKY_CHEST.add(new ItemStack(Material.GOLDEN_APPLE.getItem()));
		LUCKY_CHEST.add(new ItemStack(Material.GOLDEN_APPLE.getItem(), 1, (short)1));
		LUCKY_CHEST.add(new ItemStack(Material.INK_SACK.getItem(), 1, (short)4));
		LUCKY_CHEST.add(new ItemStack(LuckyBlocksModule.DEFAULT_LUCKY_BLOCK));
		r = new LuckyRandom(System.nanoTime());
	}
	
	public static void generateVillagerChest(LocationA l){
		l.getWorld().setBlockState(l.toBlockPos(), Blocks.chest.getDefaultState());
//		Chest c = (Chest)l.getWorld().getBlockAt(l).getState();
		TileEntityChest tec =  (TileEntityChest) l.getWorld().getTileEntity(l.toBlockPos());
//		c.getBlockInventory().clear();
//		Chest
//		l.getWorld().getBlockState(l.toBlockPos()).
		tec.clear();
		int i = r.nextInt(12);
		while(i != 0){
			i--;
			int n3 = r.nextInt(27);
			int n2 = r.nextInt(VILLAGE_CHEST_LIST.size());
			int amnt = r.nextInt(2)+1;
			ItemStack is = new ItemStack(VILLAGE_CHEST_LIST.get(n2).getItem());
			if(Material.equalsItem(is.getItem(), Material.INK_SACK))
				is.setItemDamage((short)3);
			if (!(Material.equalsItem(is.getItem(), Material.IRON_SWORD)
					|| Material.equalsItem(is.getItem(), Material.IRON_PICKAXE)
					|| Material.equalsItem(is.getItem(), Material.IRON_HELMET)
					|| Material.equalsItem(is.getItem(), Material.IRON_CHESTPLATE)
					|| Material.equalsItem(is.getItem(), Material.IRON_LEGGINGS)
					|| Material.equalsItem(is.getItem(), Material.IRON_BOOTS)))
				is.stackSize = amnt;
			tec.setInventorySlotContents(n3, is);
		}
		tec.update();
	}
	
	public static void generateBonusChest(LocationA l){
		l.getWorld().setBlockState(l.toBlockPos(), Blocks.chest.getDefaultState());
//		Chest c = (Chest)l.getWorld().getBlockAt(l).getState();
		TileEntityChest tec =  (TileEntityChest) l.getWorld().getTileEntity(l.toBlockPos());
		tec.clear();
		int i = r.nextInt(12);
		while(i != 0){
			i--;
			int n3 = r.nextInt(27);
			int n2 = r.nextInt(BONUS_CHEST_LIST.size());
			int amnt = r.nextInt(2)+1;
			ItemStack is = new ItemStack(BONUS_CHEST_LIST.get(n2).getItem());
			if(Material.equalsItem(is.getItem(), Material.INK_SACK))
				is.setItemDamage((short)3);
			if (!(Material.equalsItem(is.getItem(), Material.STONE_AXE) || Material.equalsItem(is.getItem(), Material.WOOD_AXE)
					|| Material.equalsItem(is.getItem(), Material.WOOD_PICKAXE) || Material.equalsItem(is.getItem(), Material.STONE_PICKAXE)))
				is.stackSize = amnt;
			tec.setInventorySlotContents(n3, is);
		}
		tec.update();
	}
	
	public static void generateLuckyChest(LocationA l){
		l.getWorld().setBlockState(l.toBlockPos(), Blocks.chest.getDefaultState());
//		Chest c = (Chest)l.getWorld().getBlockAt(l).getState();
		TileEntityChest tec =  (TileEntityChest) l.getWorld().getTileEntity(l.toBlockPos());
		tec.clear();
//		int i = r.nextInt(12);
//		while(i != 0){
//			i--;
//			int n3 = r.nextInt(27);
//			int n2 = r.nextInt(LUCKY_CHEST.size());
//			int amnt = r.nextInt(5)+1;
//			ItemStack is = new ItemStack(LUCKY_CHEST.get(n2));
//			is.setAmount(amnt);
////			if(is.getType() == Material.INK_SACK)
////				is.setDurability((short)3);
////			if(is.getType() != Material.STONE_AXE && is.getType() != Material.WOOD_AXE && is.getType() != Material.WOOD_PICKAXE && is.getType() != Material.STONE_PICKAXE)
////				is.setAmount(amnt);
//			c.getBlockInventory().setItem(n3, is);
//		}
		for(ItemStack is : LUCKY_CHEST){
			ItemStack i = is.copy();
			i.stackSize = r.nextInt(5)+1;
			tec.setInventorySlotContents(r.nextInt(27), i);
		}
		tec.update();
	}
	
	public static void generateDungeonChest(LocationA l){
		l.getWorld().setBlockState(l.toBlockPos(), Blocks.chest.getDefaultState());
//		Chest c = (Chest)l.getWorld().getBlockAt(l).getState();
		TileEntityChest tec =  (TileEntityChest) l.getWorld().getTileEntity(l.toBlockPos());
		tec.clear();
		int i = r.nextInt(12);
		while(i != 0){
			i--;
			int n3 = r.nextInt(27);
			int n2 = r.nextInt(DUNGEON_CHEST_LIST.size());
			int amnt = r.nextInt(2)+1;
			ItemStack is = new ItemStack(DUNGEON_CHEST_LIST.get(n2).getItem());
			if(Material.equalsItem(is.getItem(), Material.INK_SACK))
				is.setItemDamage((short)3);
			if (Material.equalsItem(is.getItem(), Material.STONE_AXE) || Material.equalsItem(is.getItem(), Material.WOOD_AXE)
					|| Material.equalsItem(is.getItem(), Material.WOOD_PICKAXE) || Material.equalsItem(is.getItem(), Material.STONE_PICKAXE))
				is.stackSize = amnt;
			tec.setInventorySlotContents(n3, is);
		}
		tec.update();
	}
}
