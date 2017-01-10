package net.wfoas.gh.protected_blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;

public final class ProtectedBlocksRegistry {
	protected static List<Block> protectedBlocks = new ArrayList<Block>();

	public static void addBlock(Block b) {
		if (!protectedBlocks.contains(b))
			protectedBlocks.add(b);
	}

	public static void removeBlock(Block b) {
		if (protectedBlocks.contains(b))
			protectedBlocks.remove(b);
	}

	public static boolean isProtectedBlock(Block b) {
		return protectedBlocks.contains(b);
	}

}
