package net.wfoas.gh.sound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SoundHandlerGH {

	static List<String> gamehelper_sounds;

	public static final String SOUND_WOW = "gamehelper:wow", SOUND_NICE = "gamehelper:nice",
			SOUND_OHYEAH = "gamehelper:ohyeah";

	// 1.11 stuff
	public static void init() {
		gamehelper_sounds = new ArrayList<String>();
	}

	public static void addSound(String sound) {
		gamehelper_sounds.add(sound);
	}

	public static final List<String> getSounds() {
		return Collections.unmodifiableList(gamehelper_sounds);
	}
}
