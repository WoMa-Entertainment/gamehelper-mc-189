package net.wfoas.gh.luckyblocksmodule.dropsapi.structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.wfoas.gh.dropsapi.pdr.Material;

public class StructureParserFromWFShematics {
	public static Map<String, Structure> STRUCTURE_MAP = new HashMap<String, Structure>();

	public static Structure readShematicsERR0(File f) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		List<String> s = new ArrayList<String>();
		String line;
		while ((line = br.readLine()) != null) {
			if (line.startsWith("#version"))
				continue;
			if (line.startsWith("//"))
				continue;
			s.add(line);
		}
		br.close();
		return readShematics(s);
	}

	public static void addShematicsToMap(Structure sh) {
		STRUCTURE_MAP.put(sh.getName(), sh);
	}

	public static void main(String... strings) {
		// ShematicsCollection sc = readShematics(new String[] {
		// "50;456;94156153-DIRT" });
		// sc.println();
	}

	public static Structure readShematics(List<String> stringlist) {
		String[] string = new String[stringlist.size()];
		stringlist.toArray(string);
		return readShematics(string);
	}

	static int x = 0, y = 0, z = 0;
	static boolean relativePos = false;
	static boolean usePlayerPosition = false;
	static Material m = null;
	static String name;
	static int meta;
	static List<IStructurePart> list = new ArrayList<IStructurePart>();

	public static Structure readShematics(String[] stringlist) {
		list.clear();
		name = null;
		meta = 0;
		x = 0;
		y = 0;
		z = 0;
		m = null;
		for (String string : stringlist) {
			if (string.startsWith("#name=")) {
				if (name == null)
					name = string.replaceFirst("#name=", "");
				continue;
			}
			if (string.startsWith("#version"))
				continue;
			if (string.startsWith("#relativepos")) {
				relativePos = Boolean.parseBoolean(string.replaceAll("#relativepos=", ""));
				continue;
			}
			if (string.startsWith("#useplayerpos")) {
				usePlayerPosition = Boolean.parseBoolean(string.replaceAll("#useplayerpos=", ""));
				continue;
			}
			if (string.startsWith("#settileentity")) {

				continue;
			}
			if (string.startsWith("//"))
				continue;
			String[] values = string.split("#");
			String[] locs = values[0].split(";");
			int x = Integer.parseInt(locs[0]);
			int y = Integer.parseInt(locs[1]);
			int z = Integer.parseInt(locs[2]);
			Material m = Material.parseFromString(values[1]);
			int meta = Integer.parseInt(values[2]);
			boolean relativePos = StructureParserFromWFShematics.relativePos;
			boolean usePlayerPosition = StructureParserFromWFShematics.usePlayerPosition;
			list.add(new StructurePartBlock(m, (Integer.valueOf(x).intValue()), y, z, relativePos, meta,
					usePlayerPosition));
		}
		if (name == null)
			name = "REPL-NAME" + System.nanoTime();
		return new Structure(list, name);
	}
}
