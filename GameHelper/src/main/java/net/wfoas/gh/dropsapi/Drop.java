package net.wfoas.gh.dropsapi;

public class Drop {
//	public static int alldrops = 0;
//	public static List<Drop> luckydrops = new ArrayList<Drop>();
//	public static List<Drop> neutraldrops = new ArrayList<Drop>();
//	public static List<Drop> unluckydrops = new ArrayList<Drop>();
//	public static List<Drop> brunnendrops = new ArrayList<Drop>();
//	private PlayerDropRunnable[] r;
//	
//	public static DropsCollector searchDrops(String luckyBlock){
////		GameHelper.instance.
////		lb.getDataFolder().mkdir();
//		//Lucky
//		File f2 = new File(GameHelper.getDataConfigFolder().getPath() + File.separator + "LUCKYBLOCK_" + luckyBlock + File.separator + "luckydrops");
//		f2.mkdirs();
//		File[] fa = f2.listFiles();
//		luckydrops.clear();
//		if(fa.length == 0||fa==null){
//			
//		} else {
//		for(File file : fa){
//			if(file.getAbsolutePath().endsWith(".wflbd")){
//				try {
//					Drop d = DropParser.parseDrop(file);
//					luckydrops.add(d);
//					Drop.DropParser.DROP_SORTER_LUCKY.put(DropParser.CURR_NAME, d);
//				} catch (IOException | RuntimeException ioe) {
//					ioe.printStackTrace();
//					continue;
//				}
//			}
//		}
//		}
//		//Neutral
//		File f3 = new File(GameHelper.getDataConfigFolder().getPath() + File.separator + "LUCKYBLOCK_" + luckyBlock + File.separator + "neutraldrops");
//		f3.mkdirs();
//		File[] fa2 = f3.listFiles();
//		neutraldrops.clear();
//		if(fa2.length == 0||fa2==null){
//			
//		} else {
//		for(File file : fa2){
//			if(file.getAbsolutePath().endsWith(".wflbd")){
//				try {
//					Drop d = DropParser.parseDrop(file);
//					neutraldrops.add(d);
//					Drop.DropParser.DROP_SORTER_NORMAL.put(DropParser.CURR_NAME, d);
//				} catch (IOException | RuntimeException ioe) {
//					ioe.printStackTrace();
//					continue;
//				}
//			}
//		}
//		}
//		//Unlucky
//		File f4 = new File(GameHelper.getDataConfigFolder().getPath() + File.separator + "LUCKYBLOCK_" + luckyBlock + File.separator + "unluckydrops");
//		f4.mkdirs();
//		File[] fa3 = f4.listFiles();
//		unluckydrops.clear();
//		if(fa3.length == 0||fa3==null){
//			
//		} else {
//		for(File file : fa3){
//			if(file.getAbsolutePath().endsWith(".wflbd")){
//				try {
//					Drop d = DropParser.parseDrop(file);
//					unluckydrops.add(d);
//					Drop.DropParser.DROP_SORTER_UNLUCKY.put(DropParser.CURR_NAME, d);
//				} catch (IOException | RuntimeException ioe) {
//					ioe.printStackTrace();
//					continue;
//				}
//			}
//		}
//		}
//		//Brunnen
//		File f5 = new File(GameHelper.getDataConfigFolder().getPath() + File.separator + "LUCKYBLOCK_" + luckyBlock + File.separator + "brunnendrops");
//		f5.mkdirs();
//		File[] fa4 = f5.listFiles();
//		brunnendrops.clear();
//		if(fa4.length == 0||fa4==null){
//			
//		} else {
//		for(File file : fa4){
//			if(file.getAbsolutePath().endsWith(".wflbd")){
//				try {
//					Drop d = DropParser.parseDrop(file);
//					brunnendrops.add(d);
//					Drop.DropParser.DROP_SORTER_BRUNNEN.put(DropParser.CURR_NAME, d);
//				} catch (IOException | RuntimeException ioe) {
//					ioe.printStackTrace();
//					continue;
//				}
//			}
//		}
//		}
//		alldrops = luckydrops.size() + neutraldrops.size() + unluckydrops.size() + brunnendrops.size();
//		GameHelper.println(GameHelper.LUCKY_MODULE.PREFIX + "Es wurden insgesammt '" + alldrops + "' Drops registriert für den Typ '" + luckyBlock + "'.");
//		System.out.println(File.separator + "LUCKYBLOCK_" + luckyBlock + File.separator);
//		List<Drop> luckyDrop = Drop.luckydrops;
//		List<Drop> neutral = Drop.neutraldrops;
//		List<Drop> unlucky = Drop.unluckydrops;
//		List<Drop> brunnen = Drop.brunnendrops;
//		Drop.luckydrops   = new ArrayList<Drop>();
//		Drop.neutraldrops = new ArrayList<Drop>();
//		Drop.unluckydrops = new ArrayList<Drop>();
//		Drop.brunnendrops = new ArrayList<Drop>();
//		return new DropsCollector(luckyBlock, luckyDrop, neutral, unlucky, brunnen);
//	}
//	
//	public static void executeRandomBrunnenDrop(Vec3d loc){
//		int randomdrop = rand.nextInt(brunnendrops.size());
//		brunnendrops.get(randomdrop).exec(null, new Vec3d(loc).getRelative(0, 5, 0));
//	}
//	
//	static LuckyRandom rand = new LuckyRandom();
//	
//	public static void executeRandomDrop(EntityPlayer player, Vec3d loc){
//		Luck l = rand.nextLuckValue();
//		int randomdrop = 0;
//		Drop d = null;
//		switch (l){
//		case LUCKY:
//			randomdrop = rand.nextInt(luckydrops.size());
//			d = luckydrops.get(randomdrop); 
//			break;
//		case NEUTRAL:
//			randomdrop = rand.nextInt(neutraldrops.size());
//			d = neutraldrops.get(randomdrop); 
//			break;
//		case UNLUCKY:
//			randomdrop = rand.nextInt(unluckydrops.size());
//			d = unluckydrops.get(randomdrop); 
//			break;
//		default:
//			randomdrop = rand.nextInt(neutraldrops.size());
//			d = neutraldrops.get(randomdrop);
//			break;
//		}
//		
//		d.exec(player, loc);
//	}
//	String name;
//	private Drop(PlayerDropRunnable[] r, String name) {
////		if (r == null) {
////			r = new PlayerDropRunnable() {
////				public void run(Player p, Location l) {
////					
////				}
////			};
////		}
//		this.name = name;
//		this.r = r;
//	}
//	
//	public String getName(){
//		return name;
//	}
//
//	public void exec(EntityPlayer p, Vec3d l) {
////		if (!p.worldObj.isRemote) {
//			GameHelper.broadcastS("isRemote");
//			// System.out.print("-----");
//			for (PlayerDropRunnable pdr : r) {
//				if (pdr != null) {
//					pdr.run(p, l);
//					// System.out.println(pdr.getName());
//				}
//			}
//			;
////		} else {
////			GameHelper.broadcastS("isLocal");
////		}
//	}
//	
//	public static void main(String[] args){
////		String[] s = {
////				"dropItem;DIAMOND_SWoRD;5,WFOAS IST EIN GUTES NETZWERK;LOOTING,50;FIRE_ASPECT,150;SHARPNESS,1024;Protection,154156;thorns,156561;",
////				"spawn;VILLAGER;Very Lucky Villager",
////				"dropItem;OBSIDIAN;1,Teil von Winstons Computer;",
////				"brunnen;"
////		};
//		List<String> list = new ArrayList<String>();
//		list.add("usePlayerLoc:::dropItem;DIAMOND_SWoRD;5,Lucky Sword;LOOTING,50;FIRE_ASPECT,150;SHARPNESS,1024;Protection,154156;thorns,156561;");
//		list.add("useBlockLoc:::dropItem;EFFECT_POTION;1,Â§cLuckyPotion;NIGHT_VISION,1,6020;JUMP_BOOST,5,6020;");
//		DropParser.parseDrop(list);
//	}
//	
//	public static class DropParser {
//		static String name;
//		static int size;
//		static short dmg = 0;
//		static String[] array2;
//		static ItemModifyRunnable ier;
//		
//		public static Drop parseDrop(List<String> toParse){
//			PlayerDropRunnable[] pdr = new PlayerDropRunnable[toParse.size()];
//			int i = 0;
//			for(String s : toParse){
//				pdr[i] = parseDrop(s);
//				i ++;
//			}
//			return new Drop(pdr, CURR_NAME);
//		}
//		
//		public static Drop parseDrop(String[] toParse){
////			Drop[] d = new Drop[toParse.length];
//			PlayerDropRunnable[] pdr = new PlayerDropRunnable[toParse.length];
//			int i = 0;
//			for(String s : toParse){
//				pdr[i] = parseDrop(s);
//				i ++;
//			}
//			return new Drop(pdr, CURR_NAME);
//		}
//		
//		public static Color getColor(int i) {
//			Color c = null;
//			if (i == 1) {
//				c = Color.AQUA;
//			}
//			if (i == 2) {
//				c = Color.BLACK;
//			}
//			if (i == 3) {
//				c = Color.BLUE;
//			}
//			if (i == 4) {
//				c = Color.FUCHSIA;
//			}
//			if (i == 5) {
//				c = Color.GRAY;
//			}
//			if (i == 6) {
//				c = Color.GREEN;
//			}
//			if (i == 7) {
//				c = Color.LIME;
//			}
//			if (i == 8) {
//				c = Color.MAROON;
//			}
//			if (i == 9) {
//				c = Color.NAVY;
//			}
//			if (i == 10) {
//				c = Color.OLIVE;
//			}
//			if (i == 11) {
//				c = Color.ORANGE;
//			}
//			if (i == 12) {
//				c = Color.PURPLE;
//			}
//			if (i == 13) {
//				c = Color.RED;
//			}
//			if (i == 14) {
//				c = Color.SILVER;
//			}
//			if (i == 15) {
//				c = Color.TEAL;
//			}
//			if (i == 16) {
//				c = Color.WHITE;
//			}
//			if (i == 17) {
//				c = Color.YELLOW;
//			}
//			return c;
//		}
//		
//		static EntityType ett = null;
//		static boolean playerloc;
//		static String CURR_NAME;
//		static public int nextChestLocation(){
//			return rand.nextInt(26);
//		}
//		static public ItemStack nextRandomAmountItem(Material m, int max, short dmg){
//			return new ItemStack(m.getItem(), rand.nextInt(max), dmg);
//		}
//		static public ItemStack nextRandomAmountItem(Material m, int max){
//			return new ItemStack(m.getItem(), rand.nextInt(max));
//		}
//		static public ItemStack nextRandomAmountLapis(int max){
//			return new ItemStack(Material.INK_SACK.getItem(), rand.nextInt(max), (short)4);
//		}
//		
////		public static Map<String, Boolean> PLAYERLOCMAP = new HashMap<String, Boolean>();
////		public static Map<String, Drop> DROPMAP = new HashMap<String, Drop>();
////		public static Map<String, String> ITEM_MOB_NAME_MAP = new HashMap<String, String>();
////		public static Map<String, Integer> ITEM_DROP_AMNT_MAP = new HashMap<String, Integer>();
////		public static Map<String, EntityType> MOB_TYPE_MAP = new HashMap<String, EntityType>();Entity
////		public static Map<String, List<ItemEnchantmentRunnable>> ITEMENCHMAP = new HashMap<String, List<ItemEnchantmentRunnable>>();
//		public static PlayerDropRunnable parseDrop(String toParse) {
//			String loc = toParse.split(":::")[0];
//			dmg = 0;
//			playerloc = false;
//			if(loc.equalsIgnoreCase("usePlayerLoc"))
//				playerloc = true;
//			else if(loc.equalsIgnoreCase("broadcast")){
//				PlayerDropRunnableWithBroadcast pdrwb = new PlayerDropRunnableWithBroadcast(toParse.split(":::")[1]) {
//					@Override
//					public void run(EntityPlayer p, Vec3d l) {
//						GameHelper.broadcastS(ChatColor.AQUA + this.broadcast);
//					}
//				};
//				return pdrwb;
//			}
////			PLAYERLOCMAP.put(CURR_NAME, playerloc);
//			String[] arrayoAll = toParse.split(":::")[1].split(";");
//			PlayerDropRunnable r = null;
//			if (arrayoAll[0].equalsIgnoreCase("luckychest")){
//				PlayerDropRunnable pdr = new PlayerDropRunnable(false) {
//					@Override
//					public void run(EntityPlayer p, Vec3d l) {
////						l.getWorld().getBlockAt(l).setType(Material.CHEST);
////						Chest c = (Chest)l.getWorld().getBlockAt(l).getState();
////						c.getBlockInventory().setItem(nextChestLocation(), nextRandomAmountItem(Material.IRON_INGOT, 8));
////						c.getBlockInventory().setItem(nextChestLocation(), nextRandomAmountItem(Material.GOLD_INGOT, 4));
////						c.getBlockInventory().setItem(nextChestLocation(), nextRandomAmountItem(Material.DIAMOND, 4));
////						c.getBlockInventory().setItem(nextChestLocation(), nextRandomAmountItem(Material.EMERALD, 4));
////						c.getBlockInventory().setItem(nextChestLocation(), nextRandomAmountItem(Material.SPONGE, 3));
////						c.getBlockInventory().setItem(nextChestLocation(), nextRandomAmountItem(Material.GOLDEN_APPLE, 2));
////						c.getBlockInventory().setItem(nextChestLocation(), nextRandomAmountItem(Material.GOLDEN_APPLE, 2, (short)1));
//						ChestGenerator.generateLuckyChest(new LocationA(l, p.getEntityWorld()));
//					}
//				};
//				return pdr;
//			}
//			if (arrayoAll[0].equalsIgnoreCase("villagerchest")){
//				PlayerDropRunnable pdr = new PlayerDropRunnable(false) {
//					@Override
//					public void run(EntityPlayer p, Vec3d l) {
//						ChestGenerator.generateVillagerChest(new LocationA(l, p.getEntityWorld()));
//					}
//				};
//				return pdr;
//			}
//			if (arrayoAll[0].equalsIgnoreCase("bonuschest")){
//				PlayerDropRunnable pdr = new PlayerDropRunnable(false) {
//					@Override
//					public void run(EntityPlayer p, Vec3d l) {
//						ChestGenerator.generateBonusChest(new LocationA(l, p.getEntityWorld()));
//					}
//				};
//				return pdr;
//			}
//			if (arrayoAll[0].equalsIgnoreCase("dungeonchest")){
//				PlayerDropRunnable pdr = new PlayerDropRunnable(false) {
//					@Override
//					public void run(EntityPlayer p, Vec3d l) {
//						ChestGenerator.generateDungeonChest(new LocationA(l, p.getEntityWorld()));
//					}
//				};
//				return pdr;
//			}
//			if (arrayoAll[0].equalsIgnoreCase("spawn")) {
////				System.out.println("spawn:");
//				//
//				
//				for (EntityType et : EntityType.values()) {
//					String mob = String.valueOf(et);
//					if (arrayoAll[1].equalsIgnoreCase(mob)) {
////						System.out.println("Type: " + mob);
//						ett = et;
//						break;
//					}
//				}
//				if(ett == null)
//					return null;
//					name = "";
//					String finisher = arrayoAll[2];
//					name = finisher;
//					if ((finisher.equals("null")) || finisher.equals("")) {
//						name = "";
//					}
////					System.out.println("Name: " + name);
//					new PlayerDropRunnableEntity(playerloc, name, ett) {
//						@Override
//						public void run(EntityPlayer p, Vec3d l) {
//						if (this.usesPlayerLoc()) {
//							System.out.println("should have been spawned: " + this.getET());
//							Entity e = EntityType.spawnEntity(this.getET(), new Vec3d(p.getPosition()), p.getEntityWorld());
//							if(!(this.getUsename().equalsIgnoreCase("null")))
//								e.setCustomNameTag(this.getUsename());
//							e.setInvisible(false);
//						} else {
//							Entity e = EntityType.spawnEntity(this.getET(), l, p.getEntityWorld());
//							if(!(this.getUsename().equalsIgnoreCase("null")))
//								e.setCustomNameTag(this.getUsename());
//							e.setInvisible(false);
//						}
//						Minecraft.stopIntegratedServer();
//					}};
//			}
//			if(arrayoAll[0].equalsIgnoreCase("lightning")){
//				r = new PlayerDropRunnable( playerloc){
//					public void run(EntityPlayer p, Vec3d l){
//						LocationA currLoc;
//						if(this.usesPlayerLoc())
//							currLoc = new LocationA(new Vec3d(p.getPosition()), p.getEntityWorld());
//						else
//							currLoc = new LocationA(l, p.getEntityWorld());
//						EntityType.spawnEntity(EntityType.LIGHTNING, currLoc, currLoc.getWorld());
//					}
//				};
//			}
//			
//			if(arrayoAll[0].equalsIgnoreCase("primedtnt")){
//				int i = Integer.parseInt(arrayoAll[1]);
//				boolean randoff = false;
//				if(arrayoAll[2].equalsIgnoreCase("rand"))
//					randoff = true;
//				r = new PlayerDropRunnableTNT(playerloc, i, randoff){
//					public void run(EntityPlayer p, Vec3d l){
//						LocationA currLoc;
//						if(this.usesPlayerLoc())
//							currLoc = new LocationA(new Vec3d(p.getPosition()), p.getEntityWorld());
//						else
//							currLoc = new LocationA(l, p.getEntityWorld());
//						EntityTNTPrimed ptnt;
//						if(this.hasRandomOffset()){
//							ptnt = EntityType.TNT_PRIMED.spawnTNT(this.getFuse(), currLoc.createRandomOffset(6), currLoc.getWorld());
////							currLoc.getWorld().spawnEntity(currLoc.createRandomOffset(6), EntityType.PRIMED_TNT);
//						} else {
//							ptnt = EntityType.TNT_PRIMED.spawnTNT(this.getFuse(), currLoc, currLoc.getWorld());
//						}
////						ptnt.setFuseTicks(this.getFuse());
//					}
//				};
//			}
//			if(arrayoAll[0].equalsIgnoreCase("explosion")){
//				float f = Float.parseFloat(arrayoAll[1]);
//				boolean b;
//				if(arrayoAll.length == 2)
//					b = false;
//				else 
//					b = Boolean.parseBoolean(arrayoAll[2]);
//				r = new PlayerDropRunnableExplosion( playerloc, f,b ) {
//					
//					@Override
//					public void run(EntityPlayer p, Vec3d l) {
//						LocationA lA;
//						if(this.usesPlayerLoc())
//							lA = new LocationA(l, p.getEntityWorld());
//						else
//							lA = new LocationA(l, p.getEntityWorld());
//						lA.getWorld().createExplosion(null, lA.x, lA.y, lA.z, getExpP(), this.isSmoking());
//					}
//				};
//			}
//			
//			if(arrayoAll[0].equalsIgnoreCase("firework")){
//				r = new PlayerDropRunnable( playerloc){
//					@Override
//					public void run(EntityPlayer p, Vec3d l) {
//						LocationA locA = null;
//						if (this.usesPlayerLoc())
//							locA = new LocationA(new Vec3d(p.getPosition()), p.getEntityWorld());
//						else
//							locA = new LocationA(l, p.getEntityWorld());
////						LocationA loc;
////						for(int i = 0;i<4;i++){
////							if(i==0)
////								loc = locA.getRelative(-1, 5, -1);
////							else if(i==1)
////								loc = locA.getRelative(1, 5, -1);
////							else if(i==2)
////								loc = locA.getRelative(-1, 5, 1);
////							else if(i==3)
////								loc = locA.getRelative(1, 5, 1);
////							else
////									loc = locA.getRelative(0, 5, 01);
////			            Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
////			            FireworkMeta fwm = fw.getFireworkMeta();
////			           
////			            //Our random generator
////			 
////			            //Get the type
////			            int rt = rand.nextInt(4) + 1;
////			            Type type = Type.BALL;      
////			            if (rt == 1) type = Type.BALL;
////			            if (rt == 2) type = Type.BALL_LARGE;
////			            if (rt == 3) type = Type.BURST;
////			            if (rt == 4) type = Type.CREEPER;
////			            if (rt == 5) type = Type.STAR;
////			           
////			            //Get our random colours  
////			            int r1i = rand.nextInt(17) + 1;
////			            int r2i = rand.nextInt(17) + 1;
////			            Color c1 = getColor(r1i);
////			            Color c2 = getColor(r2i);
////			           
////			            //Create our effect with this
////			            FireworkEffect effect = FireworkEffect.builder().flicker(rand.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(rand.nextBoolean()).build();
////			           
////			            //Then apply the effect to the meta
////			            fwm.addEffect(effect);
////			           
////			            //Generate some random power and set it
////			            int rp = rand.nextInt(2) + 1;
////			            fwm.setPower(rp);
////			           
////			            //Then apply this to our rocket
////			            fw.setFireworkMeta(fwm);
////						}
//						EntityType.FIREWORK.spawnFirework(locA.getWorld(), locA);
//					}
//				};
//				return r;
//			}
//			
//			if (arrayoAll[0].equalsIgnoreCase("dropItem")) {
//				List<ItemModifyRunnable> ench_list = new ArrayList<ItemModifyRunnable>();
////				System.out.println("dropItem:");
//				if(arrayoAll[1].equalsIgnoreCase("SPLASH_EFFECT_POTION")){
////					System.out.println("spl_effect_potion");
//					size = 0;
//					name = "";
//					String[] array = arrayoAll[2].split(",");
//					if(array[0].equalsIgnoreCase("rand")){
//						size = -111;
//					} else {
//						try{
//						size = Integer.parseInt(array[0]);
//						} catch(NumberFormatException nfe){
//							size = 3;
//						}
//					}
//					name = array[1];
//					array2 = new String[2];
////					System.out.println(arrayoAll.length);
//					for(int i = 4; i <= arrayoAll.length; i++){
////						System.out.println(i);
//						array2 = arrayoAll[i-1].split(",");
//						for(PotionFinder ench : PotionFinder.values()){
//							if(array2[0].equalsIgnoreCase(String.valueOf(ench))){
////								System.out.println("Effekt: " + String.valueOf(ench));
////								System.out.println("Level: " + array2[1]);
////								System.out.println("LÃ¤nge: " + array2[2]);
//								PotionContainer enchc = new PotionContainer(new PotionEffect(ench.toFMLEffect().getId(), Integer.parseInt(array2[2]), Integer.parseInt(array2[1])));
//								ier = new ItemPotionRunnable(CURR_NAME, enchc){
//									@Override
//									public void run(ItemStack is) {
//										System.out.println("executed itempotionrunnable for splash potions");
//										if(!(Material.equalsItem(is.getItem(), Material.POTION)))
//											return;
////										PotionMeta im = (PotionMeta)is.getItemMeta();
////										im.addCustomEffect(this.getPotionC().getPEffect(), true);
////										is.setItemMeta(im);
//										List<PotionContainer> list = new ArrayList<PotionContainer>();
//										PotionEffectHelper.writePotionContainerToNBT(list, is);
//									}
//
//								};
//								ench_list.add(ier);
//							}
//						}
//					}
//					r = new PlayerDropRunnableItem( playerloc, name, null, size, ench_list) {
//
//						@Override
//						public void run(EntityPlayer p, Vec3d l) {
//							LocationA loc;
//							if(this.usesPlayerLoc())
//								loc = new LocationA(new Vec3d(p.getPosition()), p.getEntityWorld());
//							else
//								loc = new LocationA(l, p.getEntityWorld());
//							ItemStack is = new ItemStack(Material.POTION.getItem());
//							is.setItemDamage((short) 16428);
////							ItemMeta im = is.getItemMeta();
////							is.setItemMeta(im);
//							is.stackSize = this.getSize();
//							int sizeof = this.getSize();
//							if(sizeof == -111){
//								is.stackSize = this.nextRandom();
//							}
//							if (!(getUseName().equalsIgnoreCase("null"))) {
////								im = is.getItemMeta();
//								is.setStackDisplayName(getUseName());
//							}
//							for(ItemModifyRunnable ier :  this.getEnchList()){
//								ier.run(is);
//							}
////							is = GameHelper.updateToolArmorWeaponLore(is);
////							loc.getWorld().dropItemNaturally(l, is);
//							GameHelper.dropItem(is, loc);
//						}
//					};
//					return r;
//				}
//				if(arrayoAll[1].equalsIgnoreCase("EFFECT_POTION")){
////					System.out.println("effect_potion");
//					size = 0;
//					name = "";
//					String[] array = arrayoAll[2].split(",");
//					if(array[0].equalsIgnoreCase("rand")){
//						size = -111;
//					} else {
//						try{
//						size = Integer.parseInt(array[0]);
//						} catch(NumberFormatException nfe){
//							size = 3;
//						}
//					}
//					name = array[1];
//					array2 = new String[3];
////					System.out.println("Name: " + name + " Size: " + size);
//					System.out.println(arrayoAll.length);
//					for(int i = 4; i <= arrayoAll.length; i++){
////						System.out.println(i);
//						array2 = arrayoAll[i-1].split(",");
//						for(PotionFinder ench : PotionFinder.values()){
//							if(array2[0].equalsIgnoreCase(String.valueOf(ench))){
////								System.out.println("Effekt: " + String.valueOf(ench));
//								int level = Integer.parseInt(array2[1]);
////								System.out.println("Level: " + level);
//								int duration = Integer.parseInt(array2[2]);
////								System.out.println("LÃ¤nge: " + duration);
//								PotionContainer enchc = new PotionContainer(
//										new PotionEffect(ench.toFMLEffect().getId(), 
//												duration, level));
//								ier = new ItemPotionRunnable(CURR_NAME, enchc){
//									@Override
//									public void run(ItemStack is) {
//										System.out.println("executed itempotionrunnable for normal potions");
//										if(!(Material.equalsItem(is.getItem(), Material.POTION)))
//											return;
////										PotionMeta im = (PotionMeta)is.getItemMeta();
////										im.addCustomEffect(this.getPotionC().getPEffect(), true);
////										is.setItemMeta(im);
//										List<PotionContainer> list = new ArrayList<PotionContainer>();
//										PotionEffectHelper.writePotionContainerToNBT(list, is);
//									}
//
//								};
//								ench_list.add(ier);
//							}
//						}
//					}
//					r = new PlayerDropRunnableItem(playerloc, name, null, size, ench_list) {
//
//						@Override
//						public void run(EntityPlayer p, Vec3d l) {
//							LocationA loc;
//							if(this.usesPlayerLoc())
//								loc = new LocationA(new Vec3d(p.getPosition()), p.getEntityWorld());
//							else
//								loc = new LocationA(l, p.getEntityWorld());
//							ItemStack is = new ItemStack(Material.POTION.getItem());
//							is.setItemDamage((short) 8227);
////							ItemMeta im = is.getItemMeta();
////							is.setItemMeta(im);
//							is.stackSize = this.getSize();
//							int sizeof = this.getSize();
//							if(sizeof == -111){
//								is.stackSize = this.nextRandom();
//							}
//							if (!(getUseName().equalsIgnoreCase("null"))) {
////								im = is.getItemMeta();
//								is.setStackDisplayName(getUseName());
//							}
//							for(ItemModifyRunnable ier :  this.getEnchList()){
//								ier.run(is);
//							}
////							is = GameHelper.updateToolArmorWeaponLore(is);
////							loc.getWorld().dropItemNaturally(l, is);
//							GameHelper.dropItem(is, loc);
//						}
//					};
//					return r;
//				}
////				if(arrayoAll[1].equalsIgnoreCase("ender_backpack")){
////					size = 0;
////					name = "";
////					String[] array = arrayoAll[2].split(",");
////					if(array[0].equalsIgnoreCase("rand")){
////						size = -111;
////					} else {
////						try{
////						size = Integer.parseInt(array[0]);
////						} catch(NumberFormatException nfe){
////							size = 3;
////						}
////					}
////					name = array[1];
////					array2 = new String[2];
//////					System.out.println(arrayoAll.length);
////					ier = null;
////					for(int i = 4; i <= arrayoAll.length; i++){
//////						System.out.println(i);
////						array2 = arrayoAll[i-1].split(",");
////						for(EnchantmentFinder ench : EnchantmentFinder.values()){
////							if(array2[0].equalsIgnoreCase(String.valueOf(ench))){
//////								System.out.println("Verzauberung: " + String.valueOf(ench));
//////								System.out.println("Level: " + array2[1]);
////								EnchantmentContainer enchc = new EnchantmentContainer(ench.toFMLEnchantment(), Integer.parseInt(array2[1]));
////								ier = new ItemEnchantmentRunnable(enchc){
////									@Override
////									public void run(ItemStack is) {
//////										ItemMeta im = is.getItemMeta();
////										is.addEnchantment(this.getEnchC().getEnchantment(),
////												this.getEnchC().getLevel());
//////										is.setItemMeta(im);
////									}
////
////								};
////								ench_list.add(ier);
////							}
////						}
////					}
////					r = new PlayerDropRunnableItem(playerloc, name, size, ench_list) {
////
////						@Override
////						public void run(Player p, Location l) {
////							Location loc;
////							if(this.usesPlayerLoc())
////								loc = p.getLocation();
////							else
////								loc = l;
////							ItemStack is = Item.BPE.clone();
////							ItemMeta im = is.getItemMeta();
////							is.setItemMeta(im);
////							is.setAmount(this.getSize());
////							int sizeof = this.getSize();
////							if(sizeof == -111){
////								is.setAmount(this.nextRandom());
////							}
////							for(ItemModifyRunnable ier :  this.getEnchList()){
////								ier.run(is);
////							}
////							is = GameHelper.updateToolArmorWeaponLore(is);
////							loc.getWorld().dropItemNaturally(l, is);
////						}
////					};
////					return r;
////				}
////				if(arrayoAll[1].equalsIgnoreCase("backpack")){
////					size = 0;
////					name = "";
////					String[] array = arrayoAll[2].split(",");
////					if(array[0].equalsIgnoreCase("rand")){
////						size = -111;
////					} else {
////						try{
////						size = Integer.parseInt(array[0]);
////						} catch(NumberFormatException nfe){
////							size = 3;
////						}
////					}
////					name = array[1];
////					array2 = new String[2];
//////					System.out.println(arrayoAll.length);
////					ier = null;
////					for(int i = 4; i <= arrayoAll.length; i++){
//////						System.out.println(i);
////						array2 = arrayoAll[i-1].split(",");
////						for(EnchantmentFinder ench : EnchantmentFinder.values()){
////							if(array2[0].equalsIgnoreCase(String.valueOf(ench))){
//////								System.out.println("Verzauberung: " + String.valueOf(ench));
//////								System.out.println("Level: " + array2[1]);
////								EnchantmentContainer enchc = new EnchantmentContainer(ench.toBukkitEnchantment(), Integer.parseInt(array2[1]));
////								ier = new ItemEnchantmentRunnable(CURR_NAME, enchc){
////									@Override
////									public void run(ItemStack is) {
////										ItemMeta im = is.getItemMeta();
////										im.addEnchant(this.getEnchC().getEnchantment(),
////												this.getEnchC().getLevel(), true);
////										is.setItemMeta(im);
////									}
////
////								};
////								ench_list.add(ier);
////							}
////						}
////					}
////					r = new PlayerDropRunnableItem(CURR_NAME, playerloc, name, size, ench_list) {
////
////						@Override
////						public void run(Player p, Location l) {
////							Location loc;
////							if(this.usesPlayerLoc())
////								loc = p.getLocation();
////							else
////								loc = l;
////							ItemStack is = Item.BACKPACK.clone();
////							ItemMeta im = is.getItemMeta();
////							is.setItemMeta(im);
////							is.setAmount(this.getSize());
////							int sizeof = this.getSize();
////							if(sizeof == -111){
////								is.setAmount(this.nextRandom());
////							}
////							for(ItemModifyRunnable ier :  this.getEnchList()){
////								ier.run(is);
////							}
////							is = GameHelper.updateToolArmorWeaponLore(is);
////							loc.getWorld().dropItemNaturally(l, is);
////						}
////					};
////					return r;
////				}
////				if(arrayoAll[1].equalsIgnoreCase("coin")){
////					size = 0;
////					name = "";
////					String[] array = arrayoAll[2].split(",");
////					if(array[0].equalsIgnoreCase("rand")){
////						size = -111;
////					} else {
////						try{
////						size = Integer.parseInt(array[0]);
////						} catch(NumberFormatException nfe){
////							size = 3;
////						}
////					}
////					name = array[1];
////					array2 = new String[2];
//////					System.out.println(arrayoAll.length);
////					ier = null;
////					for(int i = 4; i <= arrayoAll.length; i++){
//////						System.out.println(i);
////						array2 = arrayoAll[i-1].split(",");
////						for(EnchantmentFinder ench : EnchantmentFinder.values()){
////							if(array2[0].equalsIgnoreCase(String.valueOf(ench))){
//////								System.out.println("Verzauberung: " + String.valueOf(ench));
//////								System.out.println("Level: " + array2[1]);
////								EnchantmentContainer enchc = new EnchantmentContainer(ench.toBukkitEnchantment(), Integer.parseInt(array2[1]));
////								ier = new ItemEnchantmentRunnable(CURR_NAME, enchc){
////									@Override
////									public void run(ItemStack is) {
////										ItemMeta im = is.getItemMeta();
////										im.addEnchant(this.getEnchC().getEnchantment(),
////												this.getEnchC().getLevel(), true);
////										is.setItemMeta(im);
////									}
////
////								};
////								ench_list.add(ier);
////							}
////						}
////					}
////					r = new PlayerDropRunnableItem(CURR_NAME, playerloc, name, size, ench_list) {
////
////						@Override
////						public void run(Player p, Location l) {
////							Location loc;
////							if(this.usesPlayerLoc())
////								loc = p.getLocation();
////							else
////								loc = l;
////							ItemStack is = new ItemStack(Material.GOLD_NUGGET);
////							ItemMeta im = is.getItemMeta();
////							im.setDisplayName(ChatColor.GOLD + "MÃ¼nze");
////							is.setItemMeta(im);
////							is.setAmount(this.getSize());
////							int sizeof = this.getSize();
////							if(sizeof == -111){
////								is.setAmount(this.nextRandom());
////							}
////							for(ItemModifyRunnable ier :  this.getEnchList()){
////								ier.run(is);
////							}
////							is = GameHelper.updateToolArmorWeaponLore(is);
////							loc.getWorld().dropItemNaturally(l, is);
////						}
////					};
////					return r;
////				}
////				if(arrayoAll[1].equalsIgnoreCase("lapis")){
////					size = 0;
////					name = "";
////					String[] array = arrayoAll[2].split(",");
////					if(array[0].equalsIgnoreCase("rand")){
////						size = -111;
////					} else {
////						try{
////						size = Integer.parseInt(array[0]);
////						} catch(NumberFormatException nfe){
////							size = 3;
////						}
////					}
////					name = array[1];
////					array2 = new String[2];
//////					System.out.println(arrayoAll.length);
////					ier = null;
////					for(int i = 4; i <= arrayoAll.length; i++){
//////						System.out.println(i);
////						array2 = arrayoAll[i-1].split(",");
////						for(EnchantmentFinder ench : EnchantmentFinder.values()){
////							if(array2[0].equalsIgnoreCase(String.valueOf(ench))){
//////								System.out.println("Verzauberung: " + String.valueOf(ench));
//////								System.out.println("Level: " + array2[1]);
////								EnchantmentContainer enchc = new EnchantmentContainer(ench.toBukkitEnchantment(), Integer.parseInt(array2[1]));
////								ier = new ItemEnchantmentRunnable(CURR_NAME, enchc){
////									@Override
////									public void run(ItemStack is) {
////										ItemMeta im = is.getItemMeta();
////										im.addEnchant(this.getEnchC().getEnchantment(),
////												this.getEnchC().getLevel(), true);
////										is.setItemMeta(im);
////									}
////
////								};
////								ench_list.add(ier);
////							}
////						}
////					}
////					return new PlayerLapisDropRunnable(CURR_NAME, playerloc, name, size, ench_list) {
////					};
////				}
//				for (Material m : Material.values()) {
//					String mat = String.valueOf(m);
//					if (arrayoAll[1].equalsIgnoreCase(mat)){
////						System.out.println("Material: " + mat);
//						name = "";
//						size = 0;
//						String finisher = arrayoAll[2];
//						String[] array = finisher.split(",");
//						if(array[0].equalsIgnoreCase("rand")){
//							size = -111;
//						} else {
//							try{
//							size = Integer.parseInt(array[0]);
//							} catch(NumberFormatException nfe){
//								size = 3;
//							}
//						}
//						name = array[1];
////						System.out.println("array-lengh" + array.length);
//						if(array.length == 3)
//							dmg = Short.parseShort(array[2]);
//						else
//							dmg = 0;
////						System.out.println("Name: " + name + " Size: " + size + "Dmg: " + dmg);
//						array2 = new String[2];
////						System.out.println(arrayoAll.length);
//						ier = null;
//						for(int i = 4; i <= arrayoAll.length; i++){
////							System.out.println(i);
//							array2 = arrayoAll[i-1].split(",");
//							for(EnchantmentFinder ench : EnchantmentFinder.values()){
//								if(array2[0].equalsIgnoreCase(String.valueOf(ench))){
////									System.out.println("Verzauberung: " + String.valueOf(ench));
////									System.out.println("Level: " + array2[1]);
//									EnchantmentContainer enchc = new EnchantmentContainer(ench.toFMLEnchantment(), Integer.parseInt(array2[1]));
//									ier = new ItemEnchantmentRunnable(enchc){
//										@Override
//										public void run(ItemStack is) {
////											ItemMeta im = is.getItemMeta();
//											is.addEnchantment(this.getEnchC().getEnchantment(),
//													this.getEnchC().getLevel());
////											is.setItemMeta(im);
//										}
//
//									};
//									ench_list.add(ier);
//								}
//							}
//						}
////								for(String s : array){
////									array2 = s.split(",");
////									System.out.println("ENCH TEST");
////										for(Enchantment ench : Enchantment.values()){
//////											System.out.println(ench + " ist es nicht,");
////											if(array2[0].equalsIgnoreCase(String.valueOf(ench))){
//////												im.addEnchant(ench, Integer.parseInt(array2[1]), true);
////												System.out.println("ENCHANTMENT: " + ench + " LEVEL: " + Integer.parseInt(array2[1]) );
////											}
////										}
////								}ChatColor
//						r = new PlayerDropRunnableItemDamageAttrib(playerloc, name, m, size, ench_list, dmg) {
//							public void run(EntityPlayer p, Vec3d l){
//								LocationA lA;
//								if(this.usesPlayerLoc()){
//									lA = new LocationA(new Vec3d(p.getPosition()), p.getEntityWorld());
//								} else {
//									lA = new LocationA(l, p.getEntityWorld());
//								}
//								int sizeof = this.getSize();
//								ItemStack is = new ItemStack(this.getMaterial().getItem(), this.getSize(), this.getDmg());
//								if(sizeof == -111){
//									is.stackSize = this.nextRandom();
//								}
//								for(ItemModifyRunnable ier : this.getEnchList()){
//									ier.run(is);
//								}
////								ItemMeta im = is.getItemMeta();
//								if(this.getUseName().equalsIgnoreCase("null")){
//									
//								} else {
//									is.setStackDisplayName(this.getUseName());
//								}
////								is.setItemMeta(im);
////								is = GameHelper.updateToolArmorWeaponLore(is);
////								l.getWorld().dropItemNaturally(lA, is);
//								GameHelper.dropItem(is, lA);
//							}
//						};
//						break;
//					}
//				}
//			}
//			if(arrayoAll[0].equalsIgnoreCase("lava_trap")){
//				r = new PlayerDropRunnable(true){
//					public void run(EntityPlayer p, Vec3d l){
//						World w = p.getEntityWorld();
//						LocationA la = new LocationA(new Vec3d(p.getPosition()), p.getEntityWorld());
//						w.setBlockState(la.getRelative(1, 0, 0).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(1, 0, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(0, 0, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(-1, 0, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(-1, 0, 0).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(-1, 0, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(0, 0, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(1, 0, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(1, 1, 0).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(1, 1, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(0, 1, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(-1, 1, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(-1, 1, 0).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(-1, 1, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(0, 1, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(1, 1, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(1, 2, 0).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(1, 2, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(0, 2, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(-1, 2, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(-1, 2, 0).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(-1, 2, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(0, 2, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(1, 2, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//						w.setBlockState(la.getRelative(0, 2, 0).toBlockPos(), Blocks.lava.getDefaultState());
//						p.addChatMessage(new ChatComponentText(ChatColor.YELLOW + "Schau nach oben!"));
//					}
//				};
//				return r;
//			}
////			if(arrayoAll[0].equalsIgnoreCase("new_luckys")){
////				r = new PlayerDropRunnable(false){
////					public void run(EntityPlayer p, Vec3d l){
////						LocationA lA = new LocationA(l, p.getEntityWorld());
////						World w = lA.getWorld();
////						w.setBlockState(lA.getRelative(-1, 0, -1).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
////						w.setBlockState(lA.getRelative(1, 0, -1).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
////						w.setBlockState(lA.getRelative(-1, 0, 1).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
////						w.setBlockState(lA.getRelative(1, 0, 1).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
////						w.setBlockState(lA.getRelative(-2, 2, -2).toBlockPos(), Material.GOLD_BLOCK.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(2, 2, -2).toBlockPos(), Material.GOLD_BLOCK.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-2, 2, 2).toBlockPos(), Material.GOLD_BLOCK.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(2, 2, 2).toBlockPos(), Material.GOLD_BLOCK.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-2, 1, -2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(2, 1, -2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-2, 1, 2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(2, 1, 2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-2, 0, -2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(2, 0, -2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-2, 0, 2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(2, 0, 2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
//////						
////						w.setBlockState(lA.getRelative(0, -1, 0).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						
////						w.setBlockState(lA.getRelative(1, -1, 0).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(0, -1, 1).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-1, -1, 0).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(0, -1, -1).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(1, -1, -1).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-1, -1, 1).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(1, -1, 1).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-1, -1, -1).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						
////						w.setBlockState(lA.getRelative(2, 0, 0).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(2, 0, 1).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(2, 0, -1).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(0, 0, 2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(1, 0, 2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-1, 0, 2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-2, 0, 1).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-2, 0, -1).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-2, 0, 0).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(0, 0, -2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-1, 0, -2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(1, 0, -2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						
////						w.setBlockState(lA.getRelative(2, 1, 0).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(2, 1, 1).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(2, 1, -1).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(0, 1, 2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(1, 1, 2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-1, 1, 2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-2, 1, 1).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-2, 1, -1).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-2, 1, 0).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(0, 1, -2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(-1, 1, -2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////						w.setBlockState(lA.getRelative(1, 1, -2).toBlockPos(), Material.SANDSTONE.getBlockIfExists().getDefaultState());
////					}
////				};
////				return r;
////			}
//			if(arrayoAll[0].equalsIgnoreCase("new_luckys_big")){
//				r = new PlayerDropRunnable(false){
//					public void run(EntityPlayer p, Vec3d l){
//						LocationA lA = new LocationA(l, p.getEntityWorld());
//						World w = lA.getWorld();
//						w.setBlockState(lA.getRelative(-2, 0, -2).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
//						w.setBlockState(lA.getRelative(-2, 0, 0).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
//						w.setBlockState(lA.getRelative(2, 0, -2).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
//						w.setBlockState(lA.getRelative(-2, 0, 2).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
//						w.setBlockState(lA.getRelative(2, 0, 2).toBlockPos(),GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
//						w.setBlockState(lA.getRelative(0, 0, -2).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
//						w.setBlockState(lA.getRelative(-2, 0, 0).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
//						w.setBlockState(lA.getRelative(2, 0, 0).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
//						w.setBlockState(lA.getRelative(0, 0, 2).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
//						w.setBlockState(lA.getRelative(-3, 3, -3).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
//						w.setBlockState(lA.getRelative(3, 3, -3).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
//						w.setBlockState(lA.getRelative(-3, 3, 3).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
//						w.setBlockState(lA.getRelative(3, 3, 3).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
//						w.setBlockState(lA.getRelative(0, 4, 0).toBlockPos(), GameHelper.LUCKY_MODULE.DEFAULT_LUCKY_BLOCK.getDefaultState());
//					}
//				};
//				return r;
//			}
////			if(arrayoAll[0].equalsIgnoreCase("anvil_trap")){
////				r = new PlayerDropRunnable( true){
////					public void run(EntityPlayer p, Vec3d l){
////						World w = p.getEntityWorld();
////						LocationA la = new LocationA(new Vec3d(p.getPosition()), w);
//////						w.setBlockState(la.getRelative(1, 0, 0).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(1, 0, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(0, 0, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(-1, 0, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(-1, 0, 0).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(-1, 0, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(0, 0, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(1, 0, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(1, 1, 0).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(1, 1, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(0, 1, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(-1, 1, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(-1, 1, 0).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(-1, 1, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(0, 1, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(1, 1, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(1, 2, 0).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(1, 2, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(0, 2, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(-1, 2, 1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(-1, 2, 0).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(-1, 2, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(0, 2, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(1, 2, -1).toBlockPos(), Blocks.iron_bars.getDefaultState());
//////						w.setBlockState(la.getRelative(0, 25, 0).toBlockPos(), Blocks.anvil.getDefaultState());
//////						w.setBlockState(la.getRelative(0, 26, 0).toBlockPos(), Blocks.anvil.getDefaultState());
////						WFShematicsReader.SHEMATICS_MAP.get(key)
////						p.addChatMessage(new ChatComponentText(ChatColor.YELLOW + "Schau nach oben!"));
////					}
////				};
////				return r;
////			}
////			if(arrayoAll[0].equalsIgnoreCase("brunnen")){
////				r = new PlayerDropRunnable(false) {
////					@Override
////					public void run(EntityPlayer p, Vec3d l) {
////						World w = p.getEntityWorld();
////						LocationA la = new LocationA(l, p.getEntityWorld());
////						w.setBlockState(la.getRelative(1, 0, 0).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(0, 0, 1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(-1, 0, 0).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(0, 0, -1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(0, 0, 0).toBlockPos(), Blocks.water.getDefaultState());
////						w.setBlockState(la.getRelative(0, -2, 0).toBlockPos(), Blocks.command_block.getDefaultState());
////						w.setBlockState(la.getRelative(0, -1, 0).toBlockPos(), Blocks.wooden_pressure_plate.getDefaultState());
////						w.setBlockState(la.getRelative(1, 0, 0).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(1, 0, 1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(0, 0, 1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(-1, 0, 1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(-1, 0, 0).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(-1, 0, -1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(0, 0, -1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(1, 0, -1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(1, 1, 1).toBlockPos(), Blocks.oak_fence.getDefaultState());
////						w.setBlockState(la.getRelative(-1, 1, 1).toBlockPos(), Blocks.oak_fence.getDefaultState());
////						w.setBlockState(la.getRelative(1, 1, -1).toBlockPos(), Blocks.oak_fence.getDefaultState());
////						w.setBlockState(la.getRelative(-1, 1, -1).toBlockPos(), Blocks.oak_fence.getDefaultState());
////						w.setBlockState(la.getRelative(1, 2, 1).toBlockPos(), Blocks.oak_fence.getDefaultState());
////						w.setBlockState(la.getRelative(-1, 2, 1).toBlockPos(), Blocks.oak_fence.getDefaultState());
////						w.setBlockState(la.getRelative(1, 2, -1).toBlockPos(), Blocks.oak_fence.getDefaultState());
////						w.setBlockState(la.getRelative(-1, 2, -1).toBlockPos(), Blocks.oak_fence.getDefaultState());
////						w.setBlockState(la.getRelative(0, 0, 0).toBlockPos(), Blocks.water.getDefaultState());
////						w.setBlockState(la.getRelative(0, 3, 0).toBlockPos(), Blocks.glass.getDefaultState());
////						w.setBlockState(la.getRelative(1, -3, 0).toBlockPos(), Blocks.iron_block.getDefaultState());
////						w.setBlockState(la.getRelative(1, -3, 1).toBlockPos(), Blocks.iron_block.getDefaultState());
////						w.setBlockState(la.getRelative(0, -3, 1).toBlockPos(), Blocks.iron_block.getDefaultState());
////						w.setBlockState(la.getRelative(-1, -3, 1).toBlockPos(), Blocks.iron_block.getDefaultState());
////						w.setBlockState(la.getRelative(-1, -3, 0).toBlockPos(), Blocks.iron_block.getDefaultState());
////						w.setBlockState(la.getRelative(-1, -3, -1).toBlockPos(), Blocks.iron_block.getDefaultState());
////						w.setBlockState(la.getRelative(0, -3, -1).toBlockPos(), Blocks.iron_block.getDefaultState());
////						w.setBlockState(la.getRelative(1, -3, -1).toBlockPos(), Blocks.iron_block.getDefaultState());
////						w.setBlockState(la.getRelative(0, -3, 0).toBlockPos(), Blocks.iron_block.getDefaultState());
////						w.setBlockState(la.getRelative(1, 3, 0).toBlockPos(), Blocks.stone_stairs.getStateFromMeta(1));
//////						w.setBlockState(la.getRelative(1, 3, 0)).setData((byte) 1); 
////						w.setBlockState(la.getRelative(1, 3, 1).toBlockPos(), Blocks.stone_stairs.getDefaultState());
////						w.setBlockState(la.getRelative(0, 3, 1).toBlockPos(), Blocks.stone_stairs.getDefaultState());
////						w.setBlockState(la.getRelative(-1, 3, 1).toBlockPos(), Blocks.stone_stairs.getDefaultState());
////						w.setBlockState(la.getRelative(-1, 3, 0).toBlockPos(), Blocks.stone_stairs.getStateFromMeta(0));
//////						w.setBlockState(la.getRelative(-1, 3, 0)).setData((byte) 0);
////						w.setBlockState(la.getRelative(-1, 3, -1).toBlockPos(), Blocks.stone_stairs.getStateFromMeta(2));
////						w.setBlockState(la.getRelative(0, 3, -1).toBlockPos(), Blocks.stone_stairs.getStateFromMeta(2));
////						w.setBlockState(la.getRelative(1, 3, -1).toBlockPos(), Blocks.stone_stairs.getStateFromMeta(2));
//////						w.setBlockState(la.getRelative(-1, 3, -1)).setData((byte) 2);
//////						w.setBlockState(la.getRelative(0, 3, -1)).setData((byte) 2);
//////						w.setBlockState(la.getRelative(1, 3, -1)).setData((byte) 2);
////						w.setBlockState(la.getRelative(1, -2, 0).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(1, -2, 1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(0, -2, 1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(-1, -2, 1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(-1, -2, 0).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(-1, -2, -1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(0, -2, -1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(1, -2, -1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(1, -1, 0).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(1, -1, 1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(0, -1, 1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(-1, -1, 1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(-1, -1, 0).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(-1, -1, -1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(0, -1, -1).toBlockPos(), Blocks.cobblestone.getDefaultState());
////						w.setBlockState(la.getRelative(1, -1, -1).toBlockPos(), Blocks.cobblestone.getDefaultState());
//////						CommandBlock cb = (CommandBlock) w.getBlockAt(la.getRelative(0, -2, 0)).getState();
//////						cb.setCommand("brunnendrop" + " " + la.getRelative(0, -2, 0).getWorld().getName() + " " + la.getRelative(0, -2, 0).getBlockX() + " " + la.getRelative(0, -2, 0).getBlockY() + " " + la.getRelative(0, -2, 0).getBlockZ());
//////						cb.update(true);
////						GameHelper.broadcastS(ChatColor.AQUA + "Ein Wunschbrunnen... Du solltest eine Münze einwerfen.");
////						EntityItem ei= new EntityItem(p.getEntityWorld(), p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getZ(), new ItemStack(Material.GOLD_COIN.getItem()));
////						ei.setAlwaysRenderNameTag(true);
////						EntityType.dropItem(ei, new LocationA(new Vec3d(p.getPosition()), p.getEntityWorld()));
////					}
////					
////				};
////			}
//			return r;
//		}
//		
//		public static Map<String, Drop> DROP_SORTER_LUCKY = new HashMap<String, Drop>();
//		public static Map<String, Drop> DROP_SORTER_NORMAL = new HashMap<String, Drop>();
//		public static Map<String, Drop> DROP_SORTER_UNLUCKY = new HashMap<String, Drop>();
//		public static Map<String, Drop> DROP_SORTER_BRUNNEN = new HashMap<String, Drop>();
//
//		public static Drop parseDrop(File toParse) throws IOException {
////			Bukkit.broadcastMessage(LuckyBlocks.PREFIX + "registriere Drops: " + toParse.getName().replaceAll(".wflbd", ""));
//			ArrayList<String> stringliste = new ArrayList<String>();
//			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(toParse), StandardCharsets.UTF_8));
//			String line = "";
//			while((line = br.readLine()) != null){
//				if(!(line.contains("version"))){
//					stringliste.add(line);
//				}
//			}
//			br.close();
//			CURR_NAME = toParse.getName().replaceAll(".wflbd", "");
//			Drop d = parseDrop(stringliste);
//			return d;
////			return parseDrop("dropItem(DIAMOND_SWORD(5,DAS ULTIMATIVE SCHWERT))");
////			return parseDrop("spawn(ENDER_DRAGON(THE ENDERDRAGON))", p);
//		}
//	}
}
