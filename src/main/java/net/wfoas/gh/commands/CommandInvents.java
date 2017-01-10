package net.wfoas.gh.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.wfoas.gh.GameHelper;

public class CommandInvents extends CommandBase {

	@Override
	public String getCommandName() {
		return "invents";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/invents <Player>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		if (sender instanceof EntityPlayer) {
			return GameHelper.getUtils().isSinglePlayer() || GameHelper.getUtils().isOp(((EntityPlayer) sender));
		}
		return false;
	}
}
