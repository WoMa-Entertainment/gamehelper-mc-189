package net.wfoas.gh.dropsapi.pdr;

public abstract class PlayerDropRunnableTNT extends PlayerDropRunnable {

	int fuse;
	boolean randomoffset;

	public PlayerDropRunnableTNT(boolean useplayerloc, int fuse, boolean randomoffset) {
		super(useplayerloc);
		this.fuse = fuse;
		this.randomoffset = randomoffset;
	}

	public int getFuse() {
		return fuse;
	}

	public boolean hasRandomOffset() {
		return this.randomoffset;
	}

}
