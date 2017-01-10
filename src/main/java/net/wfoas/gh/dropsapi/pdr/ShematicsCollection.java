package net.wfoas.gh.dropsapi.pdr;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;

public class ShematicsCollection extends PlayerDropRunnable {
	PlayerDropRunnableBlocksPlace[] pdrbpa;
	String name;

	public ShematicsCollection(PlayerDropRunnableBlocksPlace[] pdrbpa, String name) {
		super(false);
		this.pdrbpa = pdrbpa;
		this.name = name;
	}

	public ShematicsCollection(List<PlayerDropRunnableBlocksPlace> pdrbpa, String name) {
		this(toArray(pdrbpa), name);
	}

	public static PlayerDropRunnableBlocksPlace[] toArray(List<PlayerDropRunnableBlocksPlace> pdrbpa) {
		PlayerDropRunnableBlocksPlace[] p = new PlayerDropRunnableBlocksPlace[pdrbpa.size()];
		pdrbpa.toArray(p);
		return p;
	}
	
	public String getShematicsVarName(){
		return name;
	}
	
	@Override
	public void run(EntityPlayer p, Vec3d l){
		run(p, l, false);
	}
	
	
	public void run(EntityPlayer p, Vec3d l, boolean playerLoc) {
		for (PlayerDropRunnableBlocksPlace ok : pdrbpa) {
			if(super.usesPlayerLoc())
				ok.run(p.worldObj, p.getPosition());
			else
				ok.run(p.worldObj, l.toBlockPos());
		}
	}
}
