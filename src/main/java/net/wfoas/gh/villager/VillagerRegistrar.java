package net.wfoas.gh.villager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.villager.entity.GHVillagerProfession;
import net.wfoas.gh.villager.entity.trades.GHVillagerTrade;

/**
 * Villager IDs are only present, after the server started.
 * 
 * @author WFS
 *
 */
public class VillagerRegistrar {
	protected static List<GHVillagerProfession> unsortedProfessionList = new ArrayList<GHVillagerProfession>();
	protected static Map<Integer, GHVillagerProfession> sortedprofessionList = new HashMap<Integer, GHVillagerProfession>();
	public static GHVillagerProfession DEALER, JEWELER;
	@SideOnly(value = Side.CLIENT)
	protected static Map<Integer, GHVillagerProfession> clientProfessionList;
	static {
		if (GameHelper.EVENT_SIDE == Side.CLIENT) {
			clientProfessionList = new HashMap<Integer, GHVillagerProfession>();
		}
	}
	public static GHVillagerProfession defaultProfession;

	@SideOnly(value = Side.CLIENT)
	public static void registerProfessionListClientSide(Map<Integer, String> map) {
		clientProfessionList.clear();
		for (Map.Entry<Integer, String> ntry : map.entrySet()) {
			clientProfessionList.put(ntry.getKey(), getProfessionByAbstractName(ntry.getValue()));
			GameHelper.getLogger().log(Level.INFO, "Injected new Villager clientside from server with server-id: "
					+ ntry.getKey() + " and server-name: " + ntry.getValue());
		}
	}

	public static void sortIntoListOnServerStart() {
		FileVillagerProfessionIdMap.sortListAndAssignIds();
		GameHelper.getLogger().log(Level.DEBUG, "ServerStart");
		GameHelper.getLogger().log(Level.DEBUG, "---|Sorted|---");
		for (Map.Entry<Integer, GHVillagerProfession> ntry : sortedprofessionList.entrySet()) {
			GameHelper.getLogger().log(Level.DEBUG,
					"ID: " + ntry.getKey() + ", Villager: " + ntry.getValue().getName());
		}
		GameHelper.getLogger().log(Level.DEBUG, "---|Unsorted|---");
		for (GHVillagerProfession ntry : unsortedProfessionList) {
			GameHelper.getLogger().log(Level.DEBUG, "Villager: " + ntry.getName());
		}
	}

	public static Map<Integer, String> getStringMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (Map.Entry<Integer, GHVillagerProfession> entry : sortedprofessionList.entrySet()) {
			map.put(entry.getKey(), entry.getValue().getName());
		}
		return map;
	}

	private static void instantiateProfessions() {
		DEALER = new GHVillagerProfession("gamehelper:dealer", "gamehelper.villager.profession.dealer",
				new ResourceLocation("gamehelper", "textures/entity/villager/dealer.png"));
		GHVillagerTrade.createMerchantTradeList(DEALER,
				new GHVillagerTrade[] { new GHVillagerTrade(new ItemStack(Blocks.glass, 3),
						new ItemStack(Blocks.tallgrass), new ItemStack(Blocks.sand)) });
		JEWELER = new GHVillagerProfession("gamehelper:jeweler", "gamehelper.villager.profession.jeweler",
				new ResourceLocation("gamehelper", "textures/entity/villager/jeweler.png"));
		GHVillagerTrade
				.createMerchantTradeList(JEWELER,
						new GHVillagerTrade[] { new GHVillagerTrade(new ItemStack(Items.gold_nugget, 18),
								new ItemStack(Items.iron_pickaxe),
								new ItemStack(GameHelperCoreModule.AMETHYST_ITEM, 7)) });
		defaultProfession = new GHVillagerProfession("gamehelper:default_not_use",
				"gamehelper.villager.profession.default_not_use",
				new ResourceLocation("gamehelper", "textures/entity/villager/default_not_use.png"));
		GHVillagerTrade.createMerchantTradeList(defaultProfession, new GHVillagerTrade[] {
				new GHVillagerTrade(new ItemStack(Items.gold_ingot, 8), new ItemStack(Items.emerald)) });
		registerGHProfession(DEALER);
		registerGHProfession(JEWELER);
	}

	public static void loadBuiltInVillagerProfessions() {
		instantiateProfessions();
		GameHelper.getLogger().log(Level.DEBUG, "---|Sorted|---");
		for (Map.Entry<Integer, GHVillagerProfession> ntry : sortedprofessionList.entrySet()) {
			GameHelper.getLogger().log(Level.DEBUG,
					"ID: " + ntry.getKey() + ", Villager: " + ntry.getValue().getName());
		}
		GameHelper.getLogger().log(Level.DEBUG, "---|Unsorted|---");
		for (GHVillagerProfession ntry : unsortedProfessionList) {
			GameHelper.getLogger().log(Level.DEBUG, "Villager: " + ntry.getName());
		}
	}

	public static void registerGHProfession(GHVillagerProfession profession) {
		// int id = nextVillagerProfessionId();
		// sortedprofessionList.put(Integer.valueOf(id), profession);
		// return id;
		unsortedProfessionList.add(profession);
	}

	public static int getIdByVillagerProfession(GHVillagerProfession key) {
		if (GameHelper.EVENT_SIDE == Side.SERVER) {
			if (sortedprofessionList.containsValue(key)) {
				for (Map.Entry<Integer, GHVillagerProfession> entry : sortedprofessionList.entrySet()) {
					if (key.equals(entry.getValue()))
						return entry.getKey();
				}
			}
		} else {
			if (clientProfessionList.containsValue(key)) {
				for (Map.Entry<Integer, GHVillagerProfession> entry : clientProfessionList.entrySet()) {
					if (key.equals(entry.getValue()))
						return entry.getKey();
				}
			}
		}
		return -1;
	}

	public static GHVillagerProfession getProfessionById(int id) {
		if (GameHelper.EVENT_SIDE == Side.SERVER) {
			if (sortedprofessionList.containsKey(Integer.valueOf(id))) {
				for (Map.Entry<Integer, GHVillagerProfession> ntry : sortedprofessionList.entrySet()) {
					if (id == ntry.getKey() && ntry.getValue() != null)
						return ntry.getValue();
				}
			}
		} else {
			if (clientProfessionList.containsKey(Integer.valueOf(id))) {
				for (Map.Entry<Integer, GHVillagerProfession> ntry : clientProfessionList.entrySet()) {
					if (id == ntry.getKey() && ntry.getValue() != null)
						return ntry.getValue();
				}
			}
		}
		return defaultProfession;
	}

	public static GHVillagerProfession getProfessionByAbstractName(String abstractname) {
		for (GHVillagerProfession entry : unsortedProfessionList) {
			if (entry.getName().equalsIgnoreCase(abstractname))
				return entry;
		}
		return defaultProfession;
	}
}