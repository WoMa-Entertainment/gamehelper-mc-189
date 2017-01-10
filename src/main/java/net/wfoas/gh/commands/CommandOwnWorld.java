package net.wfoas.gh.commands;

import java.util.ArrayList;
import java.util.List;

import de.winston.network.playerranks.PlayerRank;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.common.DimensionManager;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;
import net.wfoas.gh.world.owner.WorldOwners;
import net.wfoas.gh.world.utils.WorldPermissionsManager;

public class CommandOwnWorld extends CommandBase {

	@Override
	public String getCommandName() {
		return "ownworld";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/ownworld <Spieler> <Welt> <true | false>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		try {
			// if (!(sender instanceof EntityPlayerMP)) {
			// sender.addChatMessage(new ChatComponentText("You are not a
			// player!"));
			// return;
			// }
			if (args.length < 3) {
				sender.addChatMessage(
						new ChatComponentTranslation("gamehelper.error.notenoughparams.command", getCommandName()));
				return;
			}
			if (sender instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP)sender;
				PlayerRank pr = GameHelper.getUtils().getRankRetriever().getRank(player);
				if (pr == PlayerRank.DEV || pr == PlayerRank.OWNER || pr == PlayerRank.ADMIN
						|| pr == PlayerRank.ADMIN_DEV || pr == PlayerRank.OWNER_DEV) {
					if (GHWorldManager.isWorldAlreadyCreated(args[1])) {
						GHWorldManager.loadWorld(new GHWorld(args[1], "", ""));
						GHWorld ghw = GHWorldManager.getLoadedGHWorld(args[1]);
						boolean bool = Boolean.parseBoolean(args[2]);
						player = GameHelper.getUtils().getEntityPlayerByName(args[0]);
						if(player == null){
							sender.addChatMessage(new ChatComponentTranslation(
									"gamehelper.error.player.notonline", args[0]));
							return;
						}
						WorldOwners.setOwner(ghw, player, bool);
						//player = (EntityPlayerMP)sender;
						if (bool)
							sender.addChatMessage(new ChatComponentTranslation(
									"gamehelper.msg.success.worldownerchanged.true", player.getName(), ghw.getName()));
						if (!bool)
							sender.addChatMessage(new ChatComponentTranslation(
									"gamehelper.msg.success.worldownerchanged.false", player.getName(), ghw.getName()));
					} else {
						if (GHWorldManager.existsAndIsNonGHWorld(args[1])) {
							boolean bool = Boolean.parseBoolean(args[2]);
							WorldOwners.setOwner(DimensionManager.getWorld(0), player, bool);
							//player = (EntityPlayerMP)sender;
							if (bool)
								sender.addChatMessage(new ChatComponentTranslation(
										"gamehelper.msg.success.worldownerchanged.true", player.getName(),
										DimensionManager.getWorld(0).getWorldInfo().getWorldName()));
							if (!bool)
								sender.addChatMessage(new ChatComponentTranslation(
										"gamehelper.msg.success.worldownerchanged.false", player.getName(),
										DimensionManager.getWorld(0).getWorldInfo().getWorldName()));
						}
					}
				} else {
					sender.addChatMessage(
							new ChatComponentTranslation("gamehelper.error.nopermission.command", getCommandName()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static List<String> true_false;
	static {
		true_false = new ArrayList<String>();
		true_false.add("true");
		true_false.add("false");
	}

	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames())
				: (args.length == 2 ? getListOfStringsMatchingLastWord(args, GHWorldManager.getWorlds())
						: (args.length == 3 ? getListOfStringsMatchingLastWord(args, true_false) : null));
	}
}