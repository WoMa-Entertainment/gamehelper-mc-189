package net.wfoas.gh.luckyblocksmodule.dropsapi.structure;

import java.lang.reflect.Array;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.wfoas.gh.parseapi.LuckyBlocksDropRunnable;

public class Structure implements LuckyBlocksDropRunnable {

	String name;

	IStructurePart[] structure;

	public Structure(IStructurePart[] isp, String name) {
		this.structure = isp;
		this.name = name;
	}

	public Structure(List<IStructurePart> structure, String name) {
		this(toArray(structure, IStructurePart.class), name);
	}

	@Override
	public void executeLuckyBlockDrop(EntityPlayer player, BlockPos pos) {
		for (IStructurePart s : structure) {
			System.out.println(s);
			s.create(pos, player);
		}
	}

	public String getName() {
		return name;
	}

	private static <T> T[] toArray(List<T> list, Class<T> clazz) {
		T[] t = (T[]) Array.newInstance(clazz, list.size());
		for (int i = 0; i < list.size(); i++) {
			t[i] = list.get(i);
		}
		return t;
	}
}
