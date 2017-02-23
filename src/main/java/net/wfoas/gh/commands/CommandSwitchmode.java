package net.wfoas.gh.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

public class CommandSwitchmode extends CommandBase {

	@Override
	public String getCommandName() {
		return "switchmode";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/switchmode <SURVIVAL | WORLD>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		
	}

}