package net.wfoas.gh.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;
import net.wfoas.gh.world.utils.WorldPermission;
import net.wfoas.gh.world.utils.WorldPermissionsManager;

public class PacketPlayRequestProfileForPlayerInWorld implements IMessage {

	public PacketPlayRequestProfileForPlayerInWorld() {
	}

	String name, worldname;

	public PacketPlayRequestProfileForPlayerInWorld(String playername, String worldname) {
		this.name = playername;
		this.worldname = worldname;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		byte[] nA = new byte[buf.readInt()];
		buf.readBytes(nA);
		name = new String(nA, GameHelper.UTF_8);
		byte[] nWw = new byte[buf.readInt()];
		buf.readBytes(nWw);
		worldname = new String(nWw, GameHelper.UTF_8);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		byte[] nA = name.getBytes(GameHelper.UTF_8);
		buf.writeInt(nA.length);
		buf.writeBytes(nA);
		byte[] nWw = worldname.getBytes(GameHelper.UTF_8);
		buf.writeInt(nWw.length);
		buf.writeBytes(nWw);
	}

	public static class PacketPlayRequestOwnedWorldsHandler
			implements IMessageHandler<PacketPlayRequestProfileForPlayerInWorld, PacketPlayPlayerProfileForWorld> {

		@Override
		public PacketPlayPlayerProfileForWorld onMessage(PacketPlayRequestProfileForPlayerInWorld message,
				MessageContext ctx) {
			System.out.println("Packet gets handled");
			EntityPlayerMP ep = GameHelper.getUtils().getEntityPlayerByName(message.name);
			GHWorld w = new GHWorld(message.worldname, "", "");
			System.out.println(w);
			if (!GHWorldManager.exists(w)) {
				System.out.println("world: nullcandidate");
				return null;
			}
			if (ep == null) {
				System.out.println("player: nullcandidate");
				return null;
			}
			PacketPlayPlayerProfileForWorld p = new PacketPlayPlayerProfileForWorld(
					WorldPermissionsManager.getPermission(WorldPermission.VISIT, ep, w),
					WorldPermissionsManager.getPermission(WorldPermission.DESTROY, ep, w),
					WorldPermissionsManager.getPermission(WorldPermission.BUILD, ep, w),
					WorldPermissionsManager.getPermission(WorldPermission.KILL, ep, w),
					WorldPermissionsManager.getPermission(WorldPermission.COLLECT_ITEMS_EXP, ep, w),
					WorldPermissionsManager.getPermission(WorldPermission.INTERACT, ep, w));
			return p;
		}
	}
}