package net.wfoas.gh.network.securedlogin;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.gui.login.GuiScreenLogin;

public class PacketPlayPasswordAuthRequest /* implements IConnectionHandler */ implements IMessage {

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class PacketPlayPasswordAuthRequestHandler
			implements IMessageHandler<PacketPlayPasswordAuthRequest, IMessage> {

		@Override
		public IMessage onMessage(PacketPlayPasswordAuthRequest message, MessageContext ctx) {
			// clientside
			if (Minecraft.getMinecraft().currentScreen instanceof GuiScreenLogin)
				return null;
			Minecraft.getMinecraft().thePlayer.openGui(GameHelper.instance, GuiHandler.GH_LOGIN,
					Minecraft.getMinecraft().thePlayer.worldObj, (int) Minecraft.getMinecraft().thePlayer.posX,
					(int) Minecraft.getMinecraft().thePlayer.posY, (int) Minecraft.getMinecraft().thePlayer.posZ);
			return null;
		}
	}
}