package net.wfoas.gh.dropsapi;

public class WFShematicsReader {
//	public static Map<String, ShematicsCollection> SHEMATICS_MAP = new HashMap<String, ShematicsCollection>();
//
//	public static ShematicsCollection readShematicsERR0(File f) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
//		List<String> s = new ArrayList<String>();
//		String line;
//		while ((line = br.readLine()) != null) {
//			if (line.startsWith("#version"))
//				continue;
//			if(line.startsWith("//"))
//				continue;
//			s.add(line);
//		}
//		br.close();
//		return readShematics(s);
//	}
//	
//	public static void addShematicsToMap(ShematicsCollection sh) {
//		SHEMATICS_MAP.put(sh.getShematicsVarName(), sh);
//	}
//
//	// public static void main(String...strings){
//	// ShematicsCollection sc = readShematics(new
//	// String[]{"50;456;94156153-DIRT"});
//	//// sc.println();
//	// }
//
//	public static ShematicsCollection readShematics(List<String> stringlist) {
//		String[] string = new String[stringlist.size()];
//		stringlist.toArray(string);
//		return readShematics(string);
//	}
//
//	static int x = 0, y = 0, z = 0;
//	static Material m = null;
//	static String name;
//	static int meta;
//	static List<PlayerDropRunnableBlocksPlace> list = new ArrayList<PlayerDropRunnableBlocksPlace>();
//
//	public static ShematicsCollection readShematics(String[] stringlist) {
//		list.clear();
//		name = null;
//		meta = 0;
//		x = 0;
//		y = 0;
//		z = 0;
//		m = null;
//		for (String string : stringlist) {
//			if(string.startsWith("#name=")){
//				if(name == null)
//					name = string.replaceFirst("#name=", "");
//				continue;
//			}
//			if (string.startsWith("#version"))
//				continue;
//			if (string.startsWith("//"))
//				continue;
//			String[] values = string.split("#");
//			String[] locs = values[0].split(";");
//			x = Integer.parseInt(locs[0]);
//			y = Integer.parseInt(locs[1]);
//			z = Integer.parseInt(locs[2]);
//			m = Material.parseFromString(values[1]);
//			meta = Integer.parseInt(values[2]);
//			list.add(new PlayerDropRunnableBlocksPlace(x, y, z, m, meta));
//		}
//		if(name == null)
//			name = "REPL-NAME" + System.nanoTime();
//		return new ShematicsCollection(list, name);
//	}
}
