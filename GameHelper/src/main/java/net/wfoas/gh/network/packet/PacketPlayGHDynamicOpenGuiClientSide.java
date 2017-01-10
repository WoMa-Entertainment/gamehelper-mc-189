package net.wfoas.gh.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.gui.GuiHandler;

public class PacketPlayGHDynamicOpenGuiClientSide implements IMessage {
	int id, x, y, z;

	public PacketPlayGHDynamicOpenGuiClientSide(int id, int x, int y, int z) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public PacketPlayGHDynamicOpenGuiClientSide() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		id = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	public static class PacketPlayGHDynamicOpenGuiClientSideHandler
			implements IMessageHandler<PacketPlayGHDynamicOpenGuiClientSide, IMessage> {

		@Override
		public IMessage onMessage(final PacketPlayGHDynamicOpenGuiClientSide message, final MessageContext ctx) {
			Minecraft.getMinecraft().thePlayer.openGui(GameHelper.instance, message.id,
					ctx.getServerHandler().playerEntity.worldObj, message.x, message.y, message.z);
			return null;
		}
	}
}