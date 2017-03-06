package net.wfoas.gh.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.sound.SoundHandlerGH;

public class CommandSound extends CommandBase {

	@Override
	public String getCommandName() {
		return "sound";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/sound <GH-Sound> <Pitch 0 - 1>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (!(sender instanceof EntityPlayer)) {
			sender.addChatMessage(new ChatComponentText(ChatColor.RED + "You are not a player!"));
		}
		if (args.length == 0) {
			sender.addChatMessage(
					new ChatComponentTranslation("gamehelper.error.notenoughparams.command", getCommandName()));
		} else if (args.length == 1) {
			EntityPlayerMP pl = (EntityPlayerMP) sender;
			String snd = args[0].toLowerCase();
			pl.worldObj.playSoundAtEntity(pl, "gamehelper:" + snd, 1f, GameHelper.MOD_RANDOM.nextInt(1000) / 1000f);
		} else if (args.length == 2) {
			EntityPlayerMP pl = (EntityPlayerMP) sender;
			String snd = args[0].toLowerCase();
			float pitch = 0f;
			try {
				pitch = Float.parseFloat(args[1]);
			} catch (Exception e) {
				sender.addChatMessage(new ChatComponentTranslation("gamehelper.error.notafloat", args[1]));
			}
			if (pitch > 1f)
				pitch = 1f;
			if (pitch < 0f)
				pitch = 0f;
			pl.worldObj.playSoundAtEntity(pl, "gamehelper:" + snd, 1f, pitch);
		} else {
			sender.addChatMessage(
					new ChatComponentTranslation("gamehelper.error.toomuchparams.command", getCommandName()));
		}
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, SoundHandlerGH.getSounds()) : null;
	}

}