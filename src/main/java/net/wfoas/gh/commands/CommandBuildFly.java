package net.wfoas.gh.commands;

import java.util.Collections;
import java.util.List;

import de.winston.network.playerranks.MySQLRanks;
import de.winston.network.playerranks.PlayerRank;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandGameMode;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatComponentTranslationFormatException;
import net.minecraftforge.fml.relauncher.Side;
import net.wfoas.gh.GameHelper;

public class CommandBuildFly extends GHCommand {

	@Override
	public String getCommandName() {
		return "buildfly";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/buildfly [Player]";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP player = null;
		if (args.length == 0) {
			if (sender instanceof EntityPlayerMP) {
				player = (EntityPlayerMP) sender;
			} else {
				sender.addChatMessage(new ChatComponentTranslation("gamehelper.error.invalid.commandsender"));
				return;
			}
		} else {
			player = GameHelper.getUtils().getEntityPlayerByName(args[0]);
			if (player == null) {
				sender.addChatMessage(new ChatComponentTranslation("gamehelper.error.player.notonline"));
				return;
			}
		}
		boolean fly = player.capabilities.allowFlying = !player.capabilities.allowFlying;
		if (!fly) {
			player.capabilities.isFlying = false;
		}
		player.sendPlayerAbilities();
		player.addChatMessage(
				new ChatComponentTranslation(fly ? "gamehelper.msg.flyingenabled" : "gamehelper.msg.flyingdisabled"));
		if (sender != player) {
			sender.addChatMessage(new ChatComponentTranslation(
					fly ? "gamehelper.msg.flyingenabled" : "gamehelper.msg.flyingdisabled", player.getName()));
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		if (MinecraftServer.getServer().isSinglePlayer())
			return true;
		if (GameHelper.EVENT_SIDE == Side.SERVER && GameHelper.instance.CONFIG.getBoolean("sql"))
			return (sender instanceof EntityPlayer) && MySQLRanks.getRank(((EntityPlayer) sender).getUniqueID())
					.getSQLValue() > PlayerRank.DEV.getSQLValue();
		return sender instanceof EntityPlayer && GameHelper.getUtils().isOp((EntityPlayer) sender);
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return GameHelper.getUtils().convertPlayerListToStringList(GameHelper.getUtils().getOnlinePlayers());
		} else
			return Collections.emptyList();
	}
}
