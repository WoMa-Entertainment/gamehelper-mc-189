package net.wfoas.gh.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.playernameuuid.PlayerNameUUID;
import net.wfoas.gh.world.owner.WorldOwners;
import net.wfoas.gh.world.permissions.WorldPermissions;

public class CommandSaveData extends CommandBase {

	@Override
	public String getCommandName() {
		return "savedata";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/savedata";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		long ts1 = System.nanoTime();
		WorldPermissions.save();
		WorldOwners.save();
		PlayerNameUUID.save();
		// for (WorldServer ws : DimensionManager.getWorlds()) {
		// ws.saveChunkData();CommandSaveAll
		// } // TODO save-data crashes server CommandSaveAll
		MinecraftServer minecraftserver = MinecraftServer.getServer();
		sender.addChatMessage(new ChatComponentTranslation("commands.save.start", new Object[0]));

		if (minecraftserver.getConfigurationManager() != null) {
			minecraftserver.getConfigurationManager().saveAllPlayerData();
		}

		try {
			for (int i = 0; i < minecraftserver.worldServers.length; ++i) {
				if (minecraftserver.worldServers[i] != null) {
					WorldServer worldserver = minecraftserver.worldServers[i];
					boolean flag = worldserver.disableLevelSaving;
					worldserver.disableLevelSaving = false;
					worldserver.saveAllChunks(true, (IProgressUpdate) null);
					worldserver.disableLevelSaving = flag;
				}
			}

			if (args.length > 0 && "flush".equals(args[0])) {
				sender.addChatMessage(new ChatComponentTranslation("commands.save.flushStart", new Object[0]));

				for (int j = 0; j < minecraftserver.worldServers.length; ++j) {
					if (minecraftserver.worldServers[j] != null) {
						WorldServer worldserver1 = minecraftserver.worldServers[j];
						boolean flag1 = worldserver1.disableLevelSaving;
						worldserver1.disableLevelSaving = false;
						worldserver1.saveChunkData();
						worldserver1.disableLevelSaving = flag1;
					}
				}

				sender.addChatMessage(new ChatComponentTranslation("commands.save.flushEnd", new Object[0]));
			}
		} catch (MinecraftException minecraftexception) {
			notifyOperators(sender, this, "commands.save.failed", new Object[] { minecraftexception.getMessage() });
			return;
		}

		notifyOperators(sender, this, "commands.save.success", new Object[0]);
		long timeMS = (System.nanoTime() - ts1) / 1000000;
		GameHelper.getUtils().broadcast(new ChatComponentTranslation("gamehelper.msg.savedalldata.time", timeMS));
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		if (sender instanceof EntityPlayer)
			return GameHelper.getUtils().isSinglePlayer() || GameHelper.getUtils().isOp((EntityPlayer) sender);
		return true;
	}
}
