package net.wfoas.gh.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.multipleworlds.WorldUtils;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;

public class CommandListWorld extends CommandBase {

	@Override
	public String getCommandName() {
		return "listworld";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/listworld";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		sender.addChatMessage(new ChatComponentTranslation("gamehelper.msg.listworlds.number",
				GHWorldManager.getGHWorldsAndNormalWorld().size()));
		for (String name : GHWorldManager.getGHWorldsAndNormalWorld()) {
			sender.addChatMessage(new ChatComponentText(ChatColor.GREEN + "-" + ChatColor.GREEN + name));
		}
		sender.addChatMessage(new ChatComponentText(ChatColor.GREEN + "------"));
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
}
