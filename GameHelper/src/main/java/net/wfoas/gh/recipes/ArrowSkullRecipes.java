package net.wfoas.gh.recipes;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.wfoas.gh.enchantment.event.Mobhead;

public class ArrowSkullRecipes {
	public static void addAll() {
		GameRegistry.addShapedRecipe(Mobhead.BONUS_ARROW_RIGHT, "W  ", "WW ", "W  ", 'W', Blocks.planks);
		GameRegistry.addShapedRecipe(Mobhead.BONUS_ARROW_LEFT, " W ", "WW ", " W ", 'W', Blocks.planks);
		GameRegistry.addShapedRecipe(Mobhead.BONUS_ARROW_DOWN, "WWW", " W ", 'W', Blocks.planks);
		GameRegistry.addShapedRecipe(Mobhead.BONUS_ARROW_UP, " W ", "WWW", 'W', Blocks.planks);
		GameRegistry.addShapedRecipe(Mobhead.BONUS_QUESTION, "WW ", " W ", " WW", 'W', Blocks.planks);
		GameRegistry.addShapedRecipe(Mobhead.BONUS_ARROW_UP, "WWW", "WWW", " W ", 'W', Blocks.planks);
	}
}
