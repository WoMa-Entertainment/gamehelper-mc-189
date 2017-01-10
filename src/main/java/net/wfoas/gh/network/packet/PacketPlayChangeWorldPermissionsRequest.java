package net.wfoas.gh.network.packet;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.world.owner.WorldOwners;
import net.wfoas.gh.world.permissions.WorldPermissions;
import net.wfoas.gh.world.utils.WorldPermission;
import net.wfoas.gh.world.utils.WorldPermissionsManager;

public class PacketPlayChangeWorldPermissionsRequest implements IMessage {

	public PacketPlayChangeWorldPermissionsRequest() {
	}

	UUID uid, sender;
	String world;
	WorldPermission perm;
	boolean allowed;

	public PacketPlayChangeWorldPermissionsRequest(UUID sender, UUID player, String world, WorldPermission perm,
			boolean allow) {
		this.uid = player;
		this.world = world;
		this.perm = perm;
		this.allowed = allow;
		this.sender = sender;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		uid = new UUID(buf.readLong(), buf.readLong());
		byte[] byteArray = new byte[buf.readInt()];
		buf.readBytes(byteArray);
		world = new String(byteArray, GameHelper.UTF_8);
		perm = WorldPermission.byNetcode(buf.readInt());
		allowed = buf.readBoolean();
		sender = new UUID(buf.readLong(), buf.readLong());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(uid.getMostSignificantBits());
		buf.writeLong(uid.getLeastSignificantBits());
		byte[] utf8 = world.getBytes(GameHelper.UTF_8);
		buf.writeInt(utf8.length);
		buf.writeBytes(utf8);
		buf.writeInt(perm.getNetcode());
		buf.writeBoolean(allowed);
		buf.writeLong(sender.getMostSignificantBits());
		buf.writeLong(sender.getLeastSignificantBits());
	}

	public static class PacketPlayChangeWorldPermissionsRequestHandler
			implements IMessageHandler<PacketPlayChangeWorldPermissionsRequest, IMessage> {

		@Override
		public IMessage onMessage(final PacketPlayChangeWorldPermissionsRequest message, MessageContext ctx) {
			GHWorld ghw = new GHWorld(message.world, "normal", "normal");
			if (WorldOwners.isOwner(ghw, GameHelper.getUtils().getEntityPlayerByUUID(message.sender))) {
				WorldPermissionsManager.setPermission(message.perm,
						GameHelper.getUtils().getEntityPlayerByUUID(message.uid), ghw, message.allowed);
			} else {
				GameHelper.getUtils().getEntityPlayerByUUID(message.sender).addChatMessage(new ChatComponentTranslation("gamehelper.error.world.nopermission.owner"));
			}
			return null;
		}
	}
}
