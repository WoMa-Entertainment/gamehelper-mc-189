package net.wfoas.gh.thermalinventory.armor;

public enum ThermalType {
	COOLING(-1), WARMING(+1), ALLROUND(0);

	final int type;

	private ThermalType(int t) {
		this.type = t;
	}
}