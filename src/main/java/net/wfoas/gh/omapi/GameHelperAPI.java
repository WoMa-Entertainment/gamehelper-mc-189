package net.wfoas.gh.omapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IChatComponent;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperUtils;
import net.wfoas.gh.blocks.IGHModBlock;
import net.wfoas.gh.commands.CommandToggleNotify;
import net.wfoas.gh.commands.GHCommand;
import net.wfoas.gh.notifysettings.NotifyTable;
import net.wfoas.gh.omapi.module.GameHelperModuleAbstract;
import net.wfoas.gh.protected_blocks.IProtectedBlock;
import net.wfoas.gh.protected_blocks.ProtectedBlocksRegistry;
import net.wfoas.gh.scheduler.GHScheduler;

public class GameHelperAPI {
	GameHelper gh;
	GameHelperEnchantAPI enchant_api;
	GHItemAPI item_api;
	MinersInventoryAPI minersinv_api;

	List<GameHelperRunnableRegisterTab> registerReqs;
	List<GHCommand> gh_commands;
	List<GameHelperModuleAbstract> modules;

	public GameHelperAPI(GameHelper gh) {
		this.gh = gh;
		this.enchant_api = new GameHelperEnchantAPI();
		this.item_api = new GHItemAPI();
		this.minersinv_api = new MinersInventoryAPI();
		registerReqs = new ArrayList<GameHelperRunnableRegisterTab>();
		gh_commands = new ArrayList<GHCommand>();
		modules = new ArrayList<GameHelperModuleAbstract>();
	}

	public static GameHelperAPI ghAPI() {
		return GHIntAPIHelper.api;
	}

	/***
	 * Init Event
	 * 
	 * @return
	 */
	public GameHelperEnchantAPI ghEnchantAPI() {
		return enchant_api;
	}

	public GHItemAPI ghItemAPI() {
		return item_api;
	}

	public MinersInventoryAPI ghMinersInventoryAPI() {
		return minersinv_api;
	}

	public static GameHelperAPI getGameHelperAPI() {
		return ghAPI();
	}

	public static GameHelperUtils ghUtils() {
		return GameHelper.getUtils();
	}

	public GHScheduler ghScheduler() {
		return gh.ghscheduler;
	}

	public void ghSendNotification(EntityPlayerMP player, IChatComponent g) {
		if (NotifyTable.playerWantsNotification(player)) {
			player.addChatComponentMessage(g);
		}
	}

	/***
	 * Init Event
	 */
	public void ghRegisterCommand(GHCommand ghc) {
		gh_commands.add(ghc);
	}

	/***
	 * Init Event
	 * 
	 * @param run
	 */
	public void injectDefaultTabRegisterRequest(GameHelperRunnableRegisterTab run) {
		registerReqs.add(run);
	}

	public void injectModule(GameHelperModuleAbstract module) {
		modules.add(module);
	}

	public void addProtectedBlock(int gui_id, IGHModBlock... block) {
		ProtectedBlocksRegistry.addBlock(gui_id, block);
	}

	public int getProtectedBlockID(Block b) {
		return ProtectedBlocksRegistry.getID(b);
	}

	public List<Block> getProtectedBlocks(int id) {
		return ProtectedBlocksRegistry.getBlock(id);
	}

	public void removeProtectedBlock(Block b) {
		ProtectedBlocksRegistry.removeBlock(b);
	}

	public boolean isProtectedBlock(Block b) {
		return ProtectedBlocksRegistry.isProtectedBlock(b);
	}

	public static final String NAME = "GameHelperAPI", VERSION = "0.1.2-b";

	public final String version() {
		return VERSION;
	}

	public final String name() {
		return NAME;
	}

	protected List tabs() {
		return null;
	}
}