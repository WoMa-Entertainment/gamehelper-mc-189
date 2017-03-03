package net.wfoas.gh.commands;

import java.util.Collections;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldServer;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.event.GHEventBus;
import net.wfoas.gh.event.PlayerChangeDimensionEvent;
import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;

public class CommandTpxp extends GHCommand {

	@Override
	public String getCommandName() {
		return "tpxp";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/tpxp <Player>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (sender instanceof EntityPlayer) {
			EntityPlayerMP otherPlayer = GameHelper.getUtils().getEntityPlayerByName(args[0]);
			if (otherPlayer == null) {
				sender.addChatMessage(new ChatComponentTranslation("gamehelper.error.player.notonline", args[0]));
				return;
			}
			if (otherPlayer.equals(sender)) {
				otherPlayer.setPositionAndUpdate(otherPlayer.worldObj.getSpawnPoint().getX(),
						otherPlayer.worldObj.getSpawnPoint().getY(), otherPlayer.worldObj.getSpawnPoint().getZ());
				return;
			}
			// otherPlayer.dimension
			GHWorld ghw = GHWorldManager.getGHWorldByDimensionIDForce(otherPlayer.dimension, true);
			PlayerChangeDimensionEvent pcde = new PlayerChangeDimensionEvent((EntityPlayerMP) sender,
					((WorldServer) ((EntityPlayer) sender).worldObj).provider.getDimensionId(), ghw);
			GHEventBus.fireEvent(pcde);
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		if (sender instanceof EntityPlayer) {
			return GameHelper.getUtils().isOp((EntityPlayer) sender) || GameHelper.getUtils().isSinglePlayer();
		}
		return false;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return GameHelper.getUtils().convertPlayerListToStringList(GameHelper.getUtils().getOnlinePlayers());
		} else
			return Collections.emptyList();
	}
}