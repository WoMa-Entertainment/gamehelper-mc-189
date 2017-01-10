package de.winston.network.playerranks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.command.server.CommandTeleport;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.wfoas.gh.GameHelper;

public class PlayerRanksCommand extends CommandBase {
	// public boolean onCommand(CommandSender sender, Command command, String
	// label, String[] args) {
	// if (!(sender.isOp())) {
	// sender.sendMessage(
	// GameHelper.PREFIX_ERROR + "Du bist kein Administrator / Dev / Admin_Dev /
	// Mod auf diesen Server.");
	// return true;
	// }
	// if (args.length <= 1) {
	// sender.sendMessage(GameHelper.PREFIX_ERROR + "Nicht genug Parameter! Es
	// fehlen Spieler und / oder Rang.");
	// sender.sendMessage(GameHelper.PREFIX_ERROR + "/rank <Spieler> <Rang>");
	// sender.sendMessage(GameHelper.PREFIX_ERROR
	// + "Rang <ADMIN | ADMIN_DEV | PREMI | MOD | YT | DEV | NORMAL | OWNER |
	// OWNER_DEV>");
	// return true;
	// }
	// Player player = Bukkit.getPlayer(args[0]);
	// if (player == null) {
	// sender.sendMessage(GameHelper.PREFIX_ERROR + "Der Spieler existiert nicht
	// / ist offline.");
	// return true;
	// }
	// for (PlayerRank rank : PlayerRank.values()) {
	// if (String.valueOf(rank).equalsIgnoreCase(args[1])) {
	// MySQLRanks.updatePlayer(player.getUniqueId(), rank);
	// Scoreboard scoreboard =
	// Bukkit.getScoreboardManager().getMainScoreboard();
	// Team team = scoreboard.getTeam(String.valueOf(rank));
	// if (team == null) {
	// team = scoreboard.registerNewTeam(String.valueOf(rank));
	// team.setPrefix(rank.prefix);
	// if (rank.suffix != null)
	// team.setSuffix(rank.suffix);
	// team.setAllowFriendlyFire(true);
	// team.setCanSeeFriendlyInvisibles(false);
	// }
	// // Bukkit.broadcastMessage("finally " + String.valueOf(rank));
	// team.addPlayer(player);
	// // player.setPlayerListName(rank.prefix + player.getName());
	// player.setDisplayName(rank.prefix + player.getName() + rank.suffix);
	// sender.sendMessage(GameHelper.PREFIX_SUCCESS + "Dem Spieler '" + args[0]
	// + "' wurde der Rang '"
	// + args[1] + "' zugewiesen.");
	// MySQLRanks.changeName(player);
	// // player.kickPlayer(GameHelper.PREFIX_SUCCESS + "Dir wurde der
	// // Rang '" + args[1] + "' zugewiesen.");
	// return true;
	// }
	// }
	// sender.sendMessage(GameHelper.PREFIX_ERROR + "Der Rang '" + args[1] + "'
	// existiert nicht.");
	// return true;
	// }
	//
	// @Override
	// public List<String> onTabComplete(CommandSender sender, Command command,
	// String alias, String[] args) {
	// if (args.length == 1) {
	// List<String> players = new ArrayList<String>();
	// for (Player player : Bukkit.getOnlinePlayers()) {
	// players.add(player.getName());
	// }
	// return players;
	// }
	// if (args.length == 2) {
	// List<String> ranks = new ArrayList<String>();
	// for (PlayerRank rank : PlayerRank.values()) {
	// ranks.add(String.valueOf(rank));
	// }
	// return ranks;
	// }
	// return null;
	// }

	@Override
	public String getCommandName() {
		return "rankupdate";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/rankupdate";
	}

	public void updateRank(EntityPlayerMP playerMP, PlayerRank pr) {
		GameHelper.getUtils().getRankRetriever().setRank(playerMP, pr);
		playerMP.addChatComponentMessage(
				new ChatComponentTranslation("gamehelper.command.accepted.rank", pr.toString()));
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 2) {
			sender.addChatMessage(new ChatComponentTranslation("gamehelper.error.notenoughparams.command"));
			return;
		}
		if (sender instanceof CommandBlockLogic) {
			sender.addChatMessage(new ChatComponentTranslation("gamehelper.msg.command.notallowed.commandblock"));
			return;
		} else if (sender instanceof EntityPlayerMP) {
			if ((GameHelper.getUtils().isOp((EntityPlayerMP) sender)) || GameHelper.getUtils().isSinglePlayer()) {
				EntityPlayerMP player = GameHelper.getUtils().getEntityPlayerByName(args[0]);
				PlayerRank pr = PlayerRank.parseFromString(args[1]);
				if (pr == null) {
					sender.addChatMessage(
							new ChatComponentTranslation("gamehelper.error.command.notexisin.rank", args[1]));
					return;
				}
				if (player == null) {
					sender.addChatMessage(new ChatComponentTranslation("gamehelper.error.player.notonline", args[0]));
					return;
				}
				updateRank(player, pr);
				if (!sender.equals(player))
					sender.addChatMessage(new ChatComponentTranslation("gamehelper.command.accepted.rank.player",
							player.getName(), pr.toString()));
			}
			return;
		} else if (sender instanceof MinecraftServer) {
			EntityPlayerMP player = GameHelper.getUtils().getEntityPlayerByName(args[0]);
			PlayerRank pr = PlayerRank.parseFromString(args[1]);
			if (pr == null) {
				sender.addChatMessage(new ChatComponentTranslation("gamehelper.error.command.notexisin.rank", args[1]));
				return;
			}
			if (player == null) {
				sender.addChatMessage(new ChatComponentTranslation("gamehelper.error.player.notonline", args[0]));
				return;
			}
			updateRank(player, pr);
			if (!sender.equals(player))
				sender.addChatMessage(new ChatComponentTranslation("gamehelper.command.accepted.rank.player",
						player.getName(), pr.toString()));

			return;
		}
	}

	public static List<String> fromArray(Object[] o) {
		List<String> arr = new ArrayList<String>();
		for (Object o2 : o)
			arr.add(o2.toString());
		return arr;
	}

	protected final List<String> playerRanks = fromArray(PlayerRank.values());

	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		return args.length != 1 ? playerRanks
				: getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
	}
}
