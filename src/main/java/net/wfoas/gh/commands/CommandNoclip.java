package net.wfoas.gh.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldSettings.GameType;

public class CommandNoclip extends GHCommand {

	@Override
	public String getCommandName() {
		return "noclip";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/noclip";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
//		try {
			if ((sender instanceof EntityPlayerMP)) {
				NBTTagCompound nbttc = ((NBTTagCompound) ((EntityPlayerMP) sender).getEntityData()
						.getTag(EntityPlayerMP.PERSISTED_NBT_TAG));
				if (nbttc == null) {
					((EntityPlayerMP) sender).getEntityData().setTag(EntityPlayerMP.PERSISTED_NBT_TAG,
							new NBTTagCompound());

					nbttc = ((NBTTagCompound) ((EntityPlayerMP) sender).getEntityData()
							.getTag(EntityPlayerMP.PERSISTED_NBT_TAG));
				}
				if (nbttc.getBoolean("noclip_mode")) {
					nbttc.setBoolean("noclip_mode", !nbttc.getBoolean("noclip_mode"));
					nbttc.setInteger("prev_gamemode",
							((EntityPlayerMP) sender).theItemInWorldManager.getGameType().getID());
					((EntityPlayerMP) sender).setGameType(GameType.SPECTATOR);
					sender.addChatMessage(new ChatComponentTranslation("gamehelper.msg.noclipmode.on"));
				} else {
					nbttc.setBoolean("noclip_mode", !nbttc.getBoolean("noclip_mode"));
					// nbttc.setInteger("prev_gamemode",
					// ((EntityPlayerMP)
					// sender).theItemInWorldManager.getGameType().getID());
					((EntityPlayerMP) sender).setGameType(GameType.getByID(nbttc.getInteger("prev_gamemode")));
					sender.addChatMessage(new ChatComponentTranslation("gamehelper.msg.noclipmode.off"));
				}
			}
//		} catch (Exception e) {
//			e.printStackTrace(System.err);
//		}
	}
}