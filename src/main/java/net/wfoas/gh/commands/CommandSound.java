package net.wfoas.gh.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

public class CommandSound extends CommandBase {

	@Override
	public String getCommandName() {
		return "sound";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/sound <GH-Sound>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {

	}

}
