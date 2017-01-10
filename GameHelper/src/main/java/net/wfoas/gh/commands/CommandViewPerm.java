package net.wfoas.gh.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.wfoas.gh.network.gui.RemoteGuiOpener;

public class CommandViewPerm extends CommandBase {

	@Override
	public String getCommandName() {
		return "viewperm";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/viewperm";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (!(sender instanceof EntityPlayerMP)) {
			sender.addChatMessage(new ChatComponentText("You must be a player!"));
			return;
		}
		RemoteGuiOpener.openViewPermDialog((EntityPlayerMP) sender);
	}

}
