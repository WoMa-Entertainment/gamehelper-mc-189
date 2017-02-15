package net.wfoas.gh.commands;

import de.winston.network.playerranks.PlayerRank;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.wfoas.gh.GameHelper;

public class CommandHackSec extends CommandBase {

	@Override
	public String getCommandName() {
		return "hacksec";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/hacksec [Player]";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0) {
			GameHelper.getUtils().processHacker((EntityPlayerMP) sender);
		} else {
			EntityPlayerMP epmp = GameHelper.getUtils().getEntityPlayerByName(args[0]);
			if (epmp == null) {
				sender.addChatMessage(new ChatComponentTranslation("gamehelper.error.player.notonline", args[0]));
			} else {
				GameHelper.getUtils().processHacker((EntityPlayerMP) sender);
			}
		}
	}
}