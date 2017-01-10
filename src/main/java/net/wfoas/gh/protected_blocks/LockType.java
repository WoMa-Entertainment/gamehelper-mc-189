package net.wfoas.gh.protected_blocks;

public enum LockType {
	ALL_PLAYERS((byte) 2), WHITELISTED_PLAYERS((byte) 1), ONLY_OWNER((byte) 0);
	final byte id;

	LockType(final byte id) {
		this.id = id;
	}

	public final byte getId() {
		return id;
	}

	public static final LockType getFromId(final byte id) {
		for (LockType l : LockType.values()) {
			if (id == l.id)
				return l;
		}
		return LockType.ONLY_OWNER;
	}
}
