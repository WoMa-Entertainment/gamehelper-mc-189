package net.wfoas.gh.parseapi;

import java.io.File;
import java.util.List;

import net.wfoas.gh.GameHelper;

public class LBDLParser {
	
	@SuppressWarnings("null")
	public static DropsCollector searchDrops(String luckyBlock){
		List<LuckyBlocksDrop> luckydrops = null, neutraldrops = null, unluckydrops = null, brunnendrops = null, combatdrops = null, potiondrops = null;
//		GameHelper.instance.
//		lb.getDataFolder().mkdir();
		//Lucky
		File f2 = new File(GameHelper.getDataConfigFolder().getPath() + File.separator + "LUCKYBLOCK_" + luckyBlock + File.separator + "luckydrops");
		f2.mkdirs();
		File[] fa = f2.listFiles();
		luckydrops.clear();
		if(fa.length == 0||fa==null){
			
		} else {
		for(File file : fa){
			if(file.getAbsolutePath().endsWith(".wflbd")){
//				try {
//					Drop d = DropParser.parseDrop(file);
////					luckydrops.add(d);
////					Drop.DropParser.DROP_SORTER_LUCKY.put(DropParser.CURR_NAME, d);
//				} catch (IOException | RuntimeException ioe) {
//					ioe.printStackTrace();
//					continue;
//				}
			}
		}
		}
		//Neutral
		File f3 = new File(GameHelper.getDataConfigFolder().getPath() + File.separator + "LUCKYBLOCK_" + luckyBlock + File.separator + "neutraldrops");
		f3.mkdirs();
		File[] fa2 = f3.listFiles();
		neutraldrops.clear();
		if(fa2.length == 0||fa2==null){
			
		} else {
		for(File file : fa2){
			if(file.getAbsolutePath().endsWith(".wflbd")){
//				try {
////					LuckyBlocksDrop d = DropParser.parseDrop(file);
////					neutraldrops.add(d);
////					Drop.DropParser.DROP_SORTER_NORMAL.put(DropParser.CURR_NAME, d);
//				} catch (IOException | RuntimeException ioe) {
//					ioe.printStackTrace();
//					continue;
//				}
			}
		}
		}
		//Unlucky
		File f4 = new File(GameHelper.getDataConfigFolder().getPath() + File.separator + "LUCKYBLOCK_" + luckyBlock + File.separator + "unluckydrops");
		f4.mkdirs();
		File[] fa3 = f4.listFiles();
		unluckydrops.clear();
		if(fa3.length == 0||fa3==null){
			
		} else {
		for(File file : fa3){
			if(file.getAbsolutePath().endsWith(".wflbd")){
//				try {
//					Drop d = DropParser.parseDrop(file);
////					unluckydrops.add(d);
////					Drop.DropParser.DROP_SORTER_UNLUCKY.put(DropParser.CURR_NAME, d);
//				} catch (IOException | RuntimeException ioe) {
//					ioe.printStackTrace();
//					continue;
//				}
			}
		}
		}
		//Brunnen
		File f5 = new File(GameHelper.getDataConfigFolder().getPath() + File.separator + "LUCKYBLOCK_" + luckyBlock + File.separator + "brunnendrops");
		f5.mkdirs();
		File[] fa4 = f5.listFiles();
		brunnendrops.clear();
		if(fa4.length == 0||fa4==null){
			
		} else {
		for(File file : fa4){
			if(file.getAbsolutePath().endsWith(".wflbd")){
//				try {
//					Drop d = DropParser.parseDrop(file);
////					brunnendrops.add(d);
////					Drop.DropParser.DROP_SORTER_BRUNNEN.put(DropParser.CURR_NAME, d);
//				} catch (IOException | RuntimeException ioe) {
//					ioe.printStackTrace();
//					continue;
//				}
			}
		}
		}
//		alldrops = luckydrops.size() + neutraldrops.size() + unluckydrops.size() + brunnendrops.size();
//		GameHelper.println(GameHelper.LUCKY_MODULE.PREFIX + "Es wurden insgesammt '" + alldrops + "' Drops registriert für den Typ '" + luckyBlock + "'.");
		System.out.println(File.separator + "LUCKYBLOCK_" + luckyBlock + File.separator);
//		List<Drop> luckyDrop = Drop.luckydrops;
//		List<Drop> neutral = Drop.neutraldrops;
//		List<Drop> unlucky = Drop.unluckydrops;
//		List<Drop> brunnen = Drop.brunnendrops;
//		Drop.luckydrops   = new ArrayList<Drop>();
//		Drop.neutraldrops = new ArrayList<Drop>();
//		Drop.unluckydrops = new ArrayList<Drop>();
//		Drop.brunnendrops = new ArrayList<Drop>();
//		return new DropsCollector(luckyDrop, neutral, unlucky, brunnen);
	return null;
	}
}
