package net.wfoas.gh.network.packet;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.omapi.GameHelperAPI;

public class PacketProgressDialogSyncCollection {

	public static class ForceClientToOpenClientProgressDialog implements IMessage {

		@Override
		public void fromBytes(ByteBuf buf) {
		}

		@Override
		public void toBytes(ByteBuf buf) {
		}

		public static class ForceClientToOpenClientProgressDialogHandler
				implements IMessageHandler<ForceClientToOpenClientProgressDialog, IMessage> {

			@Override
			public IMessage onMessage(final ForceClientToOpenClientProgressDialog message, MessageContext ctx) {
				GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
					@Override
					public void run() {
						GameHelper.getUtils().openClientProgressDialog();
					}
				}, 1l);
				return null;
			}

		}

	}

	public static class ForceClientToExitClientProgressDialog implements IMessage {

		@Override
		public void fromBytes(ByteBuf buf) {
		}

		@Override
		public void toBytes(ByteBuf buf) {
		}

		public static class ForceClientToExitClientProgressDialogHandler
				implements IMessageHandler<ForceClientToExitClientProgressDialog, IMessage> {

			@Override
			public IMessage onMessage(final ForceClientToExitClientProgressDialog message, MessageContext ctx) {
				GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
					@Override
					public void run() {
						GameHelper.getUtils().exitClientProgressDialog();
					}
				}, 1l);
				return null;
			}
		}
	}

	public static class ClientSetProgressDialogMessageTitle implements IMessage {

		String msg, title;

		public ClientSetProgressDialogMessageTitle() {
		}

		public ClientSetProgressDialogMessageTitle(String msg, String title) {
			this.msg = msg;
			this.title = title;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			int length = buf.readInt();
			byte[] bytearray = new byte[length];
			buf.readBytes(bytearray);
			msg = new String(bytearray, GameHelper.UTF_8);
			int length2 = buf.readInt();
			byte[] array = new byte[length2];
			buf.readBytes(array);
			title = new String(array, GameHelper.UTF_8);
		}

		@Override
		public void toBytes(ByteBuf buf) {
			byte[] array = msg.getBytes(GameHelper.UTF_8);
			buf.writeInt(array.length);
			buf.writeBytes(array);
			byte[] array2 = title.getBytes(GameHelper.UTF_8);
			buf.writeInt(array2.length);
			buf.writeBytes(array2);
		}

		public static class ClientSetProgressDialogMessageTitleHandler
				implements IMessageHandler<ClientSetProgressDialogMessageTitle, IMessage> {

			@Override
			public IMessage onMessage(final ClientSetProgressDialogMessageTitle message, MessageContext ctx) {
				GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
					@Override
					public void run() {
						GameHelper.getUtils().setClientProgressDialogTitle(message.title);
						GameHelper.getUtils().setClientProgressDialogMessage(message.msg);
					}
				}, 1l);
				return null;
			}
		}
	}

	public static class ClientSetProgressDialogPercentage implements IMessage {
		float percentage;

		public ClientSetProgressDialogPercentage() {
		}

		public ClientSetProgressDialogPercentage(float percentage) {
			this.percentage = percentage;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			percentage = buf.readFloat();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeFloat(percentage);
		}

		public static class ClientSetProgressDialogPercentageHandler
				implements IMessageHandler<ClientSetProgressDialogPercentage, IMessage> {

			@Override
			public IMessage onMessage(final ClientSetProgressDialogPercentage message, MessageContext ctx) {
				GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
					@Override
					public void run() {
						GameHelper.getUtils().setClientProgressDialogProgress(message.percentage);
					}
				}, 1l);
				return null;
			}
		}
	}

	public static class ClientSetProgressDialogCloseOption implements IMessage {
		boolean closeoption;

		public ClientSetProgressDialogCloseOption() {
		}

		public ClientSetProgressDialogCloseOption(boolean closeoption) {
			this.closeoption = closeoption;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			closeoption = buf.readBoolean();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeBoolean(closeoption);
		}

		public static class ClientSetProgressDialogCloseOptionHandler
				implements IMessageHandler<ClientSetProgressDialogCloseOption, IMessage> {

			@Override
			public IMessage onMessage(final ClientSetProgressDialogCloseOption message, MessageContext ctx) {
				GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
					@Override
					public void run() {
						GameHelper.getUtils().setClientProgressDialogCloseOption(message.closeoption);
					}
				}, 1l);
				return null;
			}
		}
	}
}