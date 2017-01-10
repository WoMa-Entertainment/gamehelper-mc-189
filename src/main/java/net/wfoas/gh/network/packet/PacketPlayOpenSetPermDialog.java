package net.wfoas.gh.network.packet;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.proxies.ClientProxy;
import net.wfoas.gh.world.owner.WorldOwners;

public class PacketPlayOpenSetPermDialog implements IMessage {
	public PacketPlayOpenSetPermDialog() {
	}

	List<String> worlds, players;

	public PacketPlayOpenSetPermDialog(EntityPlayerMP playerServerSide) {
		worlds = WorldOwners.getAllForPlayer(playerServerSide);
		players = new ArrayList<String>();
		for (EntityPlayerMP entityPlayer : GameHelper.getUtils().getOnlinePlayers()) {
			players.add(entityPlayer.getName());
		}
		// System.out.println("---SMessageWorlds---");
		// if (worlds != null) {
		// for (String s : worlds) {
		// System.out.println(s);
		// }
		// } else {
		// System.out.println("!=/World");
		// }
		// System.out.println("---/SMWorlds---");
		// System.out.println("---SMessagePlayers---");
		// for (String s : players) {
		// System.out.println(s);
		// }
		// System.out.println("---/SMPlayers---");
	}

	public List<String> getWorlds() {
		return worlds;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (worlds == null) {
			buf.writeInt(-1);
		} else {
			buf.writeInt(worlds.size());
			for (String s : worlds) {
				byte[] arry = s.getBytes(GameHelper.UTF_8);
				buf.writeInt(arry.length);
				buf.writeBytes(arry);
			}
		}
		buf.writeInt(players.size());
		for (String s : players) {
			byte[] arry = s.getBytes(GameHelper.UTF_8);
			buf.writeInt(arry.length);
			buf.writeBytes(arry);
			// System.out.println("Written: " + s);
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		if (worlds == null) {
			worlds = new ArrayList<String>();
		}
		if (players == null) {
			players = new ArrayList<String>();
		}
		int size = buf.readInt();
		if (size == -1)
			;
		else {
			for (int i = 0; i < size; i++) {
				int bytes = buf.readInt();
				byte[] array = new byte[bytes];
				buf.readBytes(array);
				worlds.add(new String(array, GameHelper.UTF_8));
			}
		}
		int size2 = buf.readInt();
		for (int i = 0; i < size2; i++) {
			int bytes = buf.readInt();
			byte[] array = new byte[bytes];
			buf.readBytes(array);
			players.add(new String(array, GameHelper.UTF_8));
			// System.out.println("Read: " + new String(array,
			// GameHelper.UTF_8));
		}
	}

	public static class PacketPlayOpenSetPermDialogHandler
			implements IMessageHandler<PacketPlayOpenSetPermDialog, IMessage> {

		@Override
		public IMessage onMessage(final PacketPlayOpenSetPermDialog message, MessageContext ctx) {
			// IThreadListener mainThread = (WorldServer)
			// ctx.getServerHandler().playerEntity.worldObj; // or
			// Minecraft.getMinecraft()
			// on
			// the
			// client
			// System.out.println("---MessageWorlds---");
			// for (String s : message.worlds) {
			// System.out.println(s);
			// }
			// System.out.println("---/MWorlds---");
			// System.out.println("---MessagePlayers---");
			// for (String s : message.players) {
			// System.out.println(s);
			// }
			// System.out.println("---/MPlayers---");
			ClientProxy.ownedWorlds.clear();
			ClientProxy.ownedWorlds.addAll(message.worlds);
			ClientProxy.onlinePlayers.clear();
			ClientProxy.onlinePlayers.addAll(message.players);
			// ClientProxy.onlinePlayers.add("String");
			// System.out.println("---Worlds---");
			// for (String s : ClientProxy.ownedWorlds) {
			// System.out.println(s);
			// }
			// System.out.println("---/Worlds---");
			// System.out.println("---Players---");
			// for (String s : ClientProxy.onlinePlayers) {
			// System.out.println(s);
			// }
			// System.out.println("---/Players---");

			if (ClientProxy.onlinePlayers.isEmpty() || ClientProxy.ownedWorlds.isEmpty()) {
				Minecraft.getMinecraft().thePlayer
						.addChatComponentMessage(new ChatComponentTranslation("gamehelper.error.noworldsornoplayers"));
//				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
//						"An error is occurred, please report this to the mod author: error code 0xefffec"));
//				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
//						"Ein Fehler ist aufgetreten, bitte melde es dem Entwickler: error code 0xefffec"));
				 return null;
			}

			Minecraft.getMinecraft().thePlayer.openGui(GameHelper.instance, GuiHandler.SET_PERMISSION_DIALOG,
					Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0, 0, 0);
			return null; // no response in this case
		}
	}
}