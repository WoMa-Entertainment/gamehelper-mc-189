package net.wfoas.gh.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.multipleworlds.WorldUtils;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;
import net.wfoas.gh.network.NetworkUtils;
import net.wfoas.gh.proxies.ClientProxy;
import net.wfoas.gh.world.owner.WorldOwners;

public class PacketPlayOwnWorldCollection {
	public static enum EditType {
		ADD(+1), REMOVE(-1);
		EditType(int value) {
			this.value = value;
		}

		final int value;

		public final int getValue() {
			return value;
		}
	}

	public static class PacketPlayOwnWorldChangeWorldOwner implements IMessage {
		String world, name;
		int editType;

		public PacketPlayOwnWorldChangeWorldOwner(String world, EditType et, String name) {
			this.world = world;
			this.name = name;
			editType = et.value;
		}

		public PacketPlayOwnWorldChangeWorldOwner() {
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			editType = buf.readInt();
			name = NetworkUtils.readUTF8String(buf);
			world = NetworkUtils.readUTF8String(buf);
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(editType);
			NetworkUtils.writeUTF8String(name, buf);
			NetworkUtils.writeUTF8String(world, buf);
		}

		public static class PacketPlayOwnWorldChangeWorldOwnerHandler
				implements IMessageHandler<PacketPlayOwnWorldChangeWorldOwner, PacketPlayOwnWorldEditorUnlocked> {

			@Override
			public PacketPlayOwnWorldEditorUnlocked onMessage(final PacketPlayOwnWorldChangeWorldOwner message,
					MessageContext ctx) {
				if (message.name != null) {
					if (GameHelper.getUtils().getEntityPlayerByName(message.name) != null) {
						WorldOwners.setOwner(new GHWorld(message.world, "", ""),
								GameHelper.getUtils().getEntityPlayerByName(message.name),
								message.editType == EditType.ADD.getValue());
					}
				}
				return new PacketPlayOwnWorldEditorUnlocked();
			}
		}
	}

	public static class PacketPlayOwnWorldEditorUnlocked implements IMessage {

		@Override
		public void fromBytes(ByteBuf buf) {
		}

		@Override
		public void toBytes(ByteBuf buf) {
		} // SEND WHEN PacketPlayOwnWorldChangeWorldOwner is received and the
			// worldownerlist gets updated

	}

	public static class PacketPlayListWorldOwnersForAllWorlds implements IMessage {

		@Override
		public void fromBytes(ByteBuf buf) {
			int i_ = buf.readInt();
			ClientProxy.allWorlds.clear();
			for (int i = 0; i < i_; i++) {
				ClientProxy.allWorlds.add(NetworkUtils.readUTF8String(buf));
			}
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(GHWorldManager.getGHWorldsAndNormalWorld().size());
			for (String s : GHWorldManager.getGHWorldsAndNormalWorld()) {
				buf.writeInt(s.length());
				NetworkUtils.writeUTF8String(s, buf);
			}
		}

		public static class PacketPlayListWorldOwnersForAllWorldsHandler
				implements IMessageHandler<PacketPlayListWorldOwnersForAllWorlds, IMessage> {
			@Override
			public IMessage onMessage(PacketPlayListWorldOwnersForAllWorlds message, MessageContext ctx) {
				return null;
			}
		}
	}
}