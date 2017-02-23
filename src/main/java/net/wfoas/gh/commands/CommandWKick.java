package net.wfoas.gh.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.event.GHEventBus;
import net.wfoas.gh.event.LoadDimensionEventCausedByPlayerRequest;
import net.wfoas.gh.event.PlayerChangeDimensionEvent;
import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.multipleworlds.WorldUtils;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;

public class CommandWKick extends CommandBase {

	@Override
	public String getCommandName() {
		return "wkick";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/wkick <Player> <World>";
	}

	public void tp(EntityPlayerMP sender, String _args_world) {
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
				sender.addChatMessage(new ChatComponentTranslation("gamehelper.error.dimensionchange.world.notfound",
						ChatColor.YELLOW + _args_world));
				return;
			}
		}
		GHWorld ghw = GHWorldManager.getLoadedGHWorld(_args_world);
		if (ghw == null) {
			sender.addChatMessage(new ChatComponentTranslation("gamehelper.error.dimensionchange.world.notfound",
					ChatColor.YELLOW + _args_world));
		}
		WorldServer ws = DimensionManager.getWorld(ghw.dimensionId);
		PlayerChangeDimensionEvent pcde = new PlayerChangeDimensionEvent((EntityPlayerMP) sender,
				((WorldServer) ((EntityPlayer) sender).worldObj).provider.getDimensionId(), ghw);
		GHEventBus.fireEvent(pcde);
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 2) {
			sender.addChatMessage(new ChatComponentText(ChatColor.RED + "To few args!"));
			return;
		}
		if (sender instanceof EntityPlayer) {
			EntityPlayerMP player = GameHelper.getUtils().getEntityPlayerByName(args[0]);
			if (player == null) {
				sender.addChatMessage(new ChatComponentText(ChatColor.RED + "Player '" + args[0] + "' is not online!"));
				return;
			}
			boolean b_s = false;
			for (String s : GHWorldManager.getGHWorldsAndNormalWorld()) {
				if (s.equalsIgnoreCase(args[1])) {
					b_s = true;
					break;
				}
			}
			if (b_s) {
				tp(player, DimensionManager.getWorld(0).getWorldInfo().getWorldName());
			}
		} else {
			sender.addChatMessage(new ChatComponentText(ChatColor.RED + "You are not a player!"));
			return;
		}
	}
}