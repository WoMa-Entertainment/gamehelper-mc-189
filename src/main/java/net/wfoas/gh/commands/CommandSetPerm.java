package net.wfoas.gh.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.wfoas.gh.network.gui.RemoteGuiOpener;

public class CommandSetPerm extends GHCommand {

	@Override
	public String getCommandName() {
		return "setperm";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/setperm";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (!(sender instanceof EntityPlayerMP)) {
			sender.addChatMessage(new ChatComponentText("You must be a player!"));
			return;
		}
		RemoteGuiOpener.openSetPermDialog((EntityPlayerMP) sender);
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
}
