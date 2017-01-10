package net.wfoas.gh.enchantment.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.wfoas.gh.dropsapi.pdr.Material;

public class Mobhead {
	public static ItemStack ZOMBIE = getMobHead(
			"MHF_Zombie"), /* WITHER_SKELETON = getMobHead("MHF_WSKELETON"), */
			VILLAGER = getMobHead("MHF_Villager"), PLAYER_STEVE = getMobHead("MHF_Steve"),
			SQUID = getMobHead("MHF_Squid"), SPIDER = getMobHead("MHF_Spider"), SLIME = getMobHead("MHF_Slime"),
			SKELETON = getMobHead("MHF_Skeleton"), SHEEP = getMobHead("MHF_Sheep"),
			ZOMBIE_PIGMAN = getMobHead("MHF_PigZombie"), PIG = getMobHead("MHF_Pig"), OCELOT = getMobHead("MHF_Ocelot"),
			MUSHROOM_COW = getMobHead("MHF_MushroomCow"), MAGMA_SLIME = getMobHead("MHF_LavaSlime"),
			HEROBRINE = getMobHead("MHF_Herobrine"), GOLEM = getMobHead("MHF_Golem"), GHAST = getMobHead("MHF_Ghast"),
			ENDERMAN = getMobHead("MHF_Enderman"), CREEPER = getMobHead("MHF_Creeper"), COW = getMobHead("MHF_Cow"),
			CHICKEN = getMobHead("MHF_Chicken"), CAVE_SPIDER = getMobHead("MHF_CaveSpider"),
			BLAZE = getMobHead("MHF_Blaze"), PLAYER_ALEX = getMobHead("MHF_Alex"),
			BLOCK_CACTUS = getMobHead("MHF_Cactus"), BLOCK_CAKE = getMobHead("MHF_Cake"),
			BLOCK_CHEST = getMobHead("MHF_Chest"), BLOCK_COCONUT_BROWN = getMobHead("MHF_CoconutB"),
			BLOCK_COCONUT_GREEN = getMobHead("MHF_CoconutG"), BLOCK_MELON = getMobHead("MHF_Melon"),
			BLOCK_OAKLOG = getMobHead("MHF_OakLog"), BLOCK_PRESENT1 = getMobHead("MHF_Present1"),
			BLOCK_PRESENT2 = getMobHead("MHF_Present2"), BLOCK_PUMPKIN = getMobHead("MHF_Pumpkin"),
			BLOCK_TNT1 = getMobHead("MHF_TNT"), BLOCK_TNT2 = getMobHead("MHF_TNT2"),
			BONUS_ARROW_UP = getMobHead("MHF_ArrowUp"), BONUS_ARROW_DOWN = getMobHead("MHF_ArrowDown"),
			BONUS_ARROW_LEFT = getMobHead("MHF_ArrowLeft"), BONUS_ARROW_RIGHT = getMobHead("MHF_ArrowRight"),
			BONUS_EXCLAMATION = getMobHead("MHF_Exclamation"), BONUS_QUESTION = getMobHead("MHF_Question"),
			WITHER_SKELETON = new ItemStack(Material.SKULL_ITEM.getItem(), 1, 1),
			SNOWMAN = new ItemStack(Material.PUMPKIN.getItem());

	public static ItemStack getPlayerHead(EntityPlayer player) {
		return getMobHead(player.getName());
	}

	public static ItemStack getMobHead(String name) {
		ItemStack is = new ItemStack(Material.SKULL_ITEM.getItem(), 1, (short) 3);
		is.setTagCompound(new NBTTagCompound());
		is.getTagCompound().setString("SkullOwner", name);
		return is;
	}
}
