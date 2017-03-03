package net.wfoas.gh.selectiontool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.commands.GHCommand;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.ghschematics.GHSchmematicsUtils;

public class CommandExportStruct extends GHCommand {

	@Override
	public String getCommandName() {
		return "exportstruct";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/exportstruct <Name> [cl|sv]";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException { // TODO TileEntity Furnace and brewingstand and commandblock
		if (sender instanceof EntityPlayerMP) {
			try {
				if (args.length == 0) {
					sender.addChatMessage(new ChatComponentText(ChatColor.RED + "Lenght is not valid!"));
				}
				String name = args[0];
				EntityPlayerMP playerMp = (EntityPlayerMP) sender;
				int sx = Math.min(SelectionProperties.getItem("pos_1_x", playerMp),
						SelectionProperties.getItem("pos_2_x", playerMp));
				int lx = Math.max(SelectionProperties.getItem("pos_1_x", playerMp),
						SelectionProperties.getItem("pos_2_x", playerMp));

				int sy = Math.min(SelectionProperties.getItem("pos_1_y", playerMp),
						SelectionProperties.getItem("pos_2_y", playerMp));
				int ly = Math.max(SelectionProperties.getItem("pos_1_y", playerMp),
						SelectionProperties.getItem("pos_2_y", playerMp));

				int sz = Math.min(SelectionProperties.getItem("pos_1_z", playerMp),
						SelectionProperties.getItem("pos_2_z", playerMp));
				int lz = Math.max(SelectionProperties.getItem("pos_1_z", playerMp),
						SelectionProperties.getItem("pos_2_z", playerMp));

				GHSchmematicsUtils.writeToStream(
						new FileOutputStream(new File(GameHelper.instance.structuresDir, name + ".ghstruct")), playerMp,
						sx, lx, sy, ly, sz, lz);
			} catch (Exception e) {
				if (e instanceof FileNotFoundException) {
					sender.addChatMessage(
							new ChatComponentText(ChatColor.RED + "FileNotFoundException: " + e.getLocalizedMessage()));
					e.printStackTrace();
					return;
				} else {
					sender.addChatMessage(
							new ChatComponentText(ChatColor.RED + "Error: IOException: " + e.getLocalizedMessage()));
					e.printStackTrace();
					return;
				}
			}
			sender.addChatMessage(
					new ChatComponentText(ChatColor.GREEN + "Success: Exported to file " + args[0] + ".ghstruct"));
		} else {
			sender.addChatMessage(new ChatComponentText(ChatColor.RED + "You must be a player!"));
		}
	}
}
