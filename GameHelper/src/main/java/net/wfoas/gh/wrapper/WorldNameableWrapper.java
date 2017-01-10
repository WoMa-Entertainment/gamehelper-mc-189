package net.wfoas.gh.wrapper;

import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IWorldNameable;

public class WorldNameableWrapper implements IWorldNameable {
	String displayname;

	public WorldNameableWrapper(String displayname) {
		this.displayname = displayname;
	}

	@Override
	public String getName() {
		return displayname;
	}

	@Override
	public boolean hasCustomName() {
		return displayname != null;
	}

	@Override
	public IChatComponent getDisplayName() {
		return new ChatComponentTranslation(displayname);
	}

}
