package net.wfoas.gh.dropsapi.pdr;

import java.util.List;

public abstract class PlayerDropRunnableItemDamageAttrib extends PlayerDropRunnableItem {
	short dmg;
	
	public PlayerDropRunnableItemDamageAttrib(
			boolean useplayerloc, String usename, Material m, int size,
			List<ItemModifyRunnable> ENCHLIST, short dmg) {
		super(useplayerloc, usename, m, size, ENCHLIST);
		this.dmg = dmg;
	}
	
	public short getDmg(){
		return dmg;
	}

}

