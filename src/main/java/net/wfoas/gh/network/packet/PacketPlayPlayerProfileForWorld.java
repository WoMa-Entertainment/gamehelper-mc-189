package net.wfoas.gh.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;

public class PacketPlayPlayerProfileForWorld implements IMessage {

	public PacketPlayPlayerProfileForWorld() {
	}

	boolean visit, destroy, build, kill, collect_items_exp, interact;

	public PacketPlayPlayerProfileForWorld(boolean visit, boolean destroy, boolean build, boolean kill,
			boolean collect_items_exp, boolean interact) {
		this.visit = visit;
		this.destroy = destroy;
		this.build = build;
		this.kill = kill;
		this.collect_items_exp = collect_items_exp;
		this.interact = interact;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		visit = buf.readBoolean();
		destroy = buf.readBoolean();
		build = buf.readBoolean();
		kill = buf.readBoolean();
		collect_items_exp = buf.readBoolean();
		interact = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(visit);
		buf.writeBoolean(destroy);
		buf.writeBoolean(build);
		buf.writeBoolean(kill);
		buf.writeBoolean(collect_items_exp);
		buf.writeBoolean(interact);
	}

	public static class PacketPlayPlayerProfileForWorldHandler
			implements IMessageHandler<PacketPlayPlayerProfileForWorld, IMessage> {
		@Override
		public IMessage onMessage(PacketPlayPlayerProfileForWorld message, MessageContext ctx) {
			if (GameHelper.getUtils().setPermScreen != null) {
				GameHelper.getUtils().setProfileForGuiPermScreen(message.visit, message.destroy, message.build,
						message.kill, message.collect_items_exp, message.interact);
				System.out.println("V" + message.visit + "D" + message.destroy + "V" + message.build + "K"
						+ message.kill + "C" + message.collect_items_exp + "I" + message.interact);
				return null;
			}
			System.out.println("null" + "V" + message.visit + "D" + message.destroy + "V" + message.build + "K"
					+ message.kill + "C" + message.collect_items_exp + "I" + message.interact);
			return null;
		}
	}
}
