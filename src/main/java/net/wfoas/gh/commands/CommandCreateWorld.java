package net.wfoas.gh.commands;

import de.winston.network.playerranks.MySQLRanks;
import de.winston.network.playerranks.PlayerRank;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.relauncher.Side;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.gui.RemoteGuiOpener;
import net.wfoas.gh.network.packet.PacketPlayOpenClientCreateWorldGui;

public class CommandCreateWorld extends GHCommand {

	@Override
	public String getCommandName() {
		return "createworld";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/createworld";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (sender instanceof EntityPlayerMP) {
			// ((EntityPlayer) (sender)).openGui(GameHelper.instance,
			// GuiHandler.CREATE_IMPORT_WORLD_DIALOG,
			// sender.getEntityWorld(), 0, 0, 0);
			// NetworkHandler.sendToSpecificPlayer(new
			// PacketPlayOpenClientCreateWorldGui(), (EntityPlayerMP) sender);
			RemoteGuiOpener.openCreateWorldGui((EntityPlayerMP) sender);
			// EntityPlayerMP epmp = (EntityPlayerMP) sender;
			// GameHelper.getScheduler().scheduleSyncDelayedTask(new Runnable()
			// {
			// @Override
			// public void run() {
			// if (!epmp.worldObj.isRemote) {
			// epmp.closeContainer();
			// epmp.openGui(GameHelper.instance, GuiHandler.MINERS_INVENTORY,
			// epmp.worldObj, 0, 0, 0);
			// }
			// }
			// }, 0l);
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
}
