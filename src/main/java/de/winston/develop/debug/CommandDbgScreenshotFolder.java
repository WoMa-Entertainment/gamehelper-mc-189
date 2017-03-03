package de.winston.develop.debug;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.wfoas.gh.commands.GHCommand;

public class CommandDbgScreenshotFolder extends GHCommand {

	@Override
	public String getCommandName() {
		return "dbg_screenshotfolder";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/dbg_screenshotfolder";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		sender.addChatMessage(
				new ChatComponentText((new File(Minecraft.getMinecraft().mcDataDir, "screenshots")).getAbsolutePath()));
	}
}