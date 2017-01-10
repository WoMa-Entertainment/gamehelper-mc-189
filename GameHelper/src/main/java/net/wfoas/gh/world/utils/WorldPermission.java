package net.wfoas.gh.world.utils;

public enum WorldPermission {
	VISIT(0), BUILD(1), DESTROY(2), COLLECT_ITEMS_EXP(3), KILL(4), INTERACT(5);
	final int netcode;

	WorldPermission(int param1) {
		this.netcode = param1;
	}

	public final int getNetcode() {
		return netcode;
	}

	public static final WorldPermission byNetcode(int netcode) {
		for (WorldPermission wp : WorldPermission.values()) {
			if (wp.getNetcode() == netcode)
				return wp;
		}
		return null;
	}
}
