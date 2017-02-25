package net.wfoas.gh.recipes;

import net.minecraft.block.BlockSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.blocks.GameHelperModBlock;
import net.wfoas.gh.flowers.Flowers;
import net.wfoas.gh.protected_blocks.chest.ProtectedChestTileEntityBlock;
import net.wfoas.gh.protected_blocks.furnace.ProtectedFurnaceBlock;
import net.wfoas.gh.GameHelperCoreModule;

public class RecipeManager {
	public static void addAll() {
		// plugin start
		ArrowSkullRecipes.addAll();
		GameRegistry.addShapedRecipe(new ItemStack(Items.name_tag), "pp ", "ps ", "  f", 'p', Items.paper, 's',
				Items.slime_ball, 'f', Items.string);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.command_block), "igi", "drd", "igi", 'i', Blocks.iron_block,
				'g', Items.glowstone_dust, 'd', Items.diamond, 'r', Blocks.redstone_block);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.BACKPACK), "sws", "lcl", "sws", 's',
				Items.string, 'w', Blocks.wool, 'l', Items.leather, 'c', Blocks.chest);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.ENDER_BACKPACK), "sws", "lcl", "sws", 's',
				Items.string, 'w', Blocks.wool, 'l', Items.leather, 'c', Blocks.ender_chest);
		// when ready: replace obsidian with unchantingtable
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.ENCH_ALTAR), "ede", "dod", "ede", 'e',
				Blocks.enchanting_table, 'd', Items.diamond, 'o', Blocks.obsidian);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.INST_ENCH), "ooo", "oeo", "ooo", 'e',
				Blocks.enchanting_table, 'o', Blocks.bookshelf);
		GameRegistry.addShapelessRecipe(new ItemStack(GameHelperCoreModule.WORLD_TELEPORTER), Items.clock,
				Items.compass, Items.ender_eye);
		GameRegistry.addShapedRecipe(new ItemStack((ProtectedChestTileEntityBlock) GameHelperCoreModule.SEC_CHEST),
				" I ", "ICI", "III", 'I', Items.iron_ingot, 'C', Blocks.chest);
		GameRegistry.addShapedRecipe(new ItemStack((ProtectedFurnaceBlock) GameHelperCoreModule.SEC_FURNACE), " I ",
				"IFI", "III", 'I', Items.iron_ingot, 'F', Blocks.furnace);
		// saved brewingstand
		// saved hopper
		// portable workbench | + item
		GameRegistry.addShapedRecipe(new ItemStack(Items.iron_horse_armor), "HRR", " LL", " BB", 'H', Items.iron_helmet,
				'R', Items.iron_chestplate, 'L', Items.iron_leggings, 'B', Items.iron_boots);
		GameRegistry.addShapedRecipe(new ItemStack(Items.golden_horse_armor), "GGG", "GHG", "GGG", 'G',
				Items.gold_ingot, 'H', Items.iron_horse_armor);
		GameRegistry.addShapedRecipe(new ItemStack(Items.diamond_horse_armor), "GGG", "GHG", "GGG", 'G', Items.diamond,
				'H', Items.iron_horse_armor);
		GameRegistry.addShapedRecipe(new ItemStack(Items.saddle), "L L", "LLL", "TIT", 'L', Items.leather, 'T',
				Blocks.tripwire_hook, 'I', Items.iron_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.TELESCOPE), "GQS", "QGQ", "SQS", 'G',
				Blocks.glass, 'Q', Items.quartz, 'S', Items.stick);

		GameRegistry.addSmelting(Items.rotten_flesh, new ItemStack(Items.leather), 0.7f);
		// plugin end
		GameRegistry.addShapelessRecipe(new ItemStack(GameHelperCoreModule.OBSIDIAN_SHARD, 4), Blocks.obsidian);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.OBSIDIAN_STICKS, 2), "S", "S", 'S',
				GameHelperCoreModule.OBSIDIAN_SHARD);
		GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(Blocks.obsidian)), "SS", "SS", 'S',
				GameHelperCoreModule.OBSIDIAN_SHARD);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.EMERALD_HELMET), "GGG", "G G", 'G',
				Items.emerald);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.SAPPHIRE_HELMET), "GGG", "G G", 'G',
				GameHelperCoreModule.SAPPHIRE_ITEM);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.RUBY_HELMET), "GGG", "G G", 'G',
				GameHelperCoreModule.RUBY_ITEM);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.EMERALD_CHESTPLATE), "G G", "GGG", "GGG", 'G',
				Items.emerald);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.SAPPHIRE_CHESTPLATE), "G G", "GGG", "GGG", 'G',
				GameHelperCoreModule.SAPPHIRE_ITEM);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.RUBY_CHESTPLATE), "G G", "GGG", "GGG", 'G',
				GameHelperCoreModule.RUBY_ITEM);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.EMERALD_LEGGINGS), "GGG", "G G", "G G", 'G',
				Items.emerald);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.SAPPHIRE_LEGGINGS), "GGG", "G G", "G G", 'G',
				GameHelperCoreModule.SAPPHIRE_ITEM);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.RUBY_LEGGINGS), "GGG", "G G", "G G", 'G',
				GameHelperCoreModule.RUBY_ITEM);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.EMERALD_BOOTS), "G G", "G G", 'G',
				Items.emerald);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.SAPPHIRE_BOOTS), "G G", "G G", 'G',
				GameHelperCoreModule.SAPPHIRE_ITEM);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.RUBY_BOOTS), "G G", "G G", 'G',
				GameHelperCoreModule.RUBY_ITEM);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.EMERALD_SWORD), "G", "G", "S", 'G',
				Items.emerald, 'S', GameHelperCoreModule.OBSIDIAN_STICKS);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.SAPPHIRE_SWORD), "G", "G", "S", 'G',
				GameHelperCoreModule.SAPPHIRE_ITEM, 'S', GameHelperCoreModule.OBSIDIAN_STICKS);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.RUBY_SWORD), "G", "G", "S", 'G',
				GameHelperCoreModule.RUBY_ITEM, 'S', GameHelperCoreModule.OBSIDIAN_STICKS);
		GameRegistry.addShapelessRecipe(new ItemStack(GameHelperCoreModule.SAPPHIRE_ITEM, 9),
				GameHelperCoreModule.SAPPHIRE_BLOCK);
		GameRegistry.addShapelessRecipe(new ItemStack(GameHelperCoreModule.RUBY_ITEM, 9),
				GameHelperCoreModule.RUBY_BLOCK);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.SAPPHIRE_ITEM, 9), "G", 'G',
				GameHelperCoreModule.SAPPHIRE_BLOCK);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.RUBY_BLOCK), "GGG", "GGG", "GGG", 'G',
				GameHelperCoreModule.RUBY_ITEM);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.SAPPHIRE_BLOCK), "GGG", "GGG", "GGG", 'G',
				GameHelperCoreModule.SAPPHIRE_ITEM);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.SAPPHIRE_BS), "G", "G", "S", 'G',
				Item.getItemFromBlock(GameHelperCoreModule.SAPPHIRE_BLOCK), 'S', GameHelperCoreModule.OBSIDIAN_STICKS);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.EMERALD_BS), "G", "G", "S", 'G',
				Item.getItemFromBlock(Blocks.emerald_block), 'S', GameHelperCoreModule.OBSIDIAN_STICKS);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.RUBY_BS), "G", "G", "S", 'G',
				Item.getItemFromBlock(GameHelperCoreModule.RUBY_BLOCK), 'S', GameHelperCoreModule.OBSIDIAN_STICKS);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.GOLD_BS), "G", "G", "S", 'G',
				Item.getItemFromBlock(Blocks.gold_block), 'S', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.IRON_BS), "G", "G", "S", 'G',
				Item.getItemFromBlock(Blocks.iron_block), 'S', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.DIAMOND_BS), "G", "G", "S", 'G',
				Item.getItemFromBlock(Blocks.diamond_block), 'S', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.STONE_BS), "G", "G", "S", 'G',
				Item.getItemFromBlock(Blocks.furnace), 'S', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.WOOD_BS), "G", "G", "S", 'G',
				Item.getItemFromBlock(Blocks.crafting_table), 'S', Items.stick);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.PINK.getDyeDamage()),
				Flowers.Paeonia_suffruticosa);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.RED.getDyeDamage()), Flowers.Rose);
		addPureBlockRec(GameHelperCoreModule.Purewhite, 0);
		addPureBlockRec(GameHelperCoreModule.Pureorange, 1);
		addPureBlockRec(GameHelperCoreModule.Puremagenta, 2);
		addPureBlockRec(GameHelperCoreModule.Purelightblue, 3);
		addPureBlockRec(GameHelperCoreModule.Pureyellow, 4);
		addPureBlockRec(GameHelperCoreModule.Purelime, 5);
		addPureBlockRec(GameHelperCoreModule.Purepink, 6);
		addPureBlockRec(GameHelperCoreModule.Puregray, 7);
		addPureBlockRec(GameHelperCoreModule.Purelightgray, 8);
		addPureBlockRec(GameHelperCoreModule.Purecyan, 9);
		addPureBlockRec(GameHelperCoreModule.Purepurple, 10);
		addPureBlockRec(GameHelperCoreModule.Pureblue, 11);
		addPureBlockRec(GameHelperCoreModule.Purebrown, 12);
		addPureBlockRec(GameHelperCoreModule.Puregreen, 13);
		addPureBlockRec(GameHelperCoreModule.Purered, 14);
		addPureBlockRec(GameHelperCoreModule.Pureblack, 15);
		GameRegistry.addShapedRecipe(new ItemStack(GameHelperCoreModule.DIMENSION_SHARD), "OE", "EY", 'O',
				Blocks.obsidian, 'E', Items.ender_pearl, 'Y', Items.ender_pearl);
	}

	protected static void addPureTileBlockRec(GameHelperModBlock b, int meta) {

	}

	protected static void addPureBlockRec(GameHelperModBlock b, int meta) {
		GameRegistry.addShapelessRecipe(new ItemStack(b, 4), new ItemStack(Blocks.wool, 1, meta),
				new ItemStack(Blocks.wool, 1, meta), new ItemStack(Blocks.stained_hardened_clay, 1, meta),
				new ItemStack(Blocks.stained_hardened_clay, 1, meta));
	}
}