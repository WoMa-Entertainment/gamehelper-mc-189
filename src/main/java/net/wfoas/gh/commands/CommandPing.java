package net.wfoas.gh.commands;

import java.util.Collections;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.wfoas.gh.GameHelper;

public class CommandPing extends GHCommand {

	@Override
	public String getCommandName() {
		return "ping";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/ping [Player]";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0) {
			EntityPlayerMP entityPlayerMP = (EntityPlayerMP) sender;
			sender.addChatMessage(new ChatComponentTranslation("gamehelper.msg.success.ping", entityPlayerMP.ping));
			return;
		} else {
			EntityPlayerMP entityPlayerMP = GameHelper.getUtils().getEntityPlayerByName(args[0]);
			if (entityPlayerMP == null) {
				sender.addChatMessage(new ChatComponentTranslation("gamehelper.error.player.notonline", args[0]));
				return;
			}
			sender.addChatMessage(new ChatComponentTranslation("gamehelper.msg.success.ping.player",
					entityPlayerMP.getName(), entityPlayerMP.ping));
			entityPlayerMP
					.addChatMessage(new ChatComponentTranslation("gamehelper.msg.success.ping", entityPlayerMP.ping));
			return;
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return sender instanceof EntityPlayerMP;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return GameHelper.getUtils().convertPlayerListToStringList(GameHelper.getUtils().getOnlinePlayers());
		} else
			return Collections.emptyList();
	}
}
