package de.winston.network.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.dropsapi.pdr.ChatColor;

public class HubCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return null;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return null;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (sender instanceof EntityPlayerMP) {
			GameHelper.getUtils().sendPlayerToServer((EntityPlayerMP) sender, GameHelper.getUtils().HUB_ADDRESS);
			return;
		}
		sender.addChatMessage(new ChatComponentText(
				ChatColor.RED + "Du kannst nicht auf einen anderen Server gesendet werden, da du kein Spieler bist!"));
		return;
	}
}
