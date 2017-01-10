package net.wfoas.gh.dropsapi.pdr;

import java.util.List;

public abstract class PlayerDropRunnableItem extends PlayerDropRunnable {

	int size;
	String usename;
	List<ItemModifyRunnable> ENCHLIST;
	Material m;
	public PlayerDropRunnableItem(boolean useplayerloc,
			String usename, Material m, int size, List<ItemModifyRunnable> ENCHLIST) {
		super(useplayerloc);
		this.size = size;
		this.usename = usename;
		this.ENCHLIST = ENCHLIST;
		this.m = m;
	}
	
	public Material getMaterial(){
		return m;
	}
	
	public int getSize(){
		return size;
	}
	
	public String getUseName(){
		return usename;
	}
	
	public List<ItemModifyRunnable> getEnchList(){
		return ENCHLIST;
	}
}
