package net.wfoas.gh.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.wfoas.gh.notifysettings.NotifyTable;

public class CommandToggleNotify extends GHCommand {
	@Override
	public String getCommandName() {
		return "togglenotify";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/togglenotify [true|false]";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (!(sender instanceof EntityPlayerMP)) {
			sender.addChatMessage(new ChatComponentText("§cYou must be a player to perform that task!"));
			return;
		}
		boolean _new;
		if (args.length == 0) {
			_new = !NotifyTable.playerWantsNotification((EntityPlayerMP) sender);
		} else {
			_new = Boolean.parseBoolean(args[0]);
		}
		NotifyTable.toggleNotification((EntityPlayerMP) sender, _new);
		sender.addChatMessage(
				new ChatComponentTranslation("gamehelper.info.notificationsystem." + (_new ? "enabled" : "disabled")));
	}
}