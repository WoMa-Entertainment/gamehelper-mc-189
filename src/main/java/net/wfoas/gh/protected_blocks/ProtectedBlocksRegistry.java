package net.wfoas.gh.protected_blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.blocks.IGHModBlock;

public final class ProtectedBlocksRegistry {
	protected static List<Block> protectedBlocks = new ArrayList<Block>();
	protected static Map<Integer, List<Block>> protectedBlocksMap = new HashMap<Integer, List<Block>>();
	protected static Map<Block, Integer> protectedBlockReverseMap = new HashMap<Block, Integer>();

	public static List<Block> toList(IGHModBlock... ws) {
		ArrayList<Block> list = new ArrayList<Block>();
		for (IGHModBlock b : ws) {
			if (b instanceof Block)
				list.add((Block) b);
		}
		return list;
	}

	public static void addBlock(int gui_id, IGHModBlock... b) {
		for (IGHModBlock b1 : b) {
			if (!protectedBlocks.contains(b1)) {
				if (b1 instanceof Block) {
					protectedBlocks.add((Block) b1);
					protectedBlockReverseMap.put((Block) b1, Integer.valueOf(gui_id));
				}
			}
		}
		if (!protectedBlocksMap.containsKey(gui_id)) {
			protectedBlocksMap.put(Integer.valueOf(gui_id), toList(b));
			return;
		} else {
			List<Block> l = protectedBlocksMap.remove(Integer.valueOf(gui_id));
			l.addAll(toList(b));
			protectedBlocksMap.put(Integer.valueOf(gui_id), l);
			return;
		}
	}

	public static int getID(Block b) {
		if (!protectedBlockReverseMap.containsKey(b))
			return 0;
		return protectedBlockReverseMap.get(b);
	}

	public static List<Block> getBlock(int id) {
		return protectedBlocksMap.get(Integer.valueOf(id));
	}

	public static void removeBlock(Block b) {
		if (protectedBlocks.contains(b))
			protectedBlocks.remove(b);
	}

	public static boolean isProtectedBlock(Block b) {
		return protectedBlocks.contains(b);
	}

	public static void checkDataAndUpdateData(int id, int x, int y, int z, World ws, final MessageContext ctx) {
		TileEntity te = ws.getTileEntity(new BlockPos(x, y, z));
		if (te instanceof IProtectedBlock) {
			IProtectedBlock i = (IProtectedBlock) te;
			ctx.getServerHandler().sendPacket(i.getCUDescrPacket());
		}
		return;
	}

}
