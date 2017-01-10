package net.wfoas.gh.dropsapi.pdr;

public abstract class PlayerDropRunnableCommand extends PlayerDropRunnable {

	String cmd;

	public PlayerDropRunnableCommand(String cmd, boolean asPlayer) {
		super(asPlayer);
		this.cmd = cmd;
	}

	public boolean asPlayer() {
		return this.usesPlayerLoc();
	}

	public boolean asConsoleOrCMDBlock() {
		return !this.usesPlayerLoc();
	}

}
