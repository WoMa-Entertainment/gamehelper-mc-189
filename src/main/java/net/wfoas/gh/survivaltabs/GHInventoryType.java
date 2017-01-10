package net.wfoas.gh.survivaltabs;

public enum GHInventoryType {
	PLAYER_INVENTORY(0, 0), MINERS_INVENTORY(0, -38);
	final int xoff, yoff;

	GHInventoryType(int xoffset, int yoffset) {
		this.xoff = xoffset;
		this.yoff = yoffset;
	}
}
