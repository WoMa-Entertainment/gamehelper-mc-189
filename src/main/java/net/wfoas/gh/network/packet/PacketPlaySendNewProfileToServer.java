package net.wfoas.gh.network.packet;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;
import net.wfoas.gh.omapi.GameHelperAPI;
import net.wfoas.gh.world.owner.WorldOwners;
import net.wfoas.gh.world.permissions.WorldPermissions;
import net.wfoas.gh.world.utils.WorldPermission;
import net.wfoas.gh.world.utils.WorldPermissionsManager;

public class PacketPlaySendNewProfileToServer implements IMessage {

	public PacketPlaySendNewProfileToServer() {
	}

	boolean visit;
	boolean destroy;
	boolean build;
	boolean kill;
	boolean collect_items_exp;
	boolean interact;
	String player;
	String world;

	public PacketPlaySendNewProfileToServer(String player, String world, boolean visit, boolean destroy, boolean build,
			boolean kill, boolean collect_items_exp, boolean interact) {
		super();
		this.player = player;
		this.world = world;
		this.visit = visit;
		this.destroy = destroy;
		this.build = build;
		this.kill = kill;
		this.collect_items_exp = collect_items_exp;
		this.interact = interact;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		byte[] byteArray = new byte[buf.readInt()];
		buf.readBytes(byteArray);
		player = new String(byteArray, GameHelper.UTF_8);
		byte[] byteArray2 = new byte[buf.readInt()];
		buf.readBytes(byteArray2);
		world = new String(byteArray2, GameHelper.UTF_8);
		this.visit = buf.readBoolean();
		this.destroy = buf.readBoolean();
		this.build = buf.readBoolean();
		this.kill = buf.readBoolean();
		this.collect_items_exp = buf.readBoolean();
		this.interact = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		byte[] array1 = player.getBytes(GameHelper.UTF_8);
		buf.writeInt(array1.length);
		buf.writeBytes(array1);
		byte[] array2 = world.getBytes(GameHelper.UTF_8);
		buf.writeInt(array2.length);
		buf.writeBytes(array2);
		buf.writeBoolean(visit);
		buf.writeBoolean(destroy);
		buf.writeBoolean(build);
		buf.writeBoolean(kill);
		buf.writeBoolean(collect_items_exp);
		buf.writeBoolean(interact);
	}

	public static class PacketPlaySendNewProfileToServerHandler
			implements IMessageHandler<PacketPlaySendNewProfileToServer, IMessage> {

		@Override
		public IMessage onMessage(final PacketPlaySendNewProfileToServer message, final MessageContext ctx) {
			GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
				@Override
				public void run() {
					EntityPlayerMP ep = GameHelper.getUtils().getEntityPlayerByName(message.player);
					GHWorld w = new GHWorld(message.world, "null", "null");
					System.out.println("WE" + w + "P" + ep);
					if (!GHWorldManager.exists(w) || ep == null) {
						System.out.println("nullworld");
						return;
					}
					if (!WorldOwners.isOwner(w, ctx.getServerHandler().playerEntity)) {
						ep.addChatMessage(
								new ChatComponentTranslation("gamehelper.error.world.nopermission.owner", w.getName()));
						return;
					}
					WorldPermissionsManager.setPermission(WorldPermission.VISIT, ep, w, message.visit);
					WorldPermissionsManager.setPermission(WorldPermission.BUILD, ep, w, message.build);
					WorldPermissionsManager.setPermission(WorldPermission.DESTROY, ep, w, message.destroy);
					WorldPermissionsManager.setPermission(WorldPermission.KILL, ep, w, message.kill);
					WorldPermissionsManager.setPermission(WorldPermission.COLLECT_ITEMS_EXP, ep, w,
							message.collect_items_exp);
					WorldPermissionsManager.setPermission(WorldPermission.INTERACT, ep, w, message.interact);
				}
			}, 0l);
			return null;
		}
	}
}
