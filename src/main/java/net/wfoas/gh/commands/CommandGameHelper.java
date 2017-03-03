package net.wfoas.gh.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.wfoas.gh.GameHelperServer;

public class CommandGameHelper extends GHCommand {

	@Override
	public String getCommandName() {
		return "gamehelper";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/gamehelper [arg*]";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		sender.addChatMessage(new ChatComponentTranslation("---GameHelper: Mod---"));
		int ic = 1;
		for (CommandBase cb : GameHelperServer.getCommand()) {
			sender.addChatMessage(new ChatComponentText(ic + ". " + cb.getCommandUsage(sender)));
			ic++;
		}
		sender.addChatMessage(new ChatComponentTranslation("---Ende---"));
	}
}