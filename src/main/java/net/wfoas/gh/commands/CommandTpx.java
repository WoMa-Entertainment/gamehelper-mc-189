package net.wfoas.gh.commands;

import java.util.ArrayList;
import java.util.List;

import de.winston.network.playerranks.MySQLRanks;
import de.winston.network.playerranks.PlayerRank;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.event.GHEventBus;
import net.wfoas.gh.event.LoadDimensionEventCausedByPlayerRequest;
import net.wfoas.gh.event.PlayerChangeDimensionEvent;
import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;

public class CommandTpx extends CommandBase {

	@Override
	public String getCommandName() {
		return "tpx";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/tpx <World>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		try {
			processCommand0(sender, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void processCommand0(ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0) {
			sender.addChatMessage(new ChatComponentTranslation("gamehelper.error.dimensionchange.notenoughargs"));
			return;
		}
		if (sender instanceof EntityPlayer) {
			String _args_world = args[0].replaceAll("%20", " ");
			if (DimensionManager.getWorld(0).getWorldInfo().getWorldName().equalsIgnoreCase(_args_world)) {
				PlayerChangeDimensionEvent pcde = new PlayerChangeDimensionEvent((EntityPlayerMP) sender,
						((WorldServer) ((EntityPlayer) sender).worldObj).provider.getDimensionId(),
						new GHWorld("_DIMID:0", null, null));
				GHEventBus.fireEvent(pcde);
				return;
			}
			if (!GHWorldManager.isWorldAlreadyLoaded(new GHWorld(_args_world, null, null))) {
				if (GHWorldManager.isWorldAlreadyCreated(new GHWorld(_args_world, null, null))) {
					LoadDimensionEventCausedByPlayerRequest load = new LoadDimensionEventCausedByPlayerRequest(
							new GHWorld(_args_world, null, null));
					GHEventBus.fireEvent(load);
				} else {
					sender.addChatMessage(new ChatComponentTranslation(
							"gamehelper.error.dimensionchange.world.notfound", ChatColor.YELLOW + _args_world));
					return;
				}
			}
			GHWorld ghw = GHWorldManager.getLoadedGHWorld(_args_world);
			if (ghw == null) {
				sender.addChatMessage(new ChatComponentTranslation("gamehelper.error.dimensionchange.world.notfound",
						ChatColor.YELLOW + _args_world));
			}
			System.out.println("ghw: " + ghw);
			WorldServer ws = DimensionManager.getWorld(ghw.dimensionId);
			PlayerChangeDimensionEvent pcde = new PlayerChangeDimensionEvent((EntityPlayerMP) sender,
					((WorldServer) ((EntityPlayer) sender).worldObj).provider.getDimensionId(), ghw);
			GHEventBus.fireEvent(pcde);
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
		List<String> slist = new ArrayList<String>();
		for (String s : GHWorldManager.getGHWorldsAndNormalWorld()) {
			slist.add(s.replaceAll(" ", "%20"));
		}
		return slist;
	}
}