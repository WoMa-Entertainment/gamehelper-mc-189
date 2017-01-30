package net.wfoas.gh.protected_blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;

public final class ProtectedBlocksRegistry {
	protected static List<Block> protectedBlocks = new ArrayList<Block>();
	protected static Map<Integer, Block> protectedBlocksMap = new HashMap<Integer, Block>();

	public static void addBlock(Block b, int gui_id) {
		if (!protectedBlocks.contains(b))
			protectedBlocks.add(b);
		if (!protectedBlocksMap.containsKey(gui_id))
			protectedBlocksMap.put(Integer.valueOf(gui_id), b);
	}

	public static int getID(Block b) {
		for (Map.Entry<Integer, Block> val : protectedBlocksMap.entrySet()) {
			if (val.getValue().equals(b))
				return val.getKey().intValue();
		}
		return 0;
	}

	public static Block getBlock(int id) {
		return protectedBlocksMap.get(Integer.valueOf(id));
	}

	public static void removeBlock(Block b) {
		if (protectedBlocks.contains(b))
			protectedBlocks.remove(b);
	}

	public static boolean isProtectedBlock(Block b) {
		return protectedBlocks.contains(b);
	}

}
